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
import pl.marekksiazek.GastroManager.entity.User;
import pl.marekksiazek.GastroManager.repository.DailyTaskRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Tag(name = "Daily Task", description = "Daily Task API")
@RestController
@RequestMapping("/api")
public class DailyTaskController {

    @Autowired
    DailyTaskRepository dailyTaskRepository;

    @GetMapping("/dailyTasks")
    @Operation(
            summary = "Fetch all daily tasks",
            description = "Fetch all daily tasks entities and their data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = DailyTask.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<List<DailyTask>> getAllDailyTask(){
        List tasks = dailyTaskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/dailyTasks/{id}")
    @Operation(
            summary = "Fetch one daily task",
            description = "Fetch daily task entities and his data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success operation", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = DailyTask.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<DailyTask> getDailyTaskById(@Parameter(description = "Id of daily task to be searched") @PathVariable Long id){
        Optional<DailyTask> taskOptional = dailyTaskRepository.findById(id);
        return taskOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/dailyTasks")
    @Transactional
    @Operation(
            summary = "Create one daily task",
            description = "Create daily task entities and add his data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created daily task", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = DailyTask.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<DailyTask> cretateTask(@Parameter(description = "Daily task entity") @RequestBody DailyTask dailyTask){
        DailyTask savedDailyTask = dailyTaskRepository.save(dailyTask);
        return ResponseEntity.created(URI.create("/dailyTasks/" + savedDailyTask.getTaskId())).body(savedDailyTask);
    }

    @PutMapping("/dailyTasks/{id}")
    @Transactional
    @Operation(
            summary = "Update one daily task",
            description = "Update daily task entities and add his data from data source"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated daily task", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = DailyTask.class))),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<DailyTask> updateDailyTask(@Parameter (description = "Daily task entity") @RequestBody DailyTask newDailyTask,
                                              @Parameter(description = "Daily task id to update") @PathVariable Long id){

        boolean oldDailyTask = dailyTaskRepository.findById(id)
                .map(dailyTask -> {
                    dailyTask.setTitle(newDailyTask.getTitle());
                    dailyTask.setDescription(newDailyTask.getDescription());
                    dailyTask.setStatus(newDailyTask.getStatus());
                    dailyTask.setCompanyId(newDailyTask.getCompanyId());
                    dailyTask.setUserId(newDailyTask.getUserId());
                    dailyTask.setCompanyId(newDailyTask.getCompanyId());
                    dailyTaskRepository.save(dailyTask);
                    return ResponseEntity.ok(dailyTask).equals(HttpStatus.CREATED);
                })
                .orElseGet(() -> {
                    newDailyTask.setTaskId(id);
                    dailyTaskRepository.save(newDailyTask);
                    return ResponseEntity.ok(newDailyTask).equals(HttpStatus.CREATED);
                });

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/dailyTasks/{id}")
    @Transactional
    @Operation(
            summary = "Delete one daily task",
            description = "Delete daily task entities and change it to inactive"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted daily task", content = @Content),
            @ApiResponse(responseCode = "403", description = "You don't have access to the endpoint", content = @Content),
            @ApiResponse(responseCode = "404", description = "Page not found", content = @Content)
    })
    public ResponseEntity<User> deleteDailyTask(@Parameter (description = "Daily task entity") @RequestBody DailyTask deletedDailyTask,
                                           @Parameter(description = "Daily task id to delete") @PathVariable Long id){
        boolean oldDailyTesk = dailyTaskRepository.findById(id)
                .map(dailyTask -> {
                    dailyTask.setIsDeleted(1);
                    dailyTaskRepository.save(dailyTask);
                    return ResponseEntity.ok(dailyTask).equals(HttpStatus.OK);
                })
                .orElseGet(() -> {
                    deletedDailyTask.setTaskId(id);
                    dailyTaskRepository.save(deletedDailyTask);
                    return ResponseEntity.ok(deletedDailyTask).equals(HttpStatus.NO_CONTENT);
                });

        return ResponseEntity.ok().build();
    }


}
