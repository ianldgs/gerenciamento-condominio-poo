package com.cotemig.repositories;

import com.cotemig.models.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by matheus.elias on 11/2/16.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
