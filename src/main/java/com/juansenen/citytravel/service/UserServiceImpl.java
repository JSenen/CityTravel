package com.juansenen.citytravel.service;

import com.juansenen.citytravel.domain.User;
import com.juansenen.citytravel.domain.dto.UserDTO;
import com.juansenen.citytravel.repository.RoleRepository;
import com.juansenen.citytravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

//    @Override
//    public void remove(User user) {
//        userRepository.delete(user);
//    }
//
    @Override
    public Set<User> findAll() {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    @Override
//    public Set<User> findByCity(String city) {
//        return null;
//    }

    @Override
    public User addUser(UserDTO userDTO) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
//        user.setEmail("default@email.com");

        return userRepository.save(user);
    }
}
