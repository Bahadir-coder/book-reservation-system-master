package com.example.bookreservation.service;

import com.example.bookreservation.dao.entity.UserEntity;
import com.example.bookreservation.dao.exception.FoundException;
import com.example.bookreservation.dao.exception.NotFoundException;
import com.example.bookreservation.dao.repository.UserRepository;
import com.example.bookreservation.mapper.UserMapper;
import com.example.bookreservation.model.input.UserDtoInput;
import com.example.bookreservation.model.output.UserDtoOutput;
import com.example.bookreservation.model.output.UserDtoOutputForUser;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDtoOutput> getAllUsers() {
        System.out.println("Get All Users Started...");

        List<UserEntity> userEntities = userRepository.findAll();

        if (userEntities.isEmpty()) {
            throw new NotFoundException("Users Not Found!");
        }
        List<UserDtoOutput> userDtoOutputs = userMapper.
                mapEntityToDtos(userEntities);
        return userDtoOutputs;
    }

    public List<UserDtoOutputForUser> getAllUsersForUser() {
        System.out.println("Get All Users for User Started...");

        List<UserEntity> userEntities = userRepository.findAll();

        if (userEntities.isEmpty()) {
            throw new NotFoundException("Users Not Found!");
        }
        List<UserDtoOutputForUser> userDtoOutputForUsers = userMapper.
                mapEntityToDtosForUser(userEntities);
        return userDtoOutputForUsers;
    }

    public UserDtoOutput findByFinCode(String finCode) {
        System.out.println("Find by FinCode Started...");

        Optional<UserEntity> userEntity = userRepository.
                findByUserFinCodeIgnoreCase(finCode);

        if (userEntity.isEmpty()) {
            throw new NotFoundException("User Not Found!");
        }
        UserDtoOutput userDtoOutput = userMapper.
                mapEntityToDto(userEntity.orElse(null));
        return userDtoOutput;
    }

    public UserDtoOutputForUser findByFinCodeForUser(String finCode) {
        System.out.println("Find by FinCode for User Started...");

        Optional<UserEntity> userEntity = userRepository.
                findByUserFinCodeIgnoreCase(finCode);

        if (userEntity.isEmpty()) {
            throw new NotFoundException("User Not Found!");
        }
        UserDtoOutputForUser userDtoOutput = userMapper.
                mapEntityToDtoForUser(userEntity.orElse(null));
        return userDtoOutput;
    }

    @Transactional
    public void saveUser(UserDtoInput user) {
        System.out.println("Save User Started...");

        UserEntity userEntity = userMapper.
                mapDtoToEntity(user);

        List<UserEntity> userEntities = userRepository.findAll();
        for (int i = 0; i < userEntities.size(); i++) {
            if (userEntities.get(i).getUserFinCode().equals(userEntity.getUserFinCode())) {
                throw new FoundException("User is also Found!");
            }
            if (userEntities.get(i).getUserEmail().equals(userEntity.getUserEmail())) {
                throw new FoundException("User is also Found!");
            }
            if (userEntities.get(i).getUserTel().equals(userEntity.getUserTel())) {
                throw new FoundException("User is also Found!");
            }
        }
        userRepository.save(userEntity);
    }

    @Transactional
    public void deleteByFinCode(String finCode) {
        System.out.println("Delete by FinCode Started...");

        UserEntity userEntity = userRepository.
                findByUserFinCodeIgnoreCase(finCode).
                orElseThrow(() -> new NotFoundException("User Not Found!"));
        userRepository.delete(userEntity);
    }
}
