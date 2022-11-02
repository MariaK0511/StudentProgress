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

    @Autowired
    private TeacherDao teacherDao;

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
    public List<SubjectDto> findSubjectsByTeacherId(long id) {
        Session session = sessionFactory.getCurrentSession();
        List<Subject> subjects = session.createQuery("from Subject where teacher.id = :teacherId", Subject.class)
                .setParameter("teacherId", id).getResultList();
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

    public Subject findSubjectByNameOfSubject(SubjectDto subjectDto) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Subject where name = :subjectName",
                Subject.class).setParameter("subjectName", subjectDto.getName()).getSingleResult();
    }

    @Transactional
    public void addSubjectToTeacher(SubjectDto subjectDto, long id) {
        Session session = sessionFactory.getCurrentSession();
        Subject subjectByNameOfSubject = findSubjectByNameOfSubject(subjectDto);
        Teacher teacher = teacherDao.findById(id);
        subjectByNameOfSubject.setTeacher(teacher);
        session.save(subjectByNameOfSubject);
    }


    @Transactional(readOnly = true)
    public List<Subject> findSubjectsOfStudent(Student student) {
        Session session = sessionFactory.getCurrentSession();
        List<Subject> subjects = session.createQuery("from Subject  where student = :student", Subject.class)
                .setParameter("student", student).getResultList();
        return subjects;
    }
}