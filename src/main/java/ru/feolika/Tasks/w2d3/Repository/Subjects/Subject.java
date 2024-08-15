package ru.feolika.Tasks.w2d3.Repository.Subjects;

/**
 * Предмет
 */
public class Subject {
    private int ID;
    private String title;
    private String description;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Subject() {
    }

    public Subject(String title, String description) {
        this.title = title;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Subject{" +
               "ID=" + ID +
               ", title='" + title + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
