package com.iqbal.loginsystem.service;

import com.iqbal.loginsystem.entity.User;
import com.iqbal.loginsystem.model.UserResponse;
import com.iqbal.loginsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public User loadByUserId(Long id){
        return repository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "UNAUTHORIZED")
        );
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email UNAUTHORIZED")
        );
    }

    @Transactional(readOnly = true)
    public List<UserResponse> dashboard(){
        List<User> users = repository.findAll();

        List<UserResponse> responses = new ArrayList<>();

        users.forEach(user -> {
            UserResponse response = UserResponse.builder()
                    .id(user.getId())
                    .fullName(user.getFullName())
                    .build();
            responses.add(response);
        });

        return responses;
    }
}
