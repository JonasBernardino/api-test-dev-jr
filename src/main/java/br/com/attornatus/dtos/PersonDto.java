package br.com.attornatus.dtos;

import br.com.attornatus.domain.Person;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull
    @NotEmpty(message = "Name field cannot be empty or null")
    @Column(name = "name")
    private String name;
    @NotNull
    @NotEmpty(message = "Date of birth field cannot be empty or null")
    @Column(name = "birthdate")
    private Date birthDate;

    public PersonDto(Person personDto) {
        this.id = personDto.getId();
        this.name = personDto.getName();
        this.birthDate = personDto.getBirthDate();
    }
}
