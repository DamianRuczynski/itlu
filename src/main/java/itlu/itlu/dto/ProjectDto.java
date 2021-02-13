package itlu.itlu.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    private Long id;
    private String companyName;
    private String teamName;
    private String projectName;
    private String projectPurpose;
}
