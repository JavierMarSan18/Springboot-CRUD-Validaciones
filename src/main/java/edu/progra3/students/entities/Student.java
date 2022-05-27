package edu.progra3.students.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Getter @Setter
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "carnet")})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "carnet")
    private String carnet;

    private Integer average;

    @Column(name = "create_at")
    private Date createAt;

    public Student(){

    }
}
