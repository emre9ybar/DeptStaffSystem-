package com.example.demo.controller;

import com.example.demo.dto.role.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // Yeni Role kaydı oluşturma
    @PostMapping()
    public ResponseEntity<Role> createRole(@RequestBody RoleDto roleDto) {
        Role savedRole = roleService.save(roleDto);
        return new ResponseEntity<>(savedRole, HttpStatus.CREATED); // 201 Created
    }

    @PutMapping()
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        roleDto.setId(id); // Gelen roleDto'ya id setleniyor
        Role updatedRole = roleService.update(roleDto);
        return new ResponseEntity<>(updatedRole, HttpStatus.OK); // 200 OK
    }


    @GetMapping()
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<RoleDto> roleList = roleService.getAllRole();
        return new ResponseEntity<>(roleList, HttpStatus.OK); // 200 OK
    }

    // ID ile tek bir Role alma
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        RoleDto roleDto = roleService.getPersonalById(id);
        return new ResponseEntity<>(roleDto, HttpStatus.OK); // 200 OK
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
