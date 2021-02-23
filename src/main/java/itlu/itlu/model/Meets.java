package itlu.itlu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Meets {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String meet_purpose;
    private LocalDate date_of_meet;
    private LocalTime time_of_meet;
    private String location;
    private String city;
    private Long id_team;
    private Long id_customer;
    private Integer meets_status;

}
