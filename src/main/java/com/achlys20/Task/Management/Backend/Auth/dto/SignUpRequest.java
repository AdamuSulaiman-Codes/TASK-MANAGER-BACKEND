package com.achlys20.Task.Management.Backend.Auth.dto;

import lombok.Data;

@Data
public class SignUpRequest {
    private String userName;
    private String email;
    private String password;
    private Role role;
}
