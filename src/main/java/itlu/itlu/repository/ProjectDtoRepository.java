package itlu.itlu.repository;

import itlu.itlu.dto.ProjectDto;
import itlu.itlu.dto.ProjectStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class ProjectDtoRepository {

    private JdbcTemplate jdbcTemplate;

    public ProjectDtoRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<ProjectDto> getAllProjectWithName(){
        return jdbcTemplate.query(
                "SELECT p.id, t.team_name, c.company_name, p.project_name, p.project_purpose, p.project_status \n" +
                        "FROM project p \n" +
                        "LEFT JOIN customer c ON c.id = p.id_customer\n" +
                        "LEFT join team t ON t.id = p.id_team" ,
                (rs, rn) -> mapToProjectDto(rs));
    }

    private ProjectDto mapToProjectDto(ResultSet rs) throws SQLException {
        Integer status = rs.getInt("project_status");
        return new ProjectDto(
                rs.getLong("id"),
                rs.getString("company_name"),
                rs.getString("team_name"),
                rs.getString("project_name"),
                rs.getString("project_purpose"),
                getDescription(status));
    }

    private String getDescription(Integer status) {
        return Arrays.stream(ProjectStatus.values())
                .filter(e-> e.getValue().equals(status))
                .findFirst()
                .orElse(ProjectStatus.IN_PREPARATION)
                .getDescription();
    }
}
