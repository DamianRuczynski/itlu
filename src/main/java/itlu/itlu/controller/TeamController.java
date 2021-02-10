package itlu.itlu.controller;

import itlu.itlu.dto.TeamDto;
import itlu.itlu.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("allTeams")
    public String showAllTeams(Model model) {
        model.addAttribute("teams", teamService.findAll());
        return "allTeams";
    }

    @GetMapping("/addTeam")
    public String showCreateForm(Model model) {
        TeamDto teamDto = new TeamDto();
        model.addAttribute("teamForm", teamDto);
        return "addTeam";
    }

    @PostMapping("addTeam")
    public String saveBill(@ModelAttribute TeamDto form) {
        teamService.saveTeam(form);
        return "redirect:/allTeams";
    }

    @GetMapping(path = "/{id}/deleteTeam")
    public String deleteDoctor(@PathVariable Long id) {
        System.out.println();
        teamService.deleteTeam(id);
        return "redirect:/allTeams";
    }

}
