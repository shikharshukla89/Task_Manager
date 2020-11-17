package com.example.sample_app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;


@Data
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String priority;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne(optional = false)
    private Label label;

    @Transient
    private int labelId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                labelId == task.labelId &&
                Objects.equals(name, task.name) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, priority, date, labelId);
    }
    @Override
    public String toString() {
        return "TaskManagement{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priority='" + priority + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

}
