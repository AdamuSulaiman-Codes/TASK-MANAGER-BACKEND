package com.achlys20.Task.Management.Backend.Project;

import com.achlys20.Task.Management.Backend.Notification.NotificationService;
import com.achlys20.Task.Management.Backend.Project.Exception.ProjectException;
import com.achlys20.Task.Management.Backend.Project.dto.ProjectRequest;
import com.achlys20.Task.Management.Backend.User.User;
import com.achlys20.Task.Management.Backend.User.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {
    public final ProjectRepository projectRepository;
    public final UserRepository userRepository;
    public final NotificationService notificationService;

    public List<ProjectRequest> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(project -> new ProjectRequest(
                        project.getId(),
                        project.getName(),
                        project.getDescription(),
                        project.getLead().getId(),
                        project.getMembers()
                                .stream()
                                .map(user -> user.getId())
                                .collect(Collectors.toSet()),
                        project.getCreatedAt(),
                        project.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void addMemberToProject(Long projectId, Long assigneeId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(()-> new ProjectException("project not found"));

        User user = userRepository.findById(assigneeId)
                .orElseThrow(()-> new RuntimeException("user not found"));

        boolean exists = project.getMembers()
                .stream()
                .anyMatch(member -> member.getId().equals(user.getId()));
        project.addMember(user);

        if(exists){
            throw new RuntimeException("User is already a member of this project");
        }

        projectRepository.save(project);

        notificationService.sendProjectMemberAddedNotification(user, project);
    }

    @Transactional
    public void createProject(ProjectRequest projectRequest, Long leadId) {

        Project project = new Project();

        User lead = userRepository.findById(leadId)
                .orElseThrow(() -> new RuntimeException("Lead not found"));

        project.setName(projectRequest.getName());
        project.setDescription(projectRequest.getDescription());
        project.setLead(lead);

        if (projectRequest.getMemberIds() != null && !projectRequest.getMemberIds().isEmpty()) {

            Set<User> members = projectRequest.getMemberIds()
                    .stream()
                    .map(id -> userRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("User not found: " + id)))
                    .collect(Collectors.toSet());

            project.setMembers(members);
        }

        projectRepository.save(project);
    }
}
