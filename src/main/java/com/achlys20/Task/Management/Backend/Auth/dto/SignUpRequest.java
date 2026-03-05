package com.achlys20.Task.Management.Backend.Auth.dto;

import com.achlys20.Task.Management.Backend.User.Role;
import lombok.Data;

@Data
public class SignUpRequest {
    private String userName;
    private String email;
    private String password;
    private Role role;
}
