package server.controller;

import jwt.Jwt;
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
import server.repository.UserRepository;

import java.util.List;

public class UserController implements Controller {
    private static UserController instance = null;
    private final UserRepository repository = new UserRepository();

    private UserController() {
    }

    public static UserController getInstance() {
        if (instance == null) {
            instance = new UserController();
        }
        return instance;
    }

    public String login(LoginRequest.Payload login) throws AuthenticationException {
        var user = repository.login(login.getEmail()).orElseThrow(AuthenticationException::new);
        if(!user.getSenha().equals(login.getSenha())) {
            throw new AuthenticationException();
        }
        return getToken(user);
    }

    public String getToken(User user) {
        return Jwt.createJWT(user.getTipo(), user.getRegistro());
    }

    public List<UserDTO> findUsers() {
        return repository.findAll()
                .stream()
                .map(UserDTO::of)
                .toList();
    }

    public UserDTO findUser(long id) throws ResourceNotFoundException {
        var entity = repository.find(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return UserDTO.of(entity);
    }

    public UserDTO createUser(CreateUser user) throws ServerResponseException {
        var entity = User.of(user);
        repository.create(entity);
        return UserDTO.of(entity);
    }

    public UserDTO updateUser(UpdateUser user) throws ServerResponseException {
        var entity = repository.update(user.getRegistro(), User.of(user));
        return UserDTO.of(entity);
    }

    public void deleteUser(DeleteUser user) throws BadRequestException {
        if (user.getIsSenderAdmin() && user.getRegistroSender().equals(user.getRegistroToDelete())) {
            if (!repository.tryDelete(user.getRegistroToDelete())) {
                throw new BadRequestException("Bad Request");
            }
        } else {
            repository.deleteById(user.getRegistroToDelete());
        }
    }
}
