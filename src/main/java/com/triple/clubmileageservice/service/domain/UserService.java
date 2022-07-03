package com.triple.clubmileageservice.service.domain;

import com.triple.clubmileageservice.domain.entity.UserEntity;
import com.triple.clubmileageservice.dto.UserDto;
import com.triple.clubmileageservice.repository.UserEntityRepository;
import com.triple.clubmileageservice.reqres.UserRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userEntityRepository;

    public UserRes createUser(String userEmail) {
        UserDto userDto = UserDto.createUserDto(userEmail);
        UserEntity userEntity = new UserEntity(userDto.getUserId(), userDto.getUserEmail());
        UserEntity save = userEntityRepository.save(userEntity);
        return new UserRes(save.getUserId(), save.getEmail());
    }

    public List<UserRes> findAllUser() {
        List<UserEntity> users = userEntityRepository.findAll();
        List<UserRes> userResList = new ArrayList<>();
        for (UserEntity user : users) {
            userResList.add(new UserRes(user.getUserId(), user.getEmail()));
        }
        return userResList;
    }

    public UserEntity findUserId(String userId) {
        return userEntityRepository.findByUserId(userId);
    }
}
