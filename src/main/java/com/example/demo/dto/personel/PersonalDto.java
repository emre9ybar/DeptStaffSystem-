package com.example.demo.dto.personel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDto {
    private Long id;
    private Long departmentId;
    private Long roleId;
    private String name;
    private String lastname;
    private String degree;
    private Timestamp createdAt;
    private Timestamp updateAt;

    public void setDegree(String degree) {
        this.degree = degree.substring(0,4);
    }
}
