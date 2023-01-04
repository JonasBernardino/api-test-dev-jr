package br.com.attornatus.repositories;

import br.com.attornatus.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Person, Long> {
}
