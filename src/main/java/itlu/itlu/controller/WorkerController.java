package itlu.itlu.controller;

import itlu.itlu.service.WorkerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WorkerController {

    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @GetMapping("allBills")
    public String showAllWorkers(Model model){
        model.addAttribute("workers", workerService.findAll());
        return "allBills";
    }
}
