package itlu.itlu.controller;

import itlu.itlu.dto.*;
import itlu.itlu.dto.MeetsStatus;
import itlu.itlu.dto.ProjectStatus;
import itlu.itlu.model.Customer;
import itlu.itlu.model.Team;
import itlu.itlu.service.CustomerService;
import itlu.itlu.service.MeetsService;
import itlu.itlu.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MeetsController {

    private final MeetsService meetsService;
    private final CustomerService customerService;
    private final TeamService teamService;

    public MeetsController(MeetsService meetsService, CustomerService customerService, TeamService teamService) {
        this.meetsService = meetsService;
        this.customerService = customerService;
        this.teamService = teamService;
    }

    @GetMapping("allMeets")
    public String getAllMeets(Model model){
        List<MeetsDto> allMeets = meetsService.getAllMeets();
        model.addAttribute("meets", allMeets);
        return "allMeets";
    }

    @GetMapping(path = "/{id}/deleteMeet")
    public String deleteDoctor(@PathVariable Long id) {
        if(meetsService.checkStatus(id)){
            return "redirect:/canNotDeleteTeam/CAN NOT DELETE THIS MEET !!!";
        }
        meetsService.deleteMeets(id);
        return "redirect:/allMeets";
    }

    @GetMapping("/addMeets")
    public String showCreateForm(Model model) {
        CreateMeetsDto createMeetsDto = new CreateMeetsDto();
        List<Team> teams = teamService.findAll();
        List<Customer> customers = customerService.findAll();
        model.addAttribute("meetsForm", createMeetsDto);
        model.addAttribute("statuses", Arrays.asList(MeetsStatus.values()));
        model.addAttribute("teams", teams);
        model.addAttribute("customers", customers);
        return "addMeets";
    }

    @PostMapping("addMeets")
    public String saveMeet(@ModelAttribute CreateMeetsDto createMeetsDto) {
        meetsService.saveMeets(createMeetsDto);
        return "redirect:/allMeets";
    }
}
