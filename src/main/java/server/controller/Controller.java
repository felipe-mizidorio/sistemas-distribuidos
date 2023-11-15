package server.controller;

import protocol.request.LoginRequest;
import server.datatransferobject.CreateUser;
import server.datatransferobject.DeleteUser;
import server.datatransferobject.UpdateUser;
import server.datatransferobject.UserDTO;
import server.entity.User;
import server.exceptions.AuthenticationException;
import server.exceptions.BadRequestException;
import server.exceptions.ResourceNotFoundException;
import server.exceptions.ServerResponseException;

import java.util.List;

public interface Controller {
    String login(LoginRequest.Payload login) throws AuthenticationException;

    String getToken(User user);

    void checkCredentials(DeleteUser user) throws AuthenticationException, ResourceNotFoundException;

    List<UserDTO> findUsers();

    UserDTO findUser(Long registro) throws ResourceNotFoundException;

    UserDTO createUser(CreateUser user) throws ServerResponseException;

    UserDTO updateUser(UpdateUser user) throws ServerResponseException;

    void deleteUser(DeleteUser user) throws BadRequestException;
}
