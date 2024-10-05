package com.example.demo.service;

import com.example.demo.dto.department.CreateDepartmentDto;
import com.example.demo.dto.department.GetAllDepartment;
import com.example.demo.dto.department.UpdateDepartment;

import java.util.List;

public interface DepartmentService  {
    CreateDepartmentDto save(CreateDepartmentDto CreateDepartmentDto);
    String update(UpdateDepartment UpdateDepartment);

    List<GetAllDepartment> getAllPersonal();

    CreateDepartmentDto getPersonalById(Long id);

    void delete(Long id);
}
