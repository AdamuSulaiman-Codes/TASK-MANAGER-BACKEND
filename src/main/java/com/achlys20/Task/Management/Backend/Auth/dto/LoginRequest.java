package com.achlys20.Task.Management.Backend.Auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
