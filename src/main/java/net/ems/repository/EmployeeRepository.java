package net.ems.repository;

import net.ems.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    //<> the type for JpaRepository will be entity and its type of primary key
    // using repository to inherit the crud method to interact with database
}
