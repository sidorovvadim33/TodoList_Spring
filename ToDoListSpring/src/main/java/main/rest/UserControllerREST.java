package main.rest;

import main.dto.UserRequestDTO;
import main.model.Role;
import main.model.User;
import main.service.ConvertDtoToModel;
import main.service.impl.UserControllerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;


@RestController
public class UserControllerREST {

    private final UserControllerServiceImpl userControllerServiceImpl;

    @Autowired
    public UserControllerREST(UserControllerServiceImpl userControllerServiceImpl) {

        this.userControllerServiceImpl = userControllerServiceImpl;
    }

    @PostMapping(value="/registration")
    public void userRegistration(UserRequestDTO userRequestDTO) {
        User user = ConvertDtoToModel.convertUserDtoToModel(userRequestDTO);
        user.setActive(true);
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(Role.USER);
        user.setRoles(roleSet);
        userControllerServiceImpl.saveUser(user);
    }
}