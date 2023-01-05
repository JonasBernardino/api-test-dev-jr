package br.com.attornatus.dtos;

import br.com.attornatus.domain.Address;
import br.com.attornatus.enums.TypeAddress;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDto implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public AddressDto(Address addressDto){
        this.street = addressDto.getStreet();
        this.zipCode = addressDto.getZipCode();
        this.addressNumber = addressDto.getAddressNumber();
        this.city = addressDto.getCity();
        this.typeAddress = addressDto.getTypeAddress();
    }
}
