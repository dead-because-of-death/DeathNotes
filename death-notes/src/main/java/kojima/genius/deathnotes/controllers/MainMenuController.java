package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.entities.Note;
import kojima.genius.deathnotes.entities.User;
import kojima.genius.deathnotes.repositories.NoteRepository;
import kojima.genius.deathnotes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

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

    @PostMapping("/note/{id}/delete")
    public String deleteNote(@PathVariable String id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = userRep.findByUsername( (String) session.getAttribute("user"));
        Note neededNote = user.getNotes().stream().filter(note -> {
            if(note.getId().equals(new Long(id))) return true;
            return false;
        }).findFirst().get();
        user.getNotes().remove(neededNote);
        userRep.save(user);
        noteRep.delete(neededNote);
        return "redirect:/";
    }
}
