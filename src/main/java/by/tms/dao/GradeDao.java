package by.tms.dao;

import by.tms.dto.GradeDto;
import by.tms.dto.StudentDto;
import by.tms.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class GradeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(GradeDto gradeDto) {
        Session session = sessionFactory.getCurrentSession();
        Grade grade = new Grade();
        grade.setGrade(gradeDto.getGrade());
        session.save(grade);
        gradeDto.setId(grade.getId());
    }

    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Grade.class, id));
    }

    @Transactional(readOnly = true)
    public Grade findGradeByStudentAndSubjectAndLesson(Student student, Subject subject, Lesson lesson) {
        Session session = sessionFactory.getCurrentSession();
        Grade grade = session.createQuery("from Grade  where student = :student and subject = :subject and lesson = :lesson",
                        Grade.class)
                .setParameter("student", student)
                .setParameter("subject", subject)
                .setParameter("lesson", lesson).getSingleResult();
        return grade;
    }

    @Transactional(readOnly = true)
    public List<Grade> findAllGradesByStudentAndSubject(Student student, Subject subject) {
        Session session = sessionFactory.getCurrentSession();
        List<Grade> grades = session.createQuery("from Grade  where student = :student and subject = :subject ",
                        Grade.class)
                .setParameter("student", student)
                .setParameter("subject", subject).getResultList();
        return grades;
    }


}
