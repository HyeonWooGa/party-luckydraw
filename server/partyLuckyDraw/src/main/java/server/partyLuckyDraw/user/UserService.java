package server.partyLuckyDraw.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto.ResponseUserId registerUserName(UserDto.RequestUserSimpleUser username) {
        User user = userMapper.toUser(username);
        userRepository.save(user);
        return userMapper.toUserId(user);
    }

    public List<UserDto.ResponseSimpleUser> getUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toSimpleUserDto)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public UserDto.ResponseSimpleUser findRandomPickUser() {
        List<User> users = userRepository.findAll();
        // 빈 리스트면 예외 터트리기
        int luckyUserId = (int) (Math.random() * users.size());
        User user = users.get(luckyUserId);
        return userMapper.toSimpleUserDto(user);
    }

    public List<UserDto.ResponseSimpleUser> checkSavedUserByName(UserDto.RequestUsername requestUsername) {
        List<User> users = userRepository.findByName(requestUsername.getUsername()).orElseThrow();
        return users.stream()
                .map(userMapper::toSimpleUserDto)
                .collect(Collectors.toList());
    }
}
