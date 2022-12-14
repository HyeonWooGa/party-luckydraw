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
    public UserDto.ResponseUserId registerUserName(@RequestBody UserDto.userName userName) {
        UserDto.ResponseUserId responseUserId = userService.registerUserName(userName);
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

}
