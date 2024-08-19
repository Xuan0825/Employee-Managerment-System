package net.ems.service.impl;

import lombok.AllArgsConstructor;
import net.ems.dto.EmployeeDto;
import net.ems.entity.Department;
import net.ems.entity.Employee;
import net.ems.exception.ResourceNotFoundException;
import net.ems.mapper.EmployeeMapper;
import net.ems.repository.DepartmentRespository;
import net.ems.repository.EmployeeRepository;
import net.ems.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
     private EmployeeRepository employeeRepository;
     private DepartmentRespository departmentRespository;
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Department department = departmentRespository.findById(employeeDto.getDepartmentId()).orElseThrow(()->
                new ResourceNotFoundException("department does not exist by given id : "+ employeeDto.getDepartmentId())
                );
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
      Employee savedEmployee = employeeRepository.findById(employeeId).orElseThrow(()->
              new ResourceNotFoundException("Resource does not exist with given employeeId : "+ employeeId));
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
       List<Employee> employeesList = employeeRepository.findAll();
       return employeesList.stream().map((employee)->EmployeeMapper.mapToEmployeeDto(employee)).collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto,Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Employee does not exist for given employeeId : "+ employeeId));
        Department department= departmentRespository.findById(employeeDto.getDepartmentId()).orElseThrow(()->
                        new ResourceNotFoundException("department does not exist by given id : "+ employeeDto.getDepartmentId())
                );
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        employee.setDepartment(department);

        Employee saveEmployee= employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(saveEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee savedEmployee = employeeRepository.findById(employeeId).orElseThrow(()->
                new ResourceNotFoundException("Resource does not exist with given employeeId : "+ employeeId));
        employeeRepository.deleteById(employeeId);
    }

}
