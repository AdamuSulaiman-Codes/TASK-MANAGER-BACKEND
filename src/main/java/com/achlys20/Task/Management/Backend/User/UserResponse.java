package com.achlys20.Task.Management.Backend.User;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private Long userName;
    private String email;
}
