package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.entities.User;
import kojima.genius.deathnotes.repositories.NoteRepository;
import kojima.genius.deathnotes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainMenuController {

    @Autowired
    NoteRepository noteRep;

    @Autowired
    UserRepository userRep;

    @GetMapping("/")
    public String getPage(HttpServletRequest request, Model userDataAndNotes) {
        HttpSession session = request.getSession(false);
        User user;

        if(session != null && session.getAttribute("user") != null) {
            user = userRep.findByUsername( (String) session.getAttribute("user"));
            userDataAndNotes.addAttribute("username", user.getUsername());
            userDataAndNotes.addAttribute("logged", true);
            if (!user.getNotes().isEmpty()) {
                userDataAndNotes.addAttribute("notes", user.getNotes());
                userDataAndNotes.addAttribute("hasmessage", true);
            }else {
                userDataAndNotes.addAttribute("hasmessage", false);
            }
        }else {
            userDataAndNotes.addAttribute("logged", false);
            userDataAndNotes.addAttribute("hasmessage", false);
        }

        return "menu";
    }
}
