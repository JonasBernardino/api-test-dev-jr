package br.com.attornatus.services;

import br.com.attornatus.domain.Address;
import br.com.attornatus.repositories.EnderecoRepository;
import br.com.attornatus.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Address> findAll() {
        return enderecoRepository.findAll();
    }

    public Address findById(Long id) {
        Optional<Address> enderecoOptional = enderecoRepository.findById(id);
        return enderecoOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + "; Tipo: " + Address.class.getName()));
    }
}
