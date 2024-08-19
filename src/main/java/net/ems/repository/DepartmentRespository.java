package net.ems.repository;

import net.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRespository extends JpaRepository<Department,Long> {
}
