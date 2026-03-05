package com.achlys20.Task.Management.Backend.Project.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {

    private Long id;
    private String name;
    private String description;
    private Long leadId;
    private Set<Long> memberIds = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}