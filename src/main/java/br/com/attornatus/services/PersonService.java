package br.com.attornatus.services;

import br.com.attornatus.domain.Address;
import br.com.attornatus.domain.Person;
import br.com.attornatus.dtos.PersonDto;
import br.com.attornatus.repositories.AddressRepository;
import br.com.attornatus.repositories.PersonRepository;
import br.com.attornatus.services.exceptions.DataIntegrityViolationException;
import br.com.attornatus.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Person findById(Long id) {
        Optional<Person> byId = personRepository.findById(id);
        return byId.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id + "; Type: " + Person.class.getName()));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person post(Person person, Address address) {
        List<Address> list = new ArrayList<Address>();
        list.add(address);
        person.setId(null);
        person.setAddressList(list);
        Person personSave = personRepository.save(person);
        return personSave;
    }

    public Person update(Long id, PersonDto personDto) {
        Person personUpdate = findById(id);
        personUpdate.setName(personDto.getName());
        personUpdate.setBirthDate(personDto.getBirthDate());
        return personRepository.save(personUpdate);

    }

    public void delete(Long id) {
        findById(id);
        try {
            personRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Person cannot be deleted!");
        }
    }
}
