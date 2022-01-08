package com.epam.customerservice.service;

import com.epam.customerservice.dto.UserDto;
import com.epam.customerservice.entity.RegisteredUserEntity;
import com.epam.customerservice.entity.UserEntity;
import com.epam.customerservice.exception.EntityNotFoundException;
import com.epam.customerservice.repository.RegisteredUserRepository;
import com.epam.customerservice.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private RegisteredUserRepository registeredUserRepository;
    private UserDto userDto;

    public UserService(UserRepository userRepository, RegisteredUserRepository registeredUserRepository) {
        this.userRepository = userRepository;
        this.registeredUserRepository = registeredUserRepository;
        this.userDto = new UserDto();
    }

    public Long createUser() {
        UserEntity userEntity = new UserEntity();
        return userRepository.save(userEntity).getSessionID();
    }

    public void putRegisteredUserToUser(long sessionId, long registeredUserID) {
        UserEntity userEntity = userRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session id does not exist"));
        RegisteredUserEntity registeredUser = registeredUserRepository.findById(registeredUserID)
                .orElseThrow(() -> new EntityNotFoundException("Registered user id does not exist"));
        userEntity.setRegisteredUser(registeredUser);
        userRepository.save(userEntity);
    }

    public UserDto getRegisteredUserBySessionID(long sessionId) {
        UserEntity userEntity = userRepository.findById(sessionId)
                .orElseThrow(() -> new EntityNotFoundException("Session id does not exist"));
        if (userEntity.getRegisteredUser() != null) {
            RegisteredUserEntity registeredUser = registeredUserRepository.findById(
                            userEntity.getRegisteredUser().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Registered user id does not exist"));
            return userDto.entityToDto(registeredUser);
        }
        return null;
    }

    public Boolean checkSessionIdIsExists(Long sessionId) {
        return userRepository.existsById(sessionId);
    }

    public UserDto registerUser(UserDto userDto) {
        return userDto.entityToDto(registeredUserRepository.save(userDto.dtoToEntity()));
    }

    public UserDto getUser(long id) {
        RegisteredUserEntity registeredUser = registeredUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registered user id does not exist"));
        return userDto.entityToDto(registeredUser);
    }

    public void deleteUserInfo(long id) {
        registeredUserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Registered user id does not exist"));
        registeredUserRepository.deleteById(id);
    }

    public void deleteUser(long sessionId) {
        userRepository.deleteById(sessionId);
    }

    public void deleteAllSessionIds() {
        userRepository.deleteAll();
    }

    public void deleteAllUserInfo() {
        registeredUserRepository.deleteAll();
    }
}
