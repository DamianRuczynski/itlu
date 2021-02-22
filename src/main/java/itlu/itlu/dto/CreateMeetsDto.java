package itlu.itlu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateMeetsDto {

    private Date date_of_meet;
    private String meet_purpose;
    private String location;
    private String city;
    private Long id_team;
    private Long id_customer;
    private Integer meets_status;
}
