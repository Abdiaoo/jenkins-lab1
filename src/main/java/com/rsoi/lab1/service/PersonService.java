package com.rsoi.lab1.service;

import com.rsoi.lab1.dto.PersonRequest;
import com.rsoi.lab1.dto.PersonResponse;
import com.rsoi.lab1.model.Person;
import com.rsoi.lab1.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonService {
    private final PersonRepository personRepository;
    public PersonResponse createPerson(PersonRequest personRequest) {
        validateInput(personRequest);

        Person person = mapToPerson(personRequest);
        personRepository.save(person);
        log.info("Person {} saved successfully.", person.getId());
        return mapTOPersonResponse(person);
    }
    public List<PersonResponse> getALLPersons(){
        return personRepository.findAll().stream().map(this::mapTOPersonResponse).collect(Collectors.toList());    }
    public PersonResponse getPersonById(Integer id){
        Person person=personRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Person with id " + id + " not found"));
        return mapTOPersonResponse(person);
    }
    public PersonResponse updatePerson(Integer id,PersonRequest personRequest){
        Optional<Person> optionalPerson=personRepository.findById(id);
        if (!optionalPerson.isPresent()) {
            throw new IllegalArgumentException("Person with id " + id + " not found");
        }
        Person person=optionalPerson.get();
        if(personRequest.getName()!=null && !personRequest.getName().isEmpty()){
            person.setName(personRequest.getName());
        }
        if(personRequest.getAge()!=null && personRequest.getAge()>0){
            person.setAge(personRequest.getAge());
        }
        if(personRequest.getWork()!=null && !personRequest.getWork().isEmpty()){
            person.setName(personRequest.getWork());
        }
        if(personRequest.getAddress()!=null && !personRequest.getAddress().isEmpty()){
            person.setAddress(personRequest.getAddress());
        }
        personRepository.save(person);
        log.info("Person {} updated successfully.", person.getId());
        return mapTOPersonResponse(person);
    }
    public void deletePerson(Integer id){
        personRepository.deleteById(id);
    }
    private void validateInput(PersonRequest personRequest) {
        if (personRequest.getName() == null || personRequest.getName().isEmpty() ||
                personRequest.getAge() == null ||
                personRequest.getAddress() == null || personRequest.getAddress().isEmpty() ||
                personRequest.getWork() == null || personRequest.getWork().isEmpty()) {
            throw new IllegalArgumentException("Invalid input data.");
        }
    }

    private Person mapToPerson(PersonRequest personRequest) {
        return Person.builder()
                .name(personRequest.getName())
                .age(personRequest.getAge())
                .address(personRequest.getAddress())
                .work(personRequest.getWork())
                .build();
    }
    private PersonResponse mapTOPersonResponse(Person person){
        return PersonResponse.builder()
                .id(person.getId())
                .name(person.getName())
                .age(person.getAge())
                .address(person.getAddress())
                .work(person.getWork())
                .build();
    }


}

