package com.achlys20.Task.Management.Backend.Auth;

import com.achlys20.Task.Management.Backend.Auth.dto.AuthResponse;
import com.achlys20.Task.Management.Backend.Auth.dto.LoginRequest;
import com.achlys20.Task.Management.Backend.Auth.dto.SignUpRequest;
import com.achlys20.Task.Management.Backend.User.UserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
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
        System.out.println("LoginRequest: " + loginRequest);
        return authService.loginUser(loginRequest);
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getUser(Authentication authentication){
        String userName = authentication.getName();
        UserResponse userResponse = authService.getCurrentUser(userName);
        return ResponseEntity.ok(userResponse);
    };
}
