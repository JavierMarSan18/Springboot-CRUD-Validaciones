package edu.progra3.students.services;

import edu.progra3.students.dto.StudentDTO;
import edu.progra3.students.entities.Student;
import edu.progra3.students.exceptions.ConflictException;
import edu.progra3.students.exceptions.NotFoundException;
import edu.progra3.students.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    public static final String STUDENT_NOT_FOUND = "Estudiante no encontrado";
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<StudentDTO> findAllStudents() {
        List<Student> students = studentRepository.findAll();

        if(students.isEmpty()){
            throw new NotFoundException(STUDENT_NOT_FOUND);
        }

        return students.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO findStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));

        return mapDTO(student);
    }

    @Override
    public List<StudentDTO> findStudentsById(String studentName) {
        List<Student> students = studentRepository.findByName(studentName);

        if(students.isEmpty()){
            throw new NotFoundException(STUDENT_NOT_FOUND);
        }

        return students.stream().map(this::mapDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentRequest) {

        if(studentRepository.existsByCarnet(studentRequest.getCarnet())) {
            throw new ConflictException("Ya existe una estudiante en el carnet: " + studentRequest.getCarnet());
        }
        if(studentRepository.existsByEmail(studentRequest.getEmail()))
        {
            throw new ConflictException("Ya existe un estudiante con el email: " + studentRequest.getEmail());
        }

        Student createdStudent = studentRepository.save(mapEntity(studentRequest));

        return mapDTO(createdStudent);
    }

    @Override
    public StudentDTO updateStudentById(Long studentId, StudentDTO studentRequest) {
        Student currentStudent = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));

        if(!studentRequest.getCarnet().equals(currentStudent.getCarnet()) && studentRepository.existsByCarnet(studentRequest.getCarnet())) {
            throw new ConflictException("Ya existe una estudiante en el carnet: " + studentRequest.getCarnet());
        }
        if(!studentRequest.getEmail().equals(currentStudent.getEmail()) && studentRepository.existsByEmail(studentRequest.getEmail()))
        {
            throw new ConflictException("Ya existe un estudiante con el email: " + studentRequest.getEmail());
        }

        Student updatedStudent = mapEntity(studentRequest);
        updatedStudent.setId(currentStudent.getId());

        return mapDTO(studentRepository.save(updatedStudent));
    }

    @Override
    public void deleteStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundException(STUDENT_NOT_FOUND));
        studentRepository.delete(student);
    }

    //Convertir de DTO a entidad
    private Student mapEntity(StudentDTO studentDTO) {
        return modelMapper.map(studentDTO, Student.class);
    }

    //Convertir de entidad a DTO
    private StudentDTO mapDTO(Student student){
        return modelMapper.map(student, StudentDTO.class);
    }
}
