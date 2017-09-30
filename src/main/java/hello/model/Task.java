package hello.model;

import org.springframework.data.annotation.Id;

public class Task {

    @Id
    public String id;

    public String taskDetails;
    public boolean status;


    public Task() {}

    public Task(String taskDetails) {
        this.taskDetails = taskDetails;
        this.status = false;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", taskDetails='" + taskDetails + '\'' +
                ", status=" + status +
                '}';
    }
}

