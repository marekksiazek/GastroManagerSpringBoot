package pl.marekksiazek.GastroManager.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import pl.marekksiazek.GastroManager.entity.DailyTask;
import pl.marekksiazek.GastroManager.entity.Task;
import pl.marekksiazek.GastroManager.entity.User;
import pl.marekksiazek.GastroManager.repository.TaskRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "Task", description = "Task API")
@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskRepository taskRepository;

    @GetMapping("/tasks")
    @Operation(
            summary = "Fetch all tasks",
            description = "Fetch all tasks entities and their data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<List<Task>> getAllTasks(){
        List tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/{id}")
    @Operation(
            summary = "Fetch one task",
            description = "Fetch task entities and his data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<Task> getTaskById(@Parameter(description = "Id of task to be searched") @PathVariable Long id){
        Optional<Task> taskOptional = taskRepository.findById(id);
        return taskOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/tasks")
    @Transactional
    @Operation(
            summary = "Create one task",
            description = "Create task entities and add his data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created task", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<Task> cretateTask(@Parameter(description = "Task entity") @RequestBody Task task){
        Task savedTask = taskRepository.save(task);
        return ResponseEntity.created(URI.create("/dailyTasks/" + savedTask.getTaskId())).body(savedTask);
    }

    @PutMapping("/tasks/{id}")
    @Transactional
    @Operation(
            summary = "Update one task",
            description = "Update task entities and add his data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated task", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<Task> updateTask(@Parameter (description = "Task entity") @RequestBody Task newTask,
                                                     @Parameter(description = "Task id to update") @PathVariable Long id){

        boolean oldTask = taskRepository.findById(id)
                .map(task -> {
                    task.setTaskTitle(newTask.getTaskTitle());
                    task.setDescription(newTask.getDescription());
                    task.setAssignedWorker(newTask.getAssignedWorker());
                    task.setCompanyId(newTask.getCompanyId());
                    taskRepository.save(task);
                    return ResponseEntity.ok(task).equals(HttpStatus.CREATED);
                })
                .orElseGet(() -> {
                    newTask.setTaskId(id);
                    taskRepository.save(newTask);
                    return ResponseEntity.ok(newTask).equals(HttpStatus.CREATED);
                });

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tasks/{id}")
    @Transactional
    @Operation(
            summary = "Delete one task",
            description = "Delete task entities and change it to inactive"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted task", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<Task> deleteTask(@Parameter (description = "Task entity") @RequestBody Task deletedTask,
                                           @Parameter(description = "Task id to delete") @PathVariable Long id){
        boolean oldTask = taskRepository.findById(id)
                .map(task -> {
                    task.setIsDeleted(1);
                    taskRepository.save(task);
                    return ResponseEntity.ok(task).equals(HttpStatus.OK);
                })
                .orElseGet(() -> {
                    deletedTask.setTaskId(id);
                    taskRepository.save(deletedTask);
                    return ResponseEntity.ok(deletedTask).equals(HttpStatus.NO_CONTENT);
                });

        return ResponseEntity.ok().build();
    }
}
