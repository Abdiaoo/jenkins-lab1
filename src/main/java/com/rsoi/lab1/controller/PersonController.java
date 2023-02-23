package com.rsoi.lab1.controller;

import com.rsoi.lab1.dto.PersonRequest;
import com.rsoi.lab1.dto.PersonResponse;
import com.rsoi.lab1.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    @PostMapping("")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody PersonRequest personRequest,UriComponentsBuilder ucBuilder){
        try {
            PersonResponse response=personService.createPerson(personRequest);
            HttpHeaders headers=new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/v1/persons/{id}").buildAndExpand(response.getId()).toUri());
            return new ResponseEntity<>(response,headers,HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<PersonResponse>> getAllPersons(){
        try {
            List<PersonResponse> persons=personService.getALLPersons();
            return new ResponseEntity<>(persons,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(@PathVariable("id") Integer id){
        try {
            PersonResponse response=personService.getPersonById(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<PersonResponse> deletePersonById(@PathVariable("id") Integer id){
        try {
            personService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePersonById(@PathVariable("id") Integer id,@RequestBody PersonRequest personRequest){
        try {
            PersonResponse response=personService.updatePerson(id,personRequest);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
