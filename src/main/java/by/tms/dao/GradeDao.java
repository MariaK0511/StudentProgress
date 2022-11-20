package by.tms.dao;

import by.tms.dto.GradeDto;
import by.tms.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
public class GradeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private TeacherDao teacherDao;

    private GradeDto gradeDto;

    @Transactional
    public void save(GradeDto gradeDto) {
        Session session = sessionFactory.getCurrentSession();
        Grade grade = new Grade();
        grade.setGrade(gradeDto.getGrade());
        session.save(grade);
        //  gradeDto.setId(grade.getId());
    }

    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Grade.class, id));
    }

    @Transactional
    public void createGrade(GradeDto gradeDto) {
        Session session = sessionFactory.getCurrentSession();
        Grade grade = new Grade();
        grade.setGrade(gradeDto.getGrade());
        grade.setLesson(gradeDto.getLesson());
        Student student = studentDao.findStudent(gradeDto.getStudentName(),gradeDto.getStudentSurname());
        grade.setStudent(student);
        Subject subject = subjectDao.findSubjectByNameOfSubject(gradeDto.getSubjectName());
        grade.setSubject(subject);
        Teacher teacher = teacherDao.findById(gradeDto.getTeacherId());
        grade.setTeacher(teacher);
        session.save(grade);
    }


    @Transactional(readOnly = true)
    public List<Grade> findGradesByStudentIdAndSubjectId(long studentId, long subjectId) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Grade where student.id = :studentId and subject.id =: subjectId",
                        Grade.class)
                .setParameter("studentId", studentId)
                .setParameter("subjectId", subjectId)
                .getResultList();
    }

//    @Transactional
//    public void addGradeToStudent(GradeDto gradeDto, long studentId, long subjectId) {
//        Session session = sessionFactory.getCurrentSession();
//        Grade gradeByNameOfGrade = findGradeByNameOfGrade(gradeDto);
//        Student student = studentDao.findById(studentId);
//        Subject subject = subjectDao.findById(subjectId);
//        gradeByNameOfGrade.setStudent(student);
//        gradeByNameOfGrade.setSubject(subject);
//        session.save(gradeByNameOfGrade);
//    }


    @Transactional(readOnly = true)
    public Grade findGradeByNameOfGrade(GradeDto gradeDto) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Grade where grade  = :grade",
                Grade.class).setParameter("grade", gradeDto.getGrade()).getSingleResult();
    }
}