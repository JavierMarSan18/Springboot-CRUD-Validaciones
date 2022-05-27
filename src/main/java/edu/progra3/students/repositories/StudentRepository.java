package edu.progra3.students.repositories;

import edu.progra3.students.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByName(String name);
    Boolean existsByEmail(String email);
    Boolean existsByCarnet(String carnet);

}
