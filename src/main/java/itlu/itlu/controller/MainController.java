package itlu.itlu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = {"/canNotDeleteTeam/{error}"}, method = RequestMethod.GET)
    public String canNotDeleteTeam(@PathVariable String error, Model model) {
        model.addAttribute("error", error);
        return "canNotDeleteTeam";
    }
}
