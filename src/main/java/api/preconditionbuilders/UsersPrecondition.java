package api.preconditionbuilders;


import api.dto.rx.admin.user.UserDto;
import api.dto.rx.admin.user.UserRequest;
import api.dto.rx.admin.user.UserRole;
import api.services.UserService;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

import static zutils.FakerUtils.captionWithSuffix;
import static zutils.FakerUtils.randomMail;

@Slf4j
@Getter
@AllArgsConstructor
public class UsersPrecondition {

    private UserDto userResponse;
    private UserRequest userRequest;
    private List<UserDto> userDtoList;

    private UsersPrecondition(UsersPreconditionBuilder builder) {

        this.userRequest = builder.userRequest;
        this.userDtoList = builder.userDtoList;
        this.userResponse = builder.userResponse;
    }

    public static UsersPreconditionBuilder user() {

        return new UsersPreconditionBuilder();
    }

    public static class UsersPreconditionBuilder {

        private Response response;
        private UserDto userResponse;
        private UserRequest userRequest;
        private List<UserDto> userDtoList;
        private UserService userService = new UserService();

        public UsersPreconditionBuilder createNewUser(UserRole role) {
            this.userRequest = UserRequest.builder()
                    .name(captionWithSuffix(role.getDefinition()))
                    .mail(randomMail())
                    .role(role.getRole())
                    .publisherId(role.getRole() == 0 ? 4 : null)
                    .password("Password1")
                    .isEnabled(true)
                    .build();

            this.response = userService.createUser(userRequest);
            this.userResponse = response.as(UserDto.class);

            return this;
        }

        public UsersPreconditionBuilder updateUser(UserDto user) {
            this.userResponse = UserDto.builder()
                    .id(user.getId())
                    .mail(user.getMail())
                    .name(user.getName())
                    .role(user.getRole())
                    .publisherId(user.getPublisherId())
                    .publisherName(user.getPublisherName())
                    .loginId(user.getLoginId())
                    .createdAt(user.getCreatedAt())
                    .updatedAt(user.getUpdatedAt())
                    .isEnabled(user.getIsEnabled())
                    .build();

            this.response = userService.updateUser(userResponse);
            //this.userResponse = response.as(UserDto.class);

            return this;
        }

        public UsersPreconditionBuilder getUserById(int id) {
            this.response = userService.getUserById(id);
            this.userResponse = response.as(UserDto.class);

            return this;
        }

        public UsersPreconditionBuilder getAllUsers() {
            this.response = userService.getAll();
            this.userDtoList = this.getUserResponseList();

            return this;
        }

        public UsersPrecondition build() {

            return new UsersPrecondition(this);
        }

        private List<UserDto> getUserResponseList() {

            return Arrays.asList(response.jsonPath().getObject("items", UserDto[].class));
        }
    }
}
