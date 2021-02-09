package itlu.itlu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String city;
    private String phone_number;
    private LocalDate date_of_employment;
    private Long id_team;
}
