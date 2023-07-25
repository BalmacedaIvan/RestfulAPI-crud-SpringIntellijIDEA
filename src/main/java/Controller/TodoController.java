package Controller;

import Model.Task;
import Repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping(value = "/tasks")
    public List<Task> getTasks(){
        return todoRepository.findAll();
    }

    @PostMapping(value = "/saveTask")
    public String postTask(@RequestBody Task task){
        todoRepository.save(task);
        return "Task was saved";
    }

    @PutMapping(value = "/update/{id}")
    public String updateTask(@PathVariable Long id, @RequestBody Task task){
        Task updatedTask = todoRepository.findAllById(id).get();
        updatedTask.setTitle(task.getTitle());
        updatedTask.setDesc(task.getDesc());

        todoRepository.save(updatedTask);
        return "Task was updated";
    }

    @DeleteMapping(value = "/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        Task delTask = todoRepository.findAllById(id).get();
        todoRepository.delete(delTask);

        return "Task was deleted";
    }
}
