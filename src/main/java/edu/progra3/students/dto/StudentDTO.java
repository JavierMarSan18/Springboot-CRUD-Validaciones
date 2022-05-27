package edu.progra3.students.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter @Setter
public class StudentDTO {
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacío")
    private String name;

    @NotEmpty(message = "El email no puede estar vacío")
    @Email(message = "Formato de email no válido")
    private String email;

    @NotEmpty(message = "El carnet no puede estar vacío")
    private String carnet;

    @NotNull(message = "El promedio no puede ser nulo")
    private Integer average;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "La fecha no puede ser nula")
    private Date createAt;

    public StudentDTO(){

    }
}
