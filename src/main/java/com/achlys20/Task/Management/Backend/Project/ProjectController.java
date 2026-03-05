package com.achlys20.Task.Management.Backend.Project;

import com.achlys20.Task.Management.Backend.Project.dto.ProjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @PreAuthorize("hasRole('LEAD')")
    @PostMapping("/create-project/{leadId}")
    public ResponseEntity<String> createNewProject(@RequestBody ProjectRequest projectRequest, @PathVariable Long leadId){
        projectService.createProject(projectRequest, leadId);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectRequest>> getAllProjects(){
        List<ProjectRequest> projectRequests = projectService.getAllProjects();
        return new ResponseEntity<>(projectRequests, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('LEAD')")
    @PostMapping("/add-new-member/{projectId}/{assigneeId}")
    public ResponseEntity<String> addMemberToProject(@PathVariable Long projectId ,@PathVariable Long assigneeId){
        projectService.addMemberToProject(projectId ,assigneeId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
