package hello.services;

import hello.model.Cart;
import hello.model.Product;
import hello.model.Task;
import hello.repository.CartRepository;
import hello.repository.ProductRepository;
import hello.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TaskRepository taskRepository;


    public Observable<Task> getTaskById(String id){
        return Observable.just(taskRepository.findOne(id));
    }

    public Observable<Task> addTask(String taskDetails){
        Task saved= taskRepository.save(new Task(taskDetails));
        return Observable.just(saved);
    }

    public Observable<Task> updateTaskStatus(String taskId, Boolean status, String taskDetails) {
        Task task = taskRepository.findOne(taskId);
        if(status != null){
            task.status = status;
        }
        if(taskDetails != ""){
            task.taskDetails = taskDetails.toString();
        }
        return Observable.just(taskRepository.save(task));
    }

    public Observable<List<Task>> getAllTasks(){
        return Observable.just(taskRepository.findAll());
    }

    public Observable<String> deleteById(String id) {
        taskRepository.delete(id);
        return Observable.just("Ok");
    }

}
