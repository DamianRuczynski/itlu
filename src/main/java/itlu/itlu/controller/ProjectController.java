package itlu.itlu.controller;

import itlu.itlu.dto.CreateProjectDto;
import itlu.itlu.dto.ProjectDto;
import itlu.itlu.dto.TeamDto;
import itlu.itlu.model.Project;
import itlu.itlu.service.ProjectService;
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

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
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
        model.addAttribute("projectForm", createProjectDto);
        return "addProject";
    }

    @PostMapping("addProject")
    public String saveBill(@ModelAttribute CreateProjectDto createProjectDto) {
        projectService.saveProject(createProjectDto);
        return "redirect:/allProjects";
    }



}
