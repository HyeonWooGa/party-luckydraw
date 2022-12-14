package server.partyLuckyDraw.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto.ResponseUserId registerUserName(@RequestBody UserDto.RequestUserSimpleUser simpleUser) {
        UserDto.ResponseUserId responseUserId = userService.registerUserName(simpleUser);
        return responseUserId;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto.ResponseSimpleUser> getUserNames() {
        return userService.getUsers();
    }

    @DeleteMapping("/{user-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/randoms")
    @ResponseStatus(HttpStatus.OK)
    public UserDto.ResponseSimpleUser randomPickUser() {
        return userService.findRandomPickUser();
    }

    @GetMapping("/names")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto.ResponseSimpleUser> findSavedUser(@RequestBody UserDto.RequestUsername username) {
        return userService.checkSavedUserByName(username);
    }
}
