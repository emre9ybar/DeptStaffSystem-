package com.example.demo.service;

import com.example.demo.dto.personel.PersonalDto;
import com.example.demo.entity.Personal;

import java.util.List;

public interface PersonalService  {

    Personal save(PersonalDto personalDto);
    Personal update(PersonalDto personalDto);

    List<PersonalDto> getAllPersonal();

    PersonalDto getPersonalById(Long id);

    void delete(Long id);



}
