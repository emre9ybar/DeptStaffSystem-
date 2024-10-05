package com.example.demo.dto.role;

import com.example.demo.dto.personel.PersonalDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto {

    private Long id;

    private String name;  // Role adı

    private Long departmentId;

    private List<PersonalDto> personalDtos;

}
