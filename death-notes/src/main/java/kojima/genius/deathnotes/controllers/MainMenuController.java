package kojima.genius.deathnotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainMenuController {

    @GetMapping("/")
    public String getPage(HttpServletRequest request, Model userData) {
        HttpSession session = request.getSession(false);
        if(session != null && session.getAttribute("user") != null) {
            userData.addAttribute("username", session.getAttribute("user"));
            userData.addAttribute("logged", true);
        }else {
            userData.addAttribute("logged", false);
        }
        return "menu";
    }
}
