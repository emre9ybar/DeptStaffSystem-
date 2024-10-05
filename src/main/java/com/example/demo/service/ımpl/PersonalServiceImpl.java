package com.example.demo.service.ımpl;

import com.example.demo.core.ModelMapperService;
import com.example.demo.dto.personel.PersonalDto;
import com.example.demo.entity.Personal;
import com.example.demo.repository.PersonalRepository;
import com.example.demo.service.PersonalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonalServiceImpl implements PersonalService {

    private final PersonalRepository personalRepository;
    private final ModelMapperService mapperService;

    public PersonalServiceImpl(PersonalRepository personalRepository, ModelMapperService mapperService) {
        this.personalRepository = personalRepository;
        this.mapperService = mapperService;
    }


    public Personal save(PersonalDto personalDto) {
        Personal personal = this.mapperService.request().map(personalDto,Personal.class);
        return  personalRepository.save(personal);
    }

    public Personal update(PersonalDto personalDto) {
        Personal personal = personalRepository.findById(personalDto.getId()).
                orElseThrow(()-> new RuntimeException("not found id. "));
        this.mapperService.request().map(personalDto,Personal.class);
        return  personalRepository.save(personal);
    }

    public List<PersonalDto> getAllPersonal() {
       List<Personal> personals=personalRepository.findAll();
       if (personals.isEmpty()){
           throw new RuntimeException("not found list");
       }
       List<PersonalDto> personalDtos=personals.stream().map(personal ->
               this.mapperService.response().map(personal,PersonalDto.class)).collect(Collectors.toList());
       return  personalDtos;
    }

    public PersonalDto getPersonalById(Long id) {
        Personal personal=personalRepository.findById(id).orElseThrow(()->
                new RuntimeException("not found id "));
        PersonalDto personalDto=this.mapperService.response().map(personal,PersonalDto.class);
        return personalDto;
    }

    public void delete(Long id) {
        personalRepository.deleteById(id);
    }
}
