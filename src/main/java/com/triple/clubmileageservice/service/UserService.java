package com.triple.clubmileageservice.service;

import com.triple.clubmileageservice.domain.entity.UserEntity;
import com.triple.clubmileageservice.dto.UserDto;
import com.triple.clubmileageservice.repository.UserEntityRepository;
import com.triple.clubmileageservice.vo.ResponseUserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserEntityRepository userEntityRepository;

    public ResponseUserVo createUser(String userEmail) {
        UserDto userDto = UserDto.createUserDto(userEmail);
        UserEntity userEntity = new UserEntity(userDto.getUserId(), userDto.getUserEmail());
        UserEntity save = userEntityRepository.save(userEntity);
        return new ResponseUserVo(save.getId(), save.getEmail());
    }

    public List<ResponseUserVo> findAllUser() {
        List<UserEntity> users = userEntityRepository.findAll();
        List<ResponseUserVo> responseUserVos = new ArrayList<>();
        for (UserEntity user : users) {
            responseUserVos.add(new ResponseUserVo(user.getId(), user.getEmail()));
        }
        return responseUserVos;
    }
}
