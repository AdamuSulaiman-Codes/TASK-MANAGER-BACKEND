package com.achlys20.Task.Management.Backend.Task;

import com.achlys20.Task.Management.Backend.Task.Exception.TaskException;
import com.achlys20.Task.Management.Backend.Task.dto.TaskRequest;
import com.achlys20.Task.Management.Backend.User.User;
import com.achlys20.Task.Management.Backend.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public void addNewTask(TaskRequest taskRequest, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TaskException("unable to find user"));

        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setPriority(taskRequest.getPriority());
        task.setStatus(taskRequest.getStatus());
        task.setDueDate(taskRequest.getDueDate());
        task.setUser(user);

        taskRepository.save(task);
    }

    public List<TaskRequest> getUserTask(Long userId) {
        List<Task> tasks = taskRepository.findByUserId(userId);

        return tasks.stream().map(task -> new TaskRequest(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getDueDate(),
            task.getPriority(),
            task.getStatus(),
            task.getProject().getId(),
            task.getAssignee().getId()
        )).toList();
    }

    public void deleteUserTask(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new TaskException("task does not exist"));

        if (task.getProject() == null) {
            throw new TaskException("Task is not linked to any project");
        }

        Project project = task.getProject();

        if (project.getLead() == null) {
            throw new TaskException("Project has no lead assigned");
        }

        if (!project.getLead().getUserName().equals(userName)) {
            throw new TaskException("You are not allowed to delete this task");
        }


        taskRepository.delete(task);
    }

    @Transactional
    public void updateUserTask(TaskRequest taskRequest, Long taskId, String userName) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException("Task does not exist"));

        if (task.getAssignee() == null) {
            throw new TaskException("Task has no assignee");
        }

        if (!task.getAssignee().getUserName().equals(userName)) {
            throw new TaskException("You are not allowed to update this task");
        }

        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setDueDate(taskRequest.getDueDate());
        task.setPriority(taskRequest.getPriority());
        task.setStatus(taskRequest.getStatus());

    }

    public List<Task> getProjectTask(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new TaskException("Project does not exist"));

        return project.getTasks();
    }
}
