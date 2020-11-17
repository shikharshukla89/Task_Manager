package com.example.sample_app.repository;

import com.example.sample_app.entity.Label;
import com.example.sample_app.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findByPriorityIn(List<String> priority);

    List<Task> findByDateAndLabel(Date date, Label label);

    List<Task> findByDateBetween(Date fromDate, Date toDate);
}
