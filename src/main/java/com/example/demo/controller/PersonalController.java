package com.example.demo.controller;

import com.example.demo.dto.personel.PersonalDto;
import com.example.demo.entity.Personal;
import com.example.demo.service.PersonalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personals")  // Base URL for Personal APIs
public class PersonalController {

    private final PersonalService personalService;

    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @PostMapping()
    public ResponseEntity<Personal> createPersonal(@RequestBody PersonalDto personalDto) {
        Personal savedPersonal = personalService.save(personalDto);
        return new ResponseEntity<>(savedPersonal, HttpStatus.CREATED); // 201 Created
    }


    @PutMapping("{id}")
    public ResponseEntity<Personal> updatePersonal(@PathVariable Long id, @RequestBody PersonalDto personalDto) {
        personalDto.setId(id); // ID güncellenecek DTO'ya set ediliyor
        Personal updatedPersonal = personalService.update(personalDto);
        return new ResponseEntity<>(updatedPersonal, HttpStatus.OK); // 200 OK
    }


    @GetMapping()
    public ResponseEntity<List<PersonalDto>> getAllPersonals() {
        List<PersonalDto> personalList = personalService.getAllPersonal();
        return new ResponseEntity<>(personalList, HttpStatus.OK); // 200 OK
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonalDto> getPersonalById(@PathVariable Long id) {
        PersonalDto personalDto = personalService.getPersonalById(id);
        return new ResponseEntity<>(personalDto, HttpStatus.OK); // 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonal(@PathVariable Long id) {
        personalService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
    }
}
