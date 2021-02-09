package itlu.itlu.controller;

import itlu.itlu.dto.WorkerDto;
import itlu.itlu.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("allEmployees")
    public String showAllWorkers(Model model){
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

        System.out.println();
        workerService.saveWorker(form);

        return "redirect:/allEmployees";
    }

}
