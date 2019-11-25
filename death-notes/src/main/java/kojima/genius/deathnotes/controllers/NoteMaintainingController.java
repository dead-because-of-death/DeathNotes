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

    @GetMapping("/note/{id}/edit")
    public String editNotepage(@PathVariable String id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);
        User user = userRep.findByUsername( (String) session.getAttribute("user"));
        Note neededNote = user.getNotes().stream().filter(note -> {
            if(note.getId().equals(new Long(id))) return true;
            return false;
        }).findFirst().get();
        model.addAttribute("note", neededNote);
        userRep.save(user);
        return "edit-note";
    }

    @PostMapping("/note/{id}/edit")
    public String editNote(@PathVariable String id, HttpServletRequest request, Note note) {
        HttpSession session = request.getSession(false);
        User user = userRep.findByUsername( (String) session.getAttribute("user"));
        Note neededNote = user.getNotes().stream().filter(localNote -> {
            if(localNote.getId().equals(new Long(id))) return true;
            return false;
        }).findFirst().get();
        user.getNotes().remove(neededNote);
        neededNote.setText(note.getText());
        user.getNotes().add(neededNote);
        userRep.save(user);
        return "redirect:/";
    }
}
