package com.achlys20.Task.Management.Backend.User.dto;

import com.achlys20.Task.Management.Backend.User.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Long id;
    private String userName;
    private String email;
    private Role role;
}
