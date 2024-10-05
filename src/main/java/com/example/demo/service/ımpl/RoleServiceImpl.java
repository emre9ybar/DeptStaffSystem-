package com.example.demo.service.ımpl;
import com.example.demo.core.ModelMapperServiceImpl;
import com.example.demo.dto.role.RoleDto;
import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapperServiceImpl mapperService;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapperServiceImpl mapperService) {
        this.roleRepository = roleRepository;
        this.mapperService = mapperService;
    }

    @Override
    public Role save(RoleDto RoleDto) {
        Role Role =this.mapperService.request().map(RoleDto,Role.class);
        return  roleRepository.save(Role);    }

    public Role update(RoleDto RoleDto) {
        Role Role = roleRepository.findById(RoleDto.getId()).
                orElseThrow(()-> new RuntimeException("not found id. "));
        this.mapperService.request().map(RoleDto,Role.class);
        return  roleRepository.save(Role);
    }

    public List<RoleDto> getAllRole() {
        List<Role> RoleDto=roleRepository.findAll();
        if (RoleDto.isEmpty()){
            throw new RuntimeException("not found list");
        }
        List<RoleDto> roleDtos=RoleDto.stream().map(Rolee ->
                this.mapperService.response().map(Rolee,RoleDto.class)).collect(Collectors.toList());
        return  roleDtos;
    }

    public RoleDto getPersonalById(Long id) {
        Role role =roleRepository.findById(id).orElseThrow(()->
                new RuntimeException("not found id "));
        RoleDto roleDto=this.mapperService.response().map(role,RoleDto.class);
        return roleDto;
    }

    public void delete(Long id) {
        roleRepository.deleteById(id);
    }
}
