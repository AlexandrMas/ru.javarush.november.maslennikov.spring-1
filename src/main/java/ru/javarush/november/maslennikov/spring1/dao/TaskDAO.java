package ru.javarush.november.maslennikov.spring1.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.november.maslennikov.spring1.domain.Task;

import java.util.List;

@Repository
public class TaskDAO implements TasksDAO<Task> {

    private final SessionFactory sessionFactory;

    @Autowired
    public TaskDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Task> getAll() {
        return getSession().createQuery("from Task", Task.class).list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> getItems(int offset, int count) {
        return getSession().createQuery("from Task", Task.class)
                .setFirstResult(offset)
                .setMaxResults(count)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public Task getById(int taskId) {
        return getSession().get(Task.class, taskId);
    }

    @Override
    @Transactional
    public void save(Task task) {
        getSession().persist(task);
    }

    @Override
    @Transactional
    public void update(Task task) {
        getSession().merge(task);
    }

    @Override
    @Transactional
    public void delete(Task task) {
        getSession().remove(task);
    }

    @Override
    @Transactional
    public void deleteById(int taskId) {
        delete(getById(taskId));
    }

    @Override
    @Transactional
    public int getTotalCount() {
        String hql = "select count(t) from Task t";
        return Math.toIntExact(getSession().createQuery(hql, Long.class).getSingleResult());
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
