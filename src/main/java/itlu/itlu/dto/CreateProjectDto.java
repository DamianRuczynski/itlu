package itlu.itlu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProjectDto {

    private String project_name;
    private String project_purpose;
    private Long id_team;
    private Long id_customer;
}
