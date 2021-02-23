package itlu.itlu.controller;

import itlu.itlu.dto.CreateProjectDto;
import itlu.itlu.dto.ProjectDto;
import itlu.itlu.dto.ProjectStatus;
import itlu.itlu.model.Customer;
import itlu.itlu.model.Team;
import itlu.itlu.service.CustomerService;
import itlu.itlu.service.ProjectService;
import itlu.itlu.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        if(projectService.checkStatus(id)){
            return "redirect:/canNotDeleteTeam/CAN NOT DELETE THIS PROJECTS !!!";
        }
        projectService.deleteProject(id);
        return "redirect:/allProjects";
    }

    @GetMapping("/addProject")
    public String showCreateForm(Model model) {
        CreateProjectDto createProjectDto = new CreateProjectDto();
        List<Team> teams = teamService.findAll();
        List<Customer> customers = customerService.findAll();
        model.addAttribute("projectForm", createProjectDto);
        model.addAttribute("statuses", Arrays.asList(ProjectStatus.values()));
        model.addAttribute("teams", teams);
        model.addAttribute("customers", customers);
        return "addProject";
    }

    @PostMapping("addProject")
    public String saveBill(@ModelAttribute CreateProjectDto createProjectDto) {
        projectService.saveProject(createProjectDto);
        return "redirect:/allProjects";
    }

    @GetMapping("/{id}/editProject")
    public String editProject(@PathVariable Long id, Model model){
        CreateProjectDto projectDto = projectService.findProjectDto(id);
        model.addAttribute("projectForm", projectDto);
        model.addAttribute("status",
                Arrays.asList(
                        ProjectStatus.values())
                        .stream()
                        .filter(e -> e.getValue() >= projectDto.getProject_status())
                        .collect(Collectors.toList()));
        return "editProject";
    }

    @PostMapping("editProject")
    public String saveEditedProject(@ModelAttribute CreateProjectDto editedProjectDto){
        projectService.saveProject(editedProjectDto);
        return "redirect:/allProjects";
    }





}
