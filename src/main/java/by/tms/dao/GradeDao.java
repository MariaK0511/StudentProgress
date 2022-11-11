package by.tms.dao;

import by.tms.dto.GradeDto;
import by.tms.dto.StudentDto;
import by.tms.dto.SubjectDto;
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

    @Transactional
    public void save(GradeDto gradeDto) {
        Session session = sessionFactory.getCurrentSession();
        Grade grade = new Grade();
        grade.setName(gradeDto.getName());
        session.save(grade);
        gradeDto.setId(grade.getId());
    }

    @Transactional
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.remove(session.get(Grade.class, id));
    }

    public GradeDto createGradeDto(Grade grade) {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setId(grade.getId());
        gradeDto.setName(grade.getName());
        return gradeDto;
    }

    public Grade findGradeByNameOfGrade(GradeDto gradeDto) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Grade where name  = :gradeName",
                Grade.class).setParameter("gradeName", gradeDto.getName()).getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<GradeDto> findGradesByStudentIdAndSubjectId(long studentId, long subjectId) {
        Session session = sessionFactory.getCurrentSession();
        List<Grade> grades = session.createQuery("from Grade where student.id = :studentId and subject.id =: subjectId",
                        Grade.class)
                .setParameter("studentId", studentId)
                .setParameter("subjectId", subjectId)
                .getResultList();
        List<GradeDto> gradeDtoList = new ArrayList<>();
        grades.forEach(grade -> gradeDtoList.add(createGradeDto(grade)));
        return gradeDtoList;
    }

    @Transactional
    public void addGradeToStudent(GradeDto gradeDto, long studentId, long subjectId) {
        Session session = sessionFactory.getCurrentSession();
        Grade gradeByNameOfGrade = findGradeByNameOfGrade(gradeDto);
        Student student = studentDao.findById(studentId);
        Subject subject = subjectDao.findById(subjectId);
        gradeByNameOfGrade.setStudent(student);
        gradeByNameOfGrade.setSubject(subject);
        session.save(gradeByNameOfGrade);
    }
}