package com.user.user.services;

import com.user.user.dtos.UserRecordDto;
import com.user.user.models.UserModel;
import com.user.user.repositories.UserRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserModel userModel = new UserModel();

    public ResponseEntity<Object> findById(UUID id) {
        Optional<UserModel> user = userRepository.findById(id);

        if(user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);

    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public UserModel createUser(UserRecordDto userRecordDto) {
        BeanUtils.copyProperties(userRecordDto, userModel);
        return userRepository.save(userModel);
    }

    @Transactional
    public ResponseEntity<Object> updateUser(UUID id, UserRecordDto userRecordDto) {
        Optional<UserModel> user0 = userRepository.findById(id);

        this.userModel = user0.get();
        return ResponseEntity.status(HttpStatus.OK).body(createUser(userRecordDto));

    }

    @Transactional
    public ResponseEntity<Object> deleteUser(UUID id) {
        Optional<UserModel> user0 = userRepository.findById(id);

        userRepository.delete(user0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário excluido com sucesso!");
    }




}
