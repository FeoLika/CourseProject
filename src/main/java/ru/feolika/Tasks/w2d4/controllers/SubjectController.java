package ru.feolika.Tasks.w2d4.controllers;

import ru.feolika.Tasks.w2d4.entityDAO.SubjectDAO;
import ru.feolika.Tasks.w2d4.models.Subject;

import java.util.List;

public class SubjectController {
    private SubjectDAO subjectDAO;

    public SubjectController(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public void saveSubject(Subject subject) {
        subjectDAO.save(subject);
    }

    public void updateSubject(Subject subject) {
        subjectDAO.update(subject);
    }

    public void deleteSubject(Long id) {
        Subject subject = subjectDAO.findById(id);
        if (subject != null) {
            subjectDAO.delete(id);
        }
    }

    public List<Subject> findAllSubjects() {
        return subjectDAO.findAll();
    }

    public Subject findSubjectById(Long id) {
        return subjectDAO.findById(id);
    }
}
