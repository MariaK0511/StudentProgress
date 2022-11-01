package by.tms.dao;


import by.tms.dto.SubjectDto;
import by.tms.dto.TeacherDto;
import by.tms.entity.Lesson;
import by.tms.entity.Student;
import by.tms.entity.Subject;
import by.tms.entity.Teacher;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubjectDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(SubjectDto subjectDto) {
        Session session = sessionFactory.getCurrentSession();
        Subject subject = new Subject();
        subject.setName(subjectDto.getName());
        session.save(subject);
        subjectDto.setId(subject.getId());
    }

    @Transactional(readOnly = true)
    public List<Subject> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List<Subject> subjects = session.createQuery("from Subject ", Subject.class).getResultList();
        return subjects;
    }

    @Transactional(readOnly = true)
    public Subject findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        Subject subject = session.find(Subject.class, id);
        return subject;
    }

    @Transactional
    public Subject edit(SubjectDto subjectDto) {
        Session session = sessionFactory.getCurrentSession();
        Subject editedSubject = session.find(Subject.class, subjectDto.getId());
        editedSubject.setName(subjectDto.getName());
        session.save(editedSubject);
        return editedSubject;
    }

    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Subject.class, id));
    }

    @Transactional(readOnly = true)
    public List<SubjectDto> findSubjectByTeacher(TeacherDto teacherDto) {
        Session session = sessionFactory.getCurrentSession();
        List<Subject> subjects = session.createQuery("from Subject  where teacher.id = :teacherId", Subject.class)
                .setParameter("teacherId", teacherDto.getId()).getResultList();
        List<SubjectDto> subjectDtoList = new ArrayList<>();
        subjects.forEach(subject -> subjectDtoList.add(createSubjectDto(subject)));
        return subjectDtoList;
    }

    public SubjectDto createSubjectDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        return subjectDto;
    }

    @Transactional(readOnly = true)
    public List<Subject> findSubjectsOfStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        List<Subject> subjects = session.createQuery("from Subject  where student = :student", Subject.class)
                .setParameter("student", student).getResultList();
        return subjects;
    }
}