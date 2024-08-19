package net.ems.service.impl;

import lombok.AllArgsConstructor;
import net.ems.dto.DepartmentDto;
import net.ems.entity.Department;
import net.ems.exception.ResourceNotFoundException;
import net.ems.mapper.DepartmentMapper;
import net.ems.repository.DepartmentRespository;
import net.ems.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private DepartmentRespository departmentRespository;
    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto){
        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department saveDepartment = departmentRespository.save(department);
        return DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
       Department department = departmentRespository.findById(departmentId).orElseThrow(()->
               new ResourceNotFoundException("Department does not exist by given Id : " + departmentId));
       return DepartmentMapper.mapToDepartmentDto(department);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<DepartmentDto> departmentDtos = departmentRespository.findAll().stream()
                .map(department -> DepartmentMapper.mapToDepartmentDto(department)).collect(Collectors.toList());
        return departmentDtos;
    }

    @Override
    public DepartmentDto updateDepartment(DepartmentDto departmentDto, Long departmentId) {
       Department department = departmentRespository.findById(departmentId).orElseThrow(()->
               new ResourceNotFoundException("Department does not exist by given Id : " + departmentId));
       department.setDepartmentName(departmentDto.getDepartmentName());
       department.setDepartmentDescription(departmentDto.getDepartmentDescription());
       Department saveDepartment = departmentRespository.save(department);
       return DepartmentMapper.mapToDepartmentDto(saveDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRespository.findById(departmentId).orElseThrow(()->
                new ResourceNotFoundException("Department does not exist by given Id : " + departmentId));
       departmentRespository.deleteById(departmentId);
    }


}
