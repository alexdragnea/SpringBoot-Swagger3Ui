package net.dg.springbootswagger3.repository;

import net.dg.springbootswagger3.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee e WHERE e.first_name like %:keyword% "
            + "or e.last_name like %:keyword% "
            + "or e.designation like %:keyword% ",
            nativeQuery = true)
    List<Employee> findByKeyword(@Param("keyword") String keyword);
}
