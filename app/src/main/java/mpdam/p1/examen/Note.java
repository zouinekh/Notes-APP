package mpdam.p1.examen;

public class Note {
    private String title,note,cat;

    public Note(String title, String note, String cat) {
        this.title = title;
        this.note = note;
        this.cat = cat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }
}
