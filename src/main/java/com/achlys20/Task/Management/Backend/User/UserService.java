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
    public void seedUsers() {

        for (int i = 11; i <= 20; i++) {

            User user = new User();

            user.setUserName("user" + i);
            user.setEmail("user" + i + "@example.com");

            user.setPassword(passwordEncoder.encode("password" + i));

            if (i % 3 == 0) {
                user.setRole(Role.LEAD);
            } else {
                user.setRole(Role.MEMBER);
            }

            userRepository.save(user);
        }
    }
}
