package br.com.attornatus.domain;

import br.com.attornatus.enums.TypeAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Street field cannot be empty or null")
    @NotNull
    @Column(name = "street")
    private String street;
    @NotEmpty(message = "Zip code field cannot be empty or null")
    @NotNull
    @Column(name = "zipcode")
    private Integer zipCode;
    @NotEmpty(message = "Address number field cannot be empty or null")
    @NotNull
    @Column(name = "addressnumber")
    private Integer addressNumber;
    @NotEmpty(message = "City field cannot be empty or null")
    @NotNull
    @Column(name = "city")
    private String city;
    @NotNull
    @NotEmpty(message = "Type address field cannot be empty or null")
    private TypeAddress typeAddress;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Address address = (Address) o;
        return id != null && Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
