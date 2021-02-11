package itlu.itlu.service;


import itlu.itlu.dto.TeamDto;
import itlu.itlu.model.Team;
import itlu.itlu.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        newTeam.setTeam_name(teamDto.getTeam_name());
        newTeam.setDescription(teamDto.getDescription());

        teamRepository.save(newTeam);
    }

    public void deleteTeam(Long id){teamRepository.deleteById(id);}

    public Team findById(Long id) {
        return teamRepository.findById(id).orElse(new Team());
    }
}
