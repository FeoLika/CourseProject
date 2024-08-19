package ru.feolika.Tasks.w2d4.controllers;

import ru.feolika.Tasks.w2d4.entityDAO.StudentDAO;
import ru.feolika.Tasks.w2d4.models.Student;

import java.util.List;

public class StudentController {
    private StudentDAO studentDAO;

    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public void saveStudent(Student student) {
        studentDAO.save(student);
    }

    public void updateStudent(Student student) {
        studentDAO.update(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentDAO.findById(id);
        if (student != null) {
            studentDAO.delete(id);
        }
    }

    public List<Student> findAllStudents() {
        return studentDAO.findAll();
    }

    public Student findStudentById(Long id) {
        return studentDAO.findById(id);
    }
}
