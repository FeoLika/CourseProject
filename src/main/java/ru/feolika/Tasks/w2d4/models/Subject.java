package ru.feolika.Tasks.w2d4.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subjects")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String title;
    @Column
    private String description;
    @ManyToMany(mappedBy = "subjects", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();

    public Subject() {
    }

    public Subject(String title, String description, List<Student> students) {
        this.title = title;
        this.description = description;
        this.students = students;
    }

    public Long getID() {
        return id;
    }

    public void setID(Long id) {
        this.id = id;
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

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Subject subject = (Subject) obj;
        return id.equals(subject.id);
    }
}


