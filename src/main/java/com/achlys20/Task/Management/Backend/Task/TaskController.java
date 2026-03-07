package com.achlys20.Task.Management.Backend.Task;

import com.achlys20.Task.Management.Backend.Task.dto.TaskRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PreAuthorize("hasRole('LEAD')")
    @PostMapping("/add-task/{projectId}/{assigneeId}")
    public ResponseEntity<String> addTask(@RequestBody TaskRequest taskRequest, @PathVariable Long projectId,
            @PathVariable Long assigneeId) {
        taskService.addNewTask(taskRequest, projectId, assigneeId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/get-user-task")
    public ResponseEntity<List<TaskRequest>> getUserTask(Authentication authentication) {
        List<TaskRequest> taskRequests = taskService.getUserTask(authentication.getName());
        return new ResponseEntity<>(taskRequests, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('LEAD')")
    @DeleteMapping("/delete-user-task/{taskId}")
    public ResponseEntity<String> deleteUserTask(@PathVariable Long taskId, Authentication authentication) {
        taskService.deleteUserTask(taskId, authentication.getName());
        return ResponseEntity.ok("success");
    }

    @PreAuthorize("hasRole('LEAD')")
    @PatchMapping("/update-user-task/{taskId}")
    public ResponseEntity<String> updateUserTask(
            @PathVariable Long taskId,
            @RequestBody TaskRequest taskRequest,
            Authentication authentication) {
        taskService.updateUserTask(taskRequest, taskId, authentication.getName());
        return ResponseEntity.ok("success");
    }

    @PreAuthorize("hasRole('LEAD') or hasRole('MEMBER')")
    @GetMapping("/project-task/{projectId}")
    public ResponseEntity<List<TaskRequest>> getProjectTask(@PathVariable Long projectId) {
        List<TaskRequest> taskRequests = taskService.getProjectTask(projectId);
        return new ResponseEntity<>(taskRequests, HttpStatus.OK);
    }
}
