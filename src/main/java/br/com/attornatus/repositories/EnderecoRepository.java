package br.com.attornatus.repositories;

import br.com.attornatus.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Address, Long> {
}
