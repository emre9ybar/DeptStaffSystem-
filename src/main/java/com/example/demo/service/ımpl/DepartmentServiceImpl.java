package com.example.demo.service.ımpl;

import com.example.demo.core.ModelMapperServiceImpl;
import com.example.demo.dto.department.CreateDepartmentDto;
import com.example.demo.dto.department.GetAllDepartment;
import com.example.demo.dto.department.UpdateDepartment;
import com.example.demo.dto.personel.PersonalDto;
import com.example.demo.dto.role.RoleDto;
import com.example.demo.entity.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapperServiceImpl mapperService;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapperServiceImpl mapperService) {
        this.departmentRepository = departmentRepository;
        this.mapperService = mapperService;
    }

    @Override
    public CreateDepartmentDto save(CreateDepartmentDto createDepartmentDto) {
        Department department = this.mapperService.request().map(createDepartmentDto, Department.class);
        Department departmentOne = departmentRepository.save(department);
        CreateDepartmentDto dto = this.mapperService.response().map(departmentOne, CreateDepartmentDto.class);
        return dto;

    }

    @Override
    public String update(UpdateDepartment updateDepartmentRequest) {
        Department department = departmentRepository.findById(updateDepartmentRequest.getId())
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + updateDepartmentRequest.getId()));

        department.setName(updateDepartmentRequest.getName());

        departmentRepository.save(department);

        return "Department successfully updated.";
    }

    public List<GetAllDepartment> getAllPersonal() {
        List<Department> departments = departmentRepository.findAll();
        if (departments.isEmpty()) {
            throw new RuntimeException("Department list not found");
        }
        List<GetAllDepartment> getAllDepartments = departments.stream().map(department -> {
            // Departmanı GetAllDepartment DTO'ya çevir
            GetAllDepartment getAllDepartment = this.mapperService.response().map(department, GetAllDepartment.class);

            // Departmana ait rolleri getir ve RoleDto'ya çevir
            List<RoleDto> roleDtos = department.getRoles().stream().map(role -> {
                RoleDto roleDto = this.mapperService.response().map(role, RoleDto.class);

                // Her role ait personelleri getir ve PersonalDto'ya çevir
                List<PersonalDto> personalDtos = role.getPersonals().stream()
                        .map(personal -> this.mapperService.response().map(personal, PersonalDto.class))
                        .collect(Collectors.toList());

                // RoleDto'nun içerisine personelleri ekle
                roleDto.setPersonalDtos(personalDtos);

                return roleDto;
            }).collect(Collectors.toList());

            // GetAllDepartment DTO'suna roleDtos listesini ekle
            getAllDepartment.setRoleDtos(roleDtos);

            return getAllDepartment;
        }).collect(Collectors.toList());

        return getAllDepartments;
    }


    @Override
    public CreateDepartmentDto getPersonalById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() ->
                new RuntimeException("not found department"));
        CreateDepartmentDto createDepartmentDto = this.mapperService.response().map(department, CreateDepartmentDto.class);
        return createDepartmentDto;
    }

    @Override
    public void delete(Long id) {
        this.departmentRepository.deleteById(id);
    }
}
