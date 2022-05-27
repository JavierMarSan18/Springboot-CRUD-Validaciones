package edu.progra3.students.services;

import edu.progra3.students.dto.StudentDTO;

import java.util.List;

public interface StudentService {
    List<StudentDTO> findAllStudents();
    StudentDTO findStudentById(Long studentId);
    List<StudentDTO> findStudentsById(String studentName);
    StudentDTO createStudent(StudentDTO studentRequest);
    StudentDTO updateStudentById(Long studentId, StudentDTO studentRequest);
    void deleteStudentById(Long studentId);
}
