package hello.controllers;

import hello.model.Task;
import hello.model.Task;
import hello.model.TaskRequest;
import hello.services.CartService;
import hello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping("health-check")
    public String okay(){
        return "okay";
    }

    @RequestMapping(path = "", method = RequestMethod.POST)
    public DeferredResult<Task> addTask(
            @RequestBody TaskRequest taskRequest){
        DeferredResult<Task> result = new DeferredResult<>();
        taskService.addTask(taskRequest.getTaskDetails())
                .subscribe((task -> {
                    System.out.println("-------------added Following---------------------");
                    System.out.println(task);
                    result.setResult(task);
                }));
        return result;
    }

    @RequestMapping(path = "/{taskId}", method = RequestMethod.PUT)
    public DeferredResult<Task> updateTaskStatusAndTaskDetails(
            @PathVariable(value="taskId") String taskId,
            @RequestParam(value = "status", required = false) Boolean status,
            @RequestBody(required = false) Optional<TaskRequest> taskRequest){
        DeferredResult<Task> result = new DeferredResult<>();
        System.out.println();

        taskService.updateTaskStatus(taskId, status, taskRequest.orElse(new TaskRequest("")).getTaskDetails())
                .subscribe((task -> {
                    System.out.println("-------------updated status of task---------------------");
                    System.out.println(task);
                    result.setResult(task);
                }));
        return result;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public DeferredResult<List<Task>> getAllTasks(){
        DeferredResult<List<Task>> result = new DeferredResult<>();
        System.out.println();

        taskService.getAllTasks()
                .subscribe((tasks -> {
                    System.out.println("-------------updated status of task---------------------");
                    System.out.println(tasks);
                    result.setResult(tasks);
                }));
        return result;
    }

    @RequestMapping(path = "/{taskId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable(value="taskId") String taskId){
        taskService.deleteById(taskId)
                .subscribe((status -> {
                    System.out.println("-------------delete By ID---------------------");
                    System.out.println(status);
                }));
    }
}
