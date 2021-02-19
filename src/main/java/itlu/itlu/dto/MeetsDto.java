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
public class MeetsDto {

    private Long id;
    private String meet_purpose;
    private String date_of_meet;
    private String location;
    private String city;
    private String meets_status;
    private String companyName;
    private String teamName;

}
