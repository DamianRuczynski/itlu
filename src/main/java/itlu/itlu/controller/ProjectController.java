package itlu.itlu.controller;

import itlu.itlu.dto.CreateProjectDto;
import itlu.itlu.dto.ProjectDto;
import itlu.itlu.model.Customer;
import itlu.itlu.model.Team;
import itlu.itlu.service.CustomerService;
import itlu.itlu.service.ProjectService;
import itlu.itlu.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final CustomerService customerService;
    private final TeamService teamService;

    public ProjectController(ProjectService projectService, CustomerService customerService, TeamService teamService) {
        this.projectService = projectService;
        this.customerService = customerService;
        this.teamService = teamService;
    }

    @GetMapping("allProjects")
    public String getAllProjects(Model model){
        List<ProjectDto> allProjects = projectService.getAllProjects();
        model.addAttribute("projects", allProjects);
        return "allProjects";
    }

    @GetMapping(path = "/{id}/deleteProject")
    public String deleteDoctor(@PathVariable Long id) {
        System.out.println();
        projectService.deleteProject(id);
        return "redirect:/allProjects";
    }

    @GetMapping("/addProject")
    public String showCreateForm(Model model) {
        CreateProjectDto createProjectDto = new CreateProjectDto();
        List<Team> teams = teamService.findAll();
        List<Customer> customers = customerService.findAll();
        model.addAttribute("projectForm", createProjectDto);
        model.addAttribute("teams", teams);
        model.addAttribute("customers", customers);
        return "addProject";
    }

    @PostMapping("addProject")
    public String saveBill(@ModelAttribute CreateProjectDto createProjectDto) {
        projectService.saveProject(createProjectDto);
        return "redirect:/allProjects";
    }



}
