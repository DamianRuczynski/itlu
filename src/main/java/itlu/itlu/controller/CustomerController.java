package itlu.itlu.controller;

import itlu.itlu.dto.CustomerDto;
import itlu.itlu.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("allCustomers")
    public String showAllCustomers(Model model){
        model.addAttribute("customers", customerService.findAll());
        return "allCustomers";
    }

    @GetMapping("/addCustomer")
    public String showCreateForm(Model model) {

        CustomerDto customerDto = new CustomerDto();
        model.addAttribute("customerForm", customerDto);

        return "addCustomer";
    }


    @PostMapping("addCustomer")
    public String saveBill(@ModelAttribute CustomerDto form) {
        customerService.saveCustomer(form);
        return "redirect:/allCustomers";
    }

    @GetMapping(path = "/{id}/deleteCustomer")
    public String deleteDoctor(@PathVariable Long id) {
        if(customerService.checkCustomerHasProject(id)){
            return "redirect:/canNotDeleteTeam";
        }
        customerService.deleteCustomer(id);
        return "redirect:/allCustomers";
    }
}
