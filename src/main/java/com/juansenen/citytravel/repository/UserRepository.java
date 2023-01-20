package com.juansenen.citytravel.repository;

import com.juansenen.citytravel.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

}
