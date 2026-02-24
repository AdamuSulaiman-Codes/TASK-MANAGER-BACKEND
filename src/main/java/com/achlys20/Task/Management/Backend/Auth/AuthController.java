package com.achlys20.Task.Management.Backend.Auth;

import com.achlys20.Task.Management.Backend.Auth.dto.AuthResponse;
import com.achlys20.Task.Management.Backend.Auth.dto.LoginRequest;
import com.achlys20.Task.Management.Backend.Auth.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {
    public final AuthService authService;

    @PostMapping("/sign-up")
    public AuthResponse userSignUp(@RequestBody SignUpRequest signUpRequest){
        return authService.signUpNewUser(signUpRequest);
    }

    @PostMapping("/log-in")
    public AuthResponse userLogin(@RequestBody LoginRequest loginRequest){
        return authService.loginUser(loginRequest);
    }
}
