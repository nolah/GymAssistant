package ninja.backend.repository;

import java.util.List;
import java.util.Optional;

import ninja.backend.model.*;
import ninja.backend.model.enumeration.*;
import ninja.backend.repository.tuple.*;


public interface UserRepositoryCustom {

    Optional<User> findByIdWithPerson(Long id);

    List<User> findByRole(Role role);

    Optional<User> findByUsername(String username);

    List<User> findByPasswordHash(String passwordHash);

}
