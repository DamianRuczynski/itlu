package itlu.itlu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private String name;
    private String surname;
    private String email;
    private String phone_number;
    private String nip_number;
    private String city;
    private String street;
    private String house_number;
}