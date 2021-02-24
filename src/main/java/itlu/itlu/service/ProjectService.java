package itlu.itlu.service;

import itlu.itlu.dto.CreateProjectDto;
import itlu.itlu.dto.ProjectDto;
import itlu.itlu.model.Project;
import itlu.itlu.repository.ProjectDtoRepository;
import itlu.itlu.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectDtoRepository projectDtoRepository;


    public ProjectService(ProjectRepository projectRepository, ProjectDtoRepository projectDtoRepository) {
        this.projectRepository = projectRepository;
        this.projectDtoRepository = projectDtoRepository;
    }

    public List<ProjectDto> getAllProjects() {
        return projectDtoRepository.getAllProjectWithName();
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public void saveProject(CreateProjectDto createProjectDto) {

        Project newProject = new Project();
        Optional.ofNullable(createProjectDto.getId()).ifPresent(id -> newProject.setId(id));
        newProject.setProject_name(createProjectDto.getProject_name());
        newProject.setProject_purpose(createProjectDto.getProject_purpose());
        newProject.setId_team(createProjectDto.getId_team());
        newProject.setId_customer(createProjectDto.getId_customer());
        newProject.setProject_status(createProjectDto.getProject_status());
        newProject.setProject_price(createProjectDto.getProject_price());

        projectRepository.save(newProject);
    }

    public boolean checkHasTeamProject(Long id) {
        return projectRepository.findProjectByTeamId(id).size() > 0;
    }

    public boolean checkStatus(Long id) {
        return projectRepository.findById(id).orElse(new Project()).getProject_status() >= 1;
    }

    public CreateProjectDto findProjectDto(Long id) {
        Project project = projectRepository.findById(id).orElse(new Project());
        return new CreateProjectDto(
                project.getId(),
                project.getProject_name(),
                project.getProject_purpose(),
                project.getId_team(),
                project.getId_customer(),
                project.getProject_status(),
                project.getProject_price());
    }
}
