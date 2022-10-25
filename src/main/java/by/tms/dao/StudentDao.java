package by.tms.dao;

import by.tms.dto.StudentDto;
import by.tms.entity.Grade;
import by.tms.entity.Student;
import by.tms.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(StudentDto studentDto) {
        Session session = sessionFactory.getCurrentSession(); //generate new session
        Student student = new Student();
        student.setName(studentDto.getName());
        student.setSurname(studentDto.getSurname());
        session.save(student);
        studentDto.setId(student.getId());
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Student> students = session.createQuery("from Student ", Student.class).getResultList();
        return students;
    }

    @Transactional(readOnly = true)
    public Student findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.find(Student.class, id);
        return student;
    }
    @Transactional(readOnly = true)
    public Student show(long id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Student.class, id);
    }

    @Transactional
    public Student update(long id, Student student) {
        Session session = sessionFactory.getCurrentSession();
        Student editedStudent = session.find(Student.class, id);
        editedStudent.setName(student.getName());
        editedStudent.setSurname(student.getSurname());
        session.save(editedStudent);
        session.update(student);
        return editedStudent;
    }
    @Transactional
    public  void delete(long id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Student.class, id));
    }
}