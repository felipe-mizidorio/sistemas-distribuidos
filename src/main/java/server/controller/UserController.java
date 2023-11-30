package server.controller;

import jwt.Jwt;
import protocol.request.LoginRequest;
import server.datatransferobject.user.CreateUser;
import server.datatransferobject.user.DeleteUser;
import server.datatransferobject.user.UpdateUser;
import server.datatransferobject.user.UserDTO;
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

    public void checkCredentials(DeleteUser user) throws AuthenticationException, ResourceNotFoundException {
        var entitySender = repository.find(user.getRegistroSender())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        String email = entitySender.getEmail();
        String senha = entitySender.getSenha();
        if(!user.getEmail().equals(email)) {
            throw new AuthenticationException();
        }
        if(!user.getSenha().equals(senha)) {
            throw new AuthenticationException();
        }
    }

    public List<UserDTO> findUsers() {
        return repository.findAll()
                .stream()
                .map(UserDTO::of)
                .toList();
    }

    public UserDTO findUser(Long registro) throws ResourceNotFoundException {
        var entity = repository.find(registro)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
        return UserDTO.of(entity);
    }

    public UserDTO createUser(CreateUser user) throws ServerResponseException {
        var entity = User.of(user);
        repository.create(entity);
        return UserDTO.of(entity);
    }

    public UserDTO updateUser(UpdateUser user) throws ServerResponseException {
        if(user.getSender() == user.getRegistro() && user.getTipo() != null && !user.getTipo()) {
            if(repository.countNumberOfAdmins() == 1) {
                throw new BadRequestException("O último admin não pode alterar o seu tipo.");
            }
        }
        var entity = repository.update(user.getRegistro(), User.of(user));
        return UserDTO.of(entity);
    }

    public void deleteUser(DeleteUser user) throws BadRequestException {
        if (user.getIsSenderAdmin() && user.getRegistroSender().equals(user.getRegistroToDelete())) {
            if (!repository.tryDelete(user.getRegistroToDelete())) {
                throw new BadRequestException("O último admin não pode ser deletado.");
            }
        } else {
            repository.deleteByRegistro(user.getRegistroToDelete());
        }
    }
}
