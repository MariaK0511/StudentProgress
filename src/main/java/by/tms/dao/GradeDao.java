package by.tms.dao;

import by.tms.entity.Grade;
import by.tms.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class GradeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(Grade grade) {
        Session session = sessionFactory.getCurrentSession(); //generate new session
        session.save(grade);
        //  session.close();
    }


}
