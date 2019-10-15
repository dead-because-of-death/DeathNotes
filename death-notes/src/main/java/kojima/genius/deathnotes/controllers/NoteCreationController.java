package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.entities.Note;
import kojima.genius.deathnotes.entities.User;
import kojima.genius.deathnotes.repositories.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class NoteCreationController {

    @Autowired
    NoteRepository noteRep;

    @GetMapping("/createnote")
    String getPage(HttpServletRequest request, Model userData) {
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if(session != null && session.getAttribute("user") != null) {
            userData.addAttribute("username", user.getUsername());
            userData.addAttribute("logged", true);
        }else {
            userData.addAttribute("logged", false);
        }
        return "/createnote";
    }

    @PostMapping("/createnote")
    String writeNote(Note note) {
        noteRep.save(note);
        return "redirect:/";
    }
}
