package server.repository;

import server.entity.User;
import server.exceptions.ResourceNotFoundException;
import server.exceptions.ServerResponseException;

import java.util.List;
import java.util.Optional;

public interface Repository {
    void create(User newInstance) throws ServerResponseException;

    User update(Long registro, User instance) throws ServerResponseException;

    Optional<User> find(Long registro);

    List<User> findAll();

    void deleteByRegistro(Long registro) throws ResourceNotFoundException;
}
