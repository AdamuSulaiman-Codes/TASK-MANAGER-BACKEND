package com.achlys20.Task.Management.Backend.User;

import com.achlys20.Task.Management.Backend.User.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public ResponseEntity<List<UserRequest>> getAllUsers(){
        List<UserRequest> userRequests = userService.getUsers();
        return new ResponseEntity<>(userRequests, HttpStatus.OK);
    }
}
