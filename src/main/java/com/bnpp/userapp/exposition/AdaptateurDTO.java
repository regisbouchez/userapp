package com.bnpp.userapp.exposition;

import com.bnpp.userapp.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class AdaptateurDTO {

    public static User convertToUser(UserDTO userDTO) {
        return (new User(null,userDTO.getFirstName(),userDTO.getLastName()));
    }
    public static UserDTO convertToUserDTO(User user) {
        return (new UserDTO(user.getFirstName(), user.getLastName()));
    }
    public static List<UserDTO> convertToUserDTOList (List<User> users) {
        return (users.stream().map(AdaptateurDTO::convertToUserDTO).collect(Collectors.toList()));
    }
}
