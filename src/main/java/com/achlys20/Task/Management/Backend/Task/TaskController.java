package com.achlys20.Task.Management.Backend.Task;

import com.achlys20.Task.Management.Backend.Task.dto.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/add-task/{userId}")
    public ResponseEntity<String> addTask(@RequestBody TaskRequest taskRequest, @PathVariable Long userId){
        taskService.addNewTask(taskRequest, userId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/get-user-task/{userId}")
    public ResponseEntity<List<TaskRequest>> getUserTask(@PathVariable Long userId){
        List<TaskRequest> taskRequests = taskService.getUserTask(userId);
        return new ResponseEntity<>(taskRequests, HttpStatus.OK);
    }

    @DeleteMapping("/delete-user-task/{taskId}")
    public ResponseEntity<String> deleteUserTask(@PathVariable Long taskId) {
        taskService.deleteUserTask(taskId);
        return ResponseEntity.ok("success");
    }

    @PatchMapping("/update-user-task/{taskId}")
    public ResponseEntity<String> updateUserTask(
            @PathVariable Long taskId,
            @RequestBody TaskRequest taskRequest
    ) {
        taskService.updateUserTask(taskRequest, taskId);
        return ResponseEntity.ok("success");
    }
}
