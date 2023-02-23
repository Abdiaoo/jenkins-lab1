package com.rsoi.lab1.repository;

import com.rsoi.lab1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Integer> {
}
