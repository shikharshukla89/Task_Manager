package com.example.sample_app.RestController;

import com.example.sample_app.entity.Task;
import com.example.sample_app.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
public class TaskRestController {
    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    //List all tasks
    @GetMapping
    public List<Task> findAll(){

        List<Task> taskList = taskService.findAll();
        taskList.forEach(this::sanitize);
        return taskList;
    }

    //Get particular task of specific id
    @GetMapping("/{taskId}")
    public Optional<Task> findOne(@PathVariable("taskId") Integer id){

        Optional<Task> task = taskService.findOne(id);

        return task;
    }

    //Insert a new task
    @PostMapping
    public Task save(@RequestBody Task task){
        Task mTask = taskService.save(task);
        return sanitize(mTask);
    }

    //Update a task
    @PutMapping("/{taskId}")
    public Task update(@PathVariable("taskId") Integer id, @RequestBody Task task){
        task.setId(id);
        Task mTask = taskService.update(task);

        return sanitize(mTask);
    }

//    @DeleteMapping
//    public void deleteTasks(){
//
//        taskRepository.deleteAll();
//    }

    //Delete a task
    @DeleteMapping("/{taskId}")
    public void deleteOne(@PathVariable("taskId") int id){

        taskService.deleteOne(id);
    }

    //List tasks of the given priorities
    @GetMapping("/q")
    public List<Task> getByPriority(@RequestParam("priority") List<String> priorityList){
        List<Task> taskList = taskService.getByPriority(priorityList);
        taskList.forEach(this::sanitize);
        return taskList;
    }

    //List Tasks of specific date and label
    @GetMapping("/q1")
    public List<Task> getByDateAndLabel(@RequestParam(name = "date")
                                            @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                    Date date,
                                        @RequestParam("label") int labelId) {
        List<Task> taskList = taskService.getByDateAndLabel(date, labelId);
        taskList.forEach(this::sanitize);
        return taskList;
    }

    //List tasks between the given dates
    @GetMapping("/q2")
    public List<Task> getTwoDaysTask(
            @RequestParam("from")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @RequestParam("to")
            @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {

        List<Task> taskList = taskService.getTwoDaysTask(fromDate, toDate);
        taskList.forEach(this::sanitize);
           return taskList;
    }

    //method to remove infinite chaining
    private Task sanitize(Task mTask){
        mTask.getLabel().setTasks(null);
        return mTask;
    }
}
