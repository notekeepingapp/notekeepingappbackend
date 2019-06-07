package com.notekeepingapp.NoteKeepingAppDemo.DAO;

import com.notekeepingapp.NoteKeepingAppDemo.model.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@PropertySource("application-test.properties")
@RunWith(SpringRunner.class)
@DataJpaTest
public class NoteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NoteRepository noteRepository;

    Note mockNote = new Note(1, "admin", "noteTitle", "noteContent", new Date());

    @Before
    public void setUp() {
        this.entityManager.merge(mockNote);
    }

    @Test
    public void should_ReturnNotesList_For_FindNoteByuser() {
        List<Note> notes = noteRepository.findNoteByUser("admin");
        assertNotNull(notes);
        assertEquals(1, notes.size());
    }

    @Test
    public void should_ReturnNote_For_FindNoteById() {
        Note note = noteRepository.findNoteById(1);
        assertNotNull(note);
        assertTrue(note.toString().contains("admin"));
    }

    @Test
    public void should_UpdateNote_For_UpdatenoteTitle() {
        noteRepository.save(mockNote);
        noteRepository.updatenoteContent(1, "update");
        Note updatedNote = noteRepository.findNoteById(1);
        assertNotNull(updatedNote);
//        assertEquals("updated", updatedNote.getNoteTitle());
    }

    @Test
    public void shouldVerify_DeleteNote() {
        noteRepository.save(mockNote);
        noteRepository.deleteById(1);
        Note deletedNote = noteRepository.findNoteById(1);
        assertNull(deletedNote);
    }
}
