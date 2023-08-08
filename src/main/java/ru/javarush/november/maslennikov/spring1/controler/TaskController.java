package ru.javarush.november.maslennikov.spring1.controler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javarush.november.maslennikov.spring1.domain.Task;
import ru.javarush.november.maslennikov.spring1.service.TaskService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String showTasks(Model model, @ModelAttribute("newTask") Task newTask,
                            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                            @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        model.addAttribute("tasks", taskService.getItems((page - 1) * limit, limit));
        model.addAttribute("currentPage", page);
        setPageButton(model, limit);
        return "html/allTasks";
    }

    @GetMapping("/{id}/edit")
    public String editTask(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("task", taskService.getTaskByID(id));
        return "html/editTask";
    }

    @PatchMapping("/{id}")
    public String updateTask(@ModelAttribute Task task) {
        taskService.updateTask(task);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("task") Task newTask) {
        taskService.createNewTask(newTask);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        taskService.deleteById(id);
        return "redirect:/";
    }

    private void setPageButton(Model model, int limit) {
        int totalPage = (int) Math.ceil(1.0 * taskService.getTotalCount() / limit);
        if (totalPage > 1) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }
}
