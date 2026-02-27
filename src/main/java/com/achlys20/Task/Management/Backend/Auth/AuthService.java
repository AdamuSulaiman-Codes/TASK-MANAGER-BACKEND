package com.achlys20.Task.Management.Backend.Auth;

import com.achlys20.Task.Management.Backend.Auth.Exception.AuthException;
import com.achlys20.Task.Management.Backend.Auth.dto.AuthResponse;
import com.achlys20.Task.Management.Backend.Auth.dto.LoginRequest;
import com.achlys20.Task.Management.Backend.Auth.dto.SignUpRequest;
import com.achlys20.Task.Management.Backend.Config.JwtService;
import com.achlys20.Task.Management.Backend.User.User;
import com.achlys20.Task.Management.Backend.User.UserRepository;
import com.achlys20.Task.Management.Backend.User.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse signUpNewUser(SignUpRequest signUpRequest) {
        if(userRepository.findByUserName(signUpRequest.getUserName()).isPresent()){
            throw new AuthException("UserName Already Exists");
        }

        if(userRepository.findByUserName(signUpRequest.getEmail()).isPresent()){
            throw new AuthException("Email Already Exists");
        }

        User user = new User();

        user.setUserName(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());

        String token = jwtService.generateToken(user.getUserName());

        return new AuthResponse(token, userResponse);
    }

    public AuthResponse loginUser(LoginRequest loginRequest) {
        User user = userRepository.findByUserName(loginRequest.getUserName()).orElseThrow(()-> new AuthException("Invalid UserName or Password"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new AuthException("Invalid UserName or Password");
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());

        String token = jwtService.generateToken(user.getUserName());

        return new AuthResponse(token, userResponse);
    }
}
