package main.service;

import main.dto.TaskRequestDTO;
import main.dto.UserRequestDTO;
import main.model.Task;
import main.model.User;

public class ConvertDtoToModel {
    public static Task convertTaskDtoToModel(TaskRequestDTO taskRequestDTO) {
        return Task.builder().
                id(taskRequestDTO.getId()).
                name(taskRequestDTO.getName()).
                date(taskRequestDTO.getDate()).
                description(taskRequestDTO.getDescription()).
                status(taskRequestDTO.getStatus()).
                build();
    }

    public static User convertUserDtoToModel(UserRequestDTO userRequestDTO) {
        return User.builder().
                id(userRequestDTO.getId()).
                username(userRequestDTO.getUsername()).
                password(userRequestDTO.getPassword())
                .build();
    }
}
