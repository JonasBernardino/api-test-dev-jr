package br.com.attornatus.repositories;

import br.com.attornatus.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT obj FROM Address obj WHERE obj.person.id = :id_person ORDER BY 'title'")
    List<Address> findAllByIdPerson(@Param(value = "id_person")Long id);
}
