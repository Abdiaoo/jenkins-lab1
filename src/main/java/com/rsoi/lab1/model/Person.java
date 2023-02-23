package com.rsoi.lab1.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_persons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
    private String address;
    private String work;
}
