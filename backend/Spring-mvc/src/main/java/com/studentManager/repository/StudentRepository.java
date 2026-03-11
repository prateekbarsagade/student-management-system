package com.studentManager.repository;

import com.studentManager.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class StudentRepository {

    private final SessionFactory sessionFactory;

    public StudentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Student> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Student", Student.class).list();
    }

    public Optional<Student> findById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return Optional.ofNullable(session.get(Student.class, id));
    }

    @SuppressWarnings("deprecation")
	public Student save(Student student) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(student);
        return student;
    }

    public void delete(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Student student = session.get(Student.class, id);
        if (student != null) {
            session.remove(student);
        }
    }
}