package server.management;

import jwt.Jwt;
import protocol.request.LoginRequest;
import server.datatransferobject.CreateUser;
import server.datatransferobject.DeleteUser;
import server.datatransferobject.UpdateUser;
import server.datatransferobject.UserDTO;
import server.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserManager {
    private static UserManager instance = null;

    private UserManager() {
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public String login(LoginRequest.Payload ignoredUserToLogin) throws ResourceNotFoundException {
        return Jwt.createJWT(true, 1);
    }

    public Stream<UserDTO> findUsers() {
        var users = new ArrayList<UserDTO>();
        users.add(new UserDTO("nome", "nome@email.com", false, 1));
        return users.stream();
    }

    public UserDTO findUser(long id) {
        return new UserDTO("nome", "nome@email.com", false, 1);
    }

    public UserDTO createUser(CreateUser user) {
        return null;
    }

    public UserDTO updateUser(UpdateUser user) {
        return null;
    }

    public void deleteUser(DeleteUser user) {

    }
}
