package com.iqbal.loginsystem.service;

import com.iqbal.loginsystem.constant.EUser;
import com.iqbal.loginsystem.entity.Role;
import com.iqbal.loginsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    @Transactional(rollbackFor = Exception.class)
    public Role getOrSave(EUser role){
        Optional<Role> byUser = repository.findByUser(role);

        if (byUser.isPresent()) return byUser.get();

        Role roles = Role.builder()
                .user(role)
                .build();

        return repository.save(roles);
    }
}
