package br.com.attornatus.controller;

import br.com.attornatus.domain.Address;
import br.com.attornatus.dtos.AddressDto;
import br.com.attornatus.services.AddressService;
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
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id) {
        Address addressById = addressService.findById(id);
        return ResponseEntity.ok().body(addressById);
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll(@RequestParam(value = "person", defaultValue = "0") Long id_person) {
        List<Address> addresses = addressService.findAll(id_person);
        List<AddressDto> addressDto = addresses.stream().map(obj -> new AddressDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(addressDto);
    }

    @PostMapping
    public ResponseEntity<Address> post(@RequestParam(value = "person", defaultValue = "0") Long id_person, @Valid @RequestBody Address address) {
        Address addressPost = addressService.post(id_person, address);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/address/{id}").buildAndExpand(addressPost.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Address> update(@PathVariable Long id, @Valid @RequestBody Address address) {
        Address addressUpdate = addressService.update(id, address);
        return ResponseEntity.ok().body(addressUpdate);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Address> updatePatch(@PathVariable Long id, @Valid @RequestBody Address address) {
        Address addressUpdate = addressService.update(id, address);
        return ResponseEntity.ok().body(addressUpdate);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
