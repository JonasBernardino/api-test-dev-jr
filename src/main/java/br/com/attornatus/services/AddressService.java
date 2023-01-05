package br.com.attornatus.services;

import br.com.attornatus.domain.Address;
import br.com.attornatus.domain.Person;
import br.com.attornatus.enums.TypeAddress;
import br.com.attornatus.repositories.AddressRepository;
import br.com.attornatus.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private PersonService personService;

    public List<Address> findAll(Long id_person) {
        personService.findById(id_person);
        return addressRepository.findAllByIdPerson(id_person);
    }

    public Address findById(Long id) {
        Optional<Address> addressOptional = addressRepository.findById(id);
        return addressOptional.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! ID: " + id + "; Type: " + Address.class.getName())
        );
    }

    public Address post(Long id_person, Address address) {
        address.setId(null);
        if (address.getTypeAddress() == TypeAddress.SIM) {
            address.setTypeAddress(TypeAddress.SIM);
        } else if (address.getTypeAddress() == TypeAddress.NAO) {
            address.setTypeAddress(TypeAddress.NAO);
        } else {
            address.setTypeAddress(TypeAddress.SIM);
        }
        Person person = personService.findById(id_person);
        address.setPerson(person);
        return addressRepository.save(address);
    }

    private void updateData(Address newAddress, Address address) {
        newAddress.setStreet(address.getStreet());
        newAddress.setZipCode(address.getZipCode());
        newAddress.setAddressNumber(address.getAddressNumber());
        newAddress.setCity(address.getCity());
        newAddress.setTypeAddress(address.getTypeAddress());
    }

    public Address update(Long id, Address address) {
        address.setId(null);
        Address newAddress = findById(id);
        updateData(newAddress, address);
        return addressRepository.save(newAddress);
    }

    public void delete(Long id) {
        final Address address = findById(id);
        addressRepository.delete(address);
    }
}
