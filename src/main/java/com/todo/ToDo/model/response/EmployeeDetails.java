package com.todo.ToDo.model.response;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Employee")
public class EmployeeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;
}
