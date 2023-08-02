package ru.javarush.november.maslennikov.spring1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javarush.november.maslennikov.spring1.dao.TasksDAO;
import ru.javarush.november.maslennikov.spring1.domain.Task;

import java.util.List;

@Component
public class TaskService {

    private final TasksDAO<Task> tasksDAO;

    @Autowired
    public TaskService(TasksDAO<Task> tasksDAO) {
        this.tasksDAO = tasksDAO;
    }

    public List<Task> getItems(int offset, int count) {
        return tasksDAO.getItems(offset, count);
    }

    public int getTotalCount() {
        return tasksDAO.getTotalCount();
    }

    public void createNewTask(Task task) {
        tasksDAO.save(Task.builder().description(task.getDescription()).status(task.getStatus()).build());
    }

    public Task getTaskByID(Integer id) {
        return tasksDAO.getById(id);
    }

    public void updateTask(Task task) {
        tasksDAO.update(task);
    }

    public void deleteById(int taskId) {
        Task task = tasksDAO.getById(taskId);
        tasksDAO.delete(task);
    }
}
