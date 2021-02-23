package itlu.itlu.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MeetsDto {

    private Long id;
    private String meet_purpose;
    private Date date_of_meet;
    private String time_of_meet;
    private String location;
    private String city;
    private String meets_status;
    private String companyName;
    private String teamName;

}
