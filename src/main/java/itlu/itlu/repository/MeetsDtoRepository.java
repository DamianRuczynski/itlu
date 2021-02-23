package itlu.itlu.repository;

import itlu.itlu.dto.MeetsDto;
import itlu.itlu.dto.MeetsStatus;
import itlu.itlu.dto.ProjectDto;
import itlu.itlu.dto.ProjectStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Repository
public class MeetsDtoRepository {

    private JdbcTemplate jdbcTemplate;

    public MeetsDtoRepository(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate; }

    public List<MeetsDto> getAllMeetsWithName(){
        return jdbcTemplate.query(
                "SELECT c.company_name, t.team_name, m.city, m.location, m.meet_purpose, m.meets_status, m.id, m.date_of_meet, m.time_of_meet \n" +
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
                rs.getDate("date_of_meet"),
                rs.getString("time_of_meet"),
                rs.getString("location"),
                rs.getString("city"),
                getDescription(status),
                rs.getString("company_name"),
                rs.getString("team_name"));
    }

    private String getDescription(Integer status) {
        return Arrays.stream(MeetsStatus.values())
                .filter(e-> e.getValue().equals(status))
                .findFirst()
                .orElse(MeetsStatus.PLANNED)
                .getDescription();
    }
}
