package lt.tomas.mynotes;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static List<Note> notes = new ArrayList<>();
    public static void generateNoteList() {
        for (int i = 1; i <= 20; i++) {
            notes.add(
                    new Note(
                            i,
                            "Name_" + i,
                            "Some note text_" + i
                    )
            );
        }
    }
    public static List<Note> getNotes() {
        return notes;
    }

    public static void addNote(Note note) {
        if (note != null) {
            notes.add(note);
        }
    }

    public static void removeNote(int index) {
        if (index < notes.size()) {
            notes.remove(index);
        }
    }
}