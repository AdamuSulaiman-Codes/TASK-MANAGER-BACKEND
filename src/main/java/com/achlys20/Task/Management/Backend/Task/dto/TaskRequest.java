package com.achlys20.Task.Management.Backend.Task.dto;

import com.achlys20.Task.Management.Backend.Task.Priority;
import com.achlys20.Task.Management.Backend.Task.Status;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class TaskRequest {
    private Long id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Status status;
    private Long userId;
}
