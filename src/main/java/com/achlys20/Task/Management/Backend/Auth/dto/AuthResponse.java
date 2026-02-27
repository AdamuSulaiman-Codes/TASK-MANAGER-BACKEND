package com.achlys20.Task.Management.Backend.Auth.dto;

import com.achlys20.Task.Management.Backend.User.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String accessToken;
    private UserResponse user;
}
