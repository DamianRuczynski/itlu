package itlu.itlu.repository;

import itlu.itlu.dto.MeetsDto;
import itlu.itlu.dto.ProjectDto;
import itlu.itlu.dto.ProjectStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Repository
public class MeetsDtoRepository {

    private JdbcTemplate jdbcTemplate;

    public MeetsDtoRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<MeetsDto> getAllMeetsWithName(){
        return jdbcTemplate.query(
                "SELECT c.company_name, t.team_name, m.city, m.location, m.meet_purpose\n" +
                        "FROM meets m\n" +
                        "LEFT JOIN customer c ON c.id = m.id_customer\n" +
                        "LEFT JOIN team t ON t.id = m.id_team;" ,
                (rs, rn) -> mapToMeetsDto(rs));
    }

    private MeetsDto mapToMeetsDto(ResultSet rs) throws SQLException {
        Integer status = rs.getInt("meets_status");
        return new MeetsDto(
                rs.getLong("id"),
                rs.getString("meet_purpose"),
                rs.getString("date_od_meet"),
                rs.getString("location"),
                rs.getString("city"),
                rs.getString("companyName"),
                rs.getString("teamName"),
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
