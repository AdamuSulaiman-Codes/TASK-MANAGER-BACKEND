package com.achlys20.Task.Management.Backend.User;

import com.achlys20.Task.Management.Backend.User.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserRequest> getUsers() {

        List<User> users = userRepository.findAll();

        List<UserRequest> userRequests = users.stream()
                .map(user -> new UserRequest(
                        user.getId(),
                        user.getUserName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .toList();

        return userRequests;
    }
}
