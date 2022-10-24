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
    public void save(TeacherDto teacherDto){
        Session session = sessionFactory.getCurrentSession(); //generate new session
        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.getName());
        teacher.setSurname(teacherDto.getSurname());
        session.save(teacher);
        teacherDto.setId(teacher.getId());
    }

    @Transactional
    public Teacher show(long id){
        Session session = sessionFactory.getCurrentSession();
       return session.get(Teacher.class, id);
    }

    @Transactional(readOnly = true)
    public List<Teacher> findAll(){ // JPQL
        Session session = sessionFactory.getCurrentSession();
        List<Teacher> teachers = session.createQuery("from Teacher ", Teacher.class).getResultList();
        return teachers;
    }

    @Transactional(readOnly = true)
    public Teacher findById(long id){
        Session session = sessionFactory.getCurrentSession();
        Teacher teacher = session.find(Teacher.class, id);
        return teacher;
    }

    @Transactional
    public  Teacher edit(long id, Teacher teacher){
        Session session = sessionFactory.getCurrentSession();
        Teacher editedTeacher = session.find(Teacher.class, id);
        editedTeacher.setName(teacher.getName());
        editedTeacher.setSurname(teacher.getSurname());
        editedTeacher.setSubjects(teacher.getSubjects());
        session.save(editedTeacher);
        return editedTeacher;
    }
    @Transactional
    public  void delete(long id){
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Teacher.class, id));
    }

//    @Transactional(readOnly = true)
//    public Teacher findBySurname(String surname) {
//        Session session = sessionFactory.getCurrentSession();
//        Teacher teacher = session.createQuery("from Teacher  where surname = :teacher", Teacher.class)
//                .setParameter("teacher", surname).getSingleResult();
//        return teacher;
//    }
}
