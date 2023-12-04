package com.hexagonal.tasks.infrastructure.entities;

import java.time.LocalDateTime;

import com.hexagonal.tasks.domain.models.Task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tareas")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tittle;
    private String description;
    private LocalDateTime creationDate;
    private boolean completed;

    public TaskEntity(Long id, String tittle, String description, LocalDateTime creationDateTime, boolean completed) {
        this.id = id;
        this.tittle = tittle;
        this.description = description;
        this.creationDate = creationDateTime;
        this.completed = completed;
    }

    public TaskEntity() {
    }

    public static TaskEntity fromDomainModel(Task task) {
        return new TaskEntity(task.getId(), task.getTitle(), task.getDescription(), task.getCreationDate(),
                task.getCompleted());
    }

    public Task toDomainModel(){
        return new Task(id, tittle, description, creationDate, completed);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreatioDate(LocalDateTime creatioDate) {
        this.creationDate = creatioDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

}
