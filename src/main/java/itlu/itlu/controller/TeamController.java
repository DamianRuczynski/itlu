package itlu.itlu.controller;

import itlu.itlu.dto.TeamDto;
import itlu.itlu.service.TeamService;
import itlu.itlu.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TeamController {

    private final TeamService teamService;
    private final WorkerService workerService;

    public TeamController(TeamService teamService, WorkerService workerService) {
        this.teamService = teamService;
        this.workerService = workerService;
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


    @GetMapping("/{id}/teamDetails")
    public String showOne(@PathVariable Long id, Model model) {
        model.addAttribute("team", teamService.findById(id));
        model.addAttribute("workers", workerService.findAllWorkersToTeam(id));
        return "teamDetails";
    }

    @GetMapping(path = "/{id}/{teamId}/deleteWorkerFromTeam")
    public String deleteWorkerFromTeam(@PathVariable Long id,
                                       @PathVariable Long teamId) {
        workerService.deleteWorkerFromTeam(id);
        return "redirect:/"+teamId+"/teamDetails";
    }

}
