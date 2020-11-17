package com.example.sample_app.service;

import com.example.sample_app.entity.Label;
import com.example.sample_app.entity.Task;
import com.example.sample_app.repository.LabelRepository;
import com.example.sample_app.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaskService {
    private final TaskRepository taskRepository;
    private final LabelRepository labelRepository;

    public TaskService(TaskRepository taskRepository, LabelRepository labelRepository) {
        this.taskRepository = taskRepository;
        this.labelRepository = labelRepository;
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Optional<Task> findOne(@PathVariable("id") int id){
        return taskRepository.findById(id);
    }

    public Task save(@RequestBody Task task){
        Label label = labelRepository.findById(task.getLabelId()).orElse(null);
        task.setLabel(label);
        return taskRepository.save(task);
    }

    public Task update(Task task){
        Task mTask = taskRepository.getOne(task.getId());
        mTask.setName(task.getName());
        mTask.setPriority(task.getPriority());
        mTask.setDate(task.getDate());
        Label label = labelRepository.findById(task.getLabelId()).orElse(null);
        mTask.setLabel(label);

        return mTask;
    }

    public void deleteOne(int id){
        taskRepository.deleteById(id);
    }

    public List<Task> getByPriority(List<String> priorityList){
        return taskRepository.findByPriorityIn(priorityList);
    }

    public List<Task> getByDateAndLabel(
            Date date,
//            String date,
            int labelId){
//        List<Task> taskList = taskRepository.findByDateAndLabel(date, labelId);
        Label label = labelRepository.findById(labelId).orElse(null);
        List<Task> taskList = taskRepository.findByDateAndLabel(date, label);
        return taskList;
    }

    public List<Task> getTwoDaysTask(Date fromDate, Date toDate){
        List<Task> taskList = taskRepository.findByDateBetween(fromDate, toDate);
        return taskList;
    }
}
