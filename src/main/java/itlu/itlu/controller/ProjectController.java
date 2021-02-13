package itlu.itlu.controller;

import itlu.itlu.dto.ProjectDto;
import itlu.itlu.model.Project;
import itlu.itlu.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("allProjects")
    public String getAllProjects(Model model){
        List<Project> allProjects = projectService.getAllProjects();
        model.addAttribute("projects", allProjects);
        return "allProjects";
    }
}
