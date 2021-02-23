package itlu.itlu.service;


import itlu.itlu.dto.TeamDto;
import itlu.itlu.model.Team;
import itlu.itlu.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private  final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }

    public void saveTeam(TeamDto teamDto){

        Team newTeam = new Team();
        Optional.ofNullable(teamDto.getId()).ifPresent(id-> newTeam.setId(id));
        newTeam.setTeam_name(teamDto.getTeam_name());
        newTeam.setDescription(teamDto.getDescription());

        teamRepository.save(newTeam);
    }

    public void deleteTeam(Long id){teamRepository.deleteById(id);}

    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(new Team());
    }

    public TeamDto getTeamDto(Long id) {
        Team team = teamRepository.findById(id).orElse(new Team());
        return new TeamDto(team.getId(),
                team.getTeam_name(),
                team.getDescription());
    }
}
