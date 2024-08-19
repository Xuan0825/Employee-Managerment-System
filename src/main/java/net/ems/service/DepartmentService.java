package net.ems.service;

import net.ems.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
 DepartmentDto createDepartment(DepartmentDto departmentDto);
 DepartmentDto getDepartmentById(Long departmentId);
 List<DepartmentDto> getAllDepartments();
 DepartmentDto updateDepartment(DepartmentDto departmentDto, Long departmentId);
 void deleteDepartment(Long departmentId);

}
