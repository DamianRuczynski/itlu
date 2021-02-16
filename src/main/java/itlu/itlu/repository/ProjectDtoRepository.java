package itlu.itlu.repository;

import itlu.itlu.dto.ProjectDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProjectDtoRepository {

    private JdbcTemplate jdbcTemplate;

    public ProjectDtoRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<ProjectDto> getAllProjectWithName(){
        return jdbcTemplate.query(
                "SELECT p.id, t.team_name, c.company_name, p.project_name, p.project_purpose \n" +
                        "FROM project p \n" +
                        "LEFT JOIN customer c ON c.id = p.id_customer\n" +
                        "LEFT join team t ON t.id = p.id_team" ,
                (rs, rn) -> mapToProjectDto(rs));
    }

    private ProjectDto mapToProjectDto(ResultSet rs) throws SQLException {
        return new ProjectDto(
                rs.getLong("id"),
                rs.getString("company_name"),
                rs.getString("team_name"),
                rs.getString("project_name"),
                rs.getString("project_purpose"));
    }
}
