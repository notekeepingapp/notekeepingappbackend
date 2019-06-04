package com.notekeepingapp.NoteKeepingAppDemo.controller;

import com.notekeepingapp.NoteKeepingAppDemo.model.Note;
import com.notekeepingapp.NoteKeepingAppDemo.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class NoteController {

    @Autowired
    NoteService noteService;

    @GetMapping("/{name}/notes")
    public ResponseEntity<List<Note>> getNotes(@PathVariable String name) {
        return new ResponseEntity<>(noteService.getNotes(name), HttpStatus.OK);
    }

    @PostMapping("/{name}/notes")
    public ResponseEntity<Note> addNote(@PathVariable String name, @RequestBody Note note) {
        note.setUser(name);
        note.setCreatedAt(new Date());
        return new ResponseEntity<>(noteService.addNote(note), HttpStatus.OK);
    }

    @PostMapping("/{name}/notes/{id}")
    public ResponseEntity updateNote(@PathVariable String name, @PathVariable int id, @RequestBody Note note) {
        note.setId(id);
        note.setUser(name);
        return new ResponseEntity<>(noteService.updateNote(note), HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}/notes/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteNote(@PathVariable int id) {
        if ((noteService.isNoteExists(id))) {
            return new ResponseEntity<>(noteService.deleteNote(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
