package kojima.genius.deathnotes.controllers;

import kojima.genius.deathnotes.entities.Note;
import kojima.genius.deathnotes.entities.User;
import kojima.genius.deathnotes.repositories.NoteRepository;
import kojima.genius.deathnotes.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class NoteMaintainingController {

    NoteRepository noteRep;

    UserRepository userRep;

    public NoteMaintainingController(NoteRepository noteRep, UserRepository userRep) {
        this.noteRep = noteRep;
        this.userRep = userRep;
    }

    @PostMapping("/note/{index}/delete")
    public String deleteNote(@PathVariable String index, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        User user = userRep.findByUsername( (String) session.getAttribute("user"));
        Note neededNote = user.getNotes().get(new Integer(index));
        user.getNotes().remove(neededNote);
        userRep.save(user);
        noteRep.delete(neededNote);
        return "redirect:/";
    }

    @GetMapping("/note/{index}/edit")
    public String editNotepage(@PathVariable String index, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        User user = userRep.findByUsername( (String) session.getAttribute("user"));
        model.addAttribute("note", user.getNotes().get(new Integer(index)));
        model.addAttribute("index",index);
        userRep.save(user);
        return "edit-note";
    }

    @PostMapping("/note/{index}/edit")
    public String editNote(@PathVariable String index, HttpServletRequest request, Note note) {
        HttpSession session = request.getSession(false);
        User user = userRep.findByUsername( (String) session.getAttribute("user"));
        user.getNotes().get(new Integer(index)).setText(note.getText());
        userRep.save(user);
        return "redirect:/";
    }
}
