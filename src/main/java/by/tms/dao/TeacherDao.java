package by.tms.dao;

import by.tms.dto.TeacherDto;
import by.tms.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class TeacherDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(TeacherDto teacherDto) {
        Session session = sessionFactory.getCurrentSession();
        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.getName());
        teacher.setSurname(teacherDto.getSurname());
        session.save(teacher);
        teacherDto.setId(teacher.getId());
    }

    @Transactional(readOnly = true)
    public List<Teacher> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Teacher> teachers = session.createQuery("from Teacher ", Teacher.class).getResultList();
        return teachers;
    }

    @Transactional(readOnly = true)
    public Teacher findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Teacher teacher = session.find(Teacher.class, id);
        return teacher;
    }

    @Transactional
    public Teacher edit(TeacherDto teacherDto) {
        Session session = sessionFactory.getCurrentSession();
        Teacher editedTeacher = session.find(Teacher.class, teacherDto.getId());
        editedTeacher.setName(teacherDto.getName());
        editedTeacher.setSurname(teacherDto.getSurname());
        // editedTeacher.setSubjects(teacherDto.getSubjects());
        session.save(editedTeacher);
        return editedTeacher;
    }

    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Teacher.class, id));
    }

//    @Transactional(readOnly = true)
//    public Teacher findBySurname(String surname) {
//        Session session = sessionFactory.getCurrentSession();
//        Teacher teacher = session.createQuery("from Teacher  where surname = :teacherSurname", Teacher.class)
//                .setParameter("teacherSurname", surname).getSingleResult();
//        return teacher;
//    }
}