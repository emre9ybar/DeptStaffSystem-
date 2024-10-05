package com.example.demo.dto.department;

import com.example.demo.dto.role.RoleDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetAllDepartment {
    private Long id;

    @NotBlank(message = "veri girmeni  bırakılamaz")
    @NotNull(message = "boş bırakılamaz")
    private String name;


    private List<RoleDto> roleDtos;

}
