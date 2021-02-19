package itlu.itlu.controller;

import itlu.itlu.dto.workerAndTeamId;
import itlu.itlu.dto.WorkerDto;
import itlu.itlu.model.Worker;
import itlu.itlu.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("allEmployees")
    public String showAllWorkers(Model model) {
        model.addAttribute("workers", workerService.findAll());
        return "allEmployees";
    }

    @GetMapping("/addEmployee")
    public String showCreateForm(Model model) {
        WorkerDto workerDto = new WorkerDto();
        model.addAttribute("workerForm", workerDto);
        return "addEmployee";
    }

    @PostMapping("addEmployee")
    public String saveBill(@ModelAttribute WorkerDto form) {
        workerService.saveWorker(form);
        return "redirect:/allEmployees";
    }

    @GetMapping(path = "/{id}/deleteWorker")
    public String deleteDoctor(@PathVariable Long id) {
        if(workerService.checkWorkerHasTeam(id)){
            return "redirect:/canNotDeleteTeam/CAN NOT DELETE THIS EMPLOYEE !!!!!!";
        }
        workerService.deleteWorker(id);
        return "redirect:/allEmployees";
    }

    @GetMapping(path = "/{teamId}/addEmployeeToTeam")
    public String addEmployeeTotTeam(Model model, @PathVariable Long teamId) {
        List<Worker> workerList = workerService.findNotAssignmentEmployees();
        workerAndTeamId workerAndTeamId = new workerAndTeamId(null, teamId);
        model.addAttribute("teamId", teamId);
        model.addAttribute("att", workerAndTeamId);
        model.addAttribute("workers", workerList);
        return "addEmloyeeToTeam";
    }

    @PostMapping(path = "addEmployeeToTeam")
    public String updateEmployee(@ModelAttribute workerAndTeamId form) {
        workerService.updateWorker(form.getWorkerId(), form.getTeamId());
        return "redirect:/" + form.getTeamId() + "/teamDetails";
    }
}
