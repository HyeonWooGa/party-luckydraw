package server.partyLuckyDraw.user;

import lombok.*;


public class UserDto {

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class RequestUserSimpleUser {
        private String userName;
        private String phone;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseUserId {
        private Long userId;
    }

    @Getter
    @Builder
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ResponseSimpleUser {
        private Long userId;
        private String userName;
        private String phone;
    }
}
