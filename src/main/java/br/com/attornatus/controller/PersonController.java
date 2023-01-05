package br.com.attornatus.controller;


import br.com.attornatus.domain.Address;
import br.com.attornatus.domain.Person;
import br.com.attornatus.dtos.PersonDto;
import br.com.attornatus.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Person> findById(@PathVariable Long id) {
        Person personById = personService.findById(id);
        return ResponseEntity.ok().body(personById);
    }

    @GetMapping
    public ResponseEntity<List<PersonDto>> findAll() {
        List<Person> personList = personService.findAll();
        List<PersonDto> personDto = personList.stream().map(obj -> new PersonDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(personDto);
    }

    @PostMapping
    public ResponseEntity<Person> post(@Valid @RequestBody Person person) {
        Person personPost = personService.post(person);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(person.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PersonDto> update(@Valid @PathVariable Long id, @RequestBody PersonDto personDto) {
        Person personUpdate = personService.update(id, personDto);
        return ResponseEntity.ok().body(new PersonDto(personUpdate));
    }

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
