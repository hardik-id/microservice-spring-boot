package hello.model;

import org.springframework.data.annotation.Id;

public class TaskRequest {

    public String taskDetails;

    public TaskRequest() {}

    public TaskRequest(String taskDetails) {
        this.taskDetails = taskDetails;
    }

    public String getTaskDetails() {
        return taskDetails;
    }

    @Override
    public String toString() {
        return "Task{" +
                ", taskDetails='" + taskDetails + '\'' +
                '}';
    }
}

