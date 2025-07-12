package sample.todoapp.Model;

import java.sql.Timestamp;

public class Task {
    private Timestamp datecreated;
    private String description;
    private String task;
    private int userId;

    private int taskId;
    public Task() {
    }

    public Task(Timestamp datecreated, String description, String task) {
        this.datecreated = datecreated;
        this.description = description;
        this.task = task;
    }

    public Timestamp getDatecreated() {
        return datecreated;
    }

    public String getDescription() {
        return description;
    }

    public String getTask() {
        return task;
    }

    public void setDatecreated(Timestamp datecreated) {
        this.datecreated = datecreated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
