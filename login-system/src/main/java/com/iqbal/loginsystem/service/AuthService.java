package com.iqbal.loginsystem.service;

import com.iqbal.loginsystem.constant.EUser;
import com.iqbal.loginsystem.entity.Role;
import com.iqbal.loginsystem.entity.User;
import com.iqbal.loginsystem.model.LoginRequest;
import com.iqbal.loginsystem.model.LoginResponse;
import com.iqbal.loginsystem.model.RegisterRequest;
import com.iqbal.loginsystem.model.RegisterResponse;
import com.iqbal.loginsystem.repository.UserRepository;
import com.iqbal.loginsystem.security.JwtUtils;
import com.iqbal.loginsystem.utils.GlobalUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final RoleService roleService;
    private final String usernames;
    private final String passwords;
    private final String fullNames;

    @Autowired
    public AuthService(UserRepository repository, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                       RoleService roleService, @Value("${app.username}") String usernames,
                       @Value("${app.password}") String password,
                       @Value("${app.fullName}") String fullName) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.roleService = roleService;
        this.usernames = usernames;
        this.passwords = password;
        this.fullNames = fullName;
    }

    @PostConstruct
    @Transactional(rollbackFor = Exception.class)
    public void initAdmin(){

            String username = usernames;
            String password = passwords;
            String encode = passwordEncoder.encode(password);
            String fullName = fullNames;

            log.info("Username: {}", username);
            log.info("Password: {}", password);
            log.info("Full Name: {}", fullName);

            Optional<User> optionalUser = repository.findByUsername(username);

            if (optionalUser.isPresent()) return;

            Role roleAdmin = roleService.getOrSave(EUser.ROLE_ADMIN);

            Role roleUser = roleService.getOrSave(EUser.ROLE_USER);

            User user = User.builder()
                    .username(username)
                    .password(encode)
                    .fullName(fullName)
                    .roles(List.of(roleAdmin, roleUser))
                    .build();
            repository.save(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public LoginResponse login(LoginRequest request) {

        if (request.getUsername().isEmpty() ||
            request.getPassword().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Username or Password empty!");

        if (!GlobalUtils.isValidUsername(request.getUsername()) ||
            !GlobalUtils.isValidPassword(request.getPassword())) throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "not in accordance with pattern"
        );

        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        Authentication authentication = authenticationManager.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        User user = (User) authentication.getPrincipal();

        String token = jwtUtils.generateToken(user);
        List<String> parts = user.getRoles().stream().map(part -> part.getUser().name()).toList();

        return LoginResponse.builder()
                .token(token)
                .roles(parts)
                .build();
    }

    @Transactional(rollbackFor = Exception.class)
    public RegisterResponse register(RegisterRequest request){
        if (request.getUsername().isEmpty() ||
                request.getPassword().isEmpty() ||
            request.getFullName().isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Username, Password, Full Name empty!");

        if (request.getFullName().length() >= 30) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Result Character max 30");

        if (!GlobalUtils.isValidUsername(request.getUsername()) ||
                !GlobalUtils.isValidPassword(request.getPassword()) ||
            !GlobalUtils.isValidFullName(request.getFullName())) throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "not in accordance with pattern"
        );

        log.info("Username: {}", request.getUsername());
        log.info("Password: {}", request.getPassword());
        log.info("Full Name: {}", request.getFullName());

        Optional<User> optionalUser = repository.findByUsername(request.getUsername());

        if (optionalUser.isPresent()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already exist");

        Role roleUser = roleService.getOrSave(EUser.ROLE_USER);

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .fullName(request.getFullName())
                .roles(List.of(roleUser))
                .build();

        repository.saveAndFlush(user);

        return RegisterResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .build();
    }
}
