package com.example.demo.service;

import com.example.demo.dto.role.RoleDto;
import com.example.demo.entity.Role;

import java.util.List;

public interface RoleService {

    Role save(RoleDto RoleDto);
    Role update(RoleDto RoleDto);

    List<RoleDto> getAllRole();

    RoleDto getPersonalById(Long id);

    void delete(Long id);
}
