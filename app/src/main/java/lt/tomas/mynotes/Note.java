package lt.tomas.mynotes;

import androidx.annotation.NonNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Note {
    private int id;
    private String name;
    private String noteText;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:ss");

    public Note(int id, String name, String noteText) {
        this.id = id;
        this.name = name;
        this.noteText = noteText;
        this.creationDate = LocalDateTime.now();
        this.updateDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNoteText() {
        return noteText;
    }
    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
    public LocalDateTime getUpdateDate() {
        return updateDate;
    }
    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
    @NonNull
    @Override
    public String toString() {
        return String.format(
                "id:%s | %s\n%s\n\t%s\n\t%s",
                this.id,
                this.name,
                this.noteText,
                this.creationDate.format(formatter),
                this.updateDate.format(formatter)
        );
    }
}