package com.example.demo.controller;

import com.example.demo.dto.department.CreateDepartmentDto;
import com.example.demo.dto.department.GetAllDepartment;
import com.example.demo.dto.department.UpdateDepartment;
import com.example.demo.service.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")  // Base URL for Department APIs
public class DepartmentController {
    private final DepartmentService departmentService;
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping()
    public ResponseEntity<CreateDepartmentDto> createDepartment(@RequestBody CreateDepartmentDto createDepartmentDto) {
        CreateDepartmentDto dto = departmentService.save(createDepartmentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // Department güncelleme
    @PutMapping("{id}")
    public ResponseEntity<String> updateDepartment(@PathVariable Long id, @RequestBody UpdateDepartment updateDepartment) {
        updateDepartment.setId(id);
         departmentService.update(updateDepartment);
        return ResponseEntity.ok("Department successfully updated.");// 200 OK
    }

    // Tüm Department'ları listeleme
    @GetMapping()
    public ResponseEntity<List<GetAllDepartment>> getAllDepartments() {
        List<GetAllDepartment> departmentList = departmentService.getAllPersonal();
        return new ResponseEntity<>(departmentList, HttpStatus.OK); // 200 OK
    }

    // ID ile tek bir Department alma
    @GetMapping("/{id}")
    public ResponseEntity<CreateDepartmentDto> getDepartmentById(@PathVariable Long id) {
        CreateDepartmentDto createDepartmentDto = departmentService.getPersonalById(id);
        return new ResponseEntity<>(createDepartmentDto, HttpStatus.OK); // 200 OK
    }

    // Department silme
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePersonal(@PathVariable Long id) {
        departmentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
