package ru.job4j.socialmedia.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.job4j.socialmedia.dto.UserDto;
import ru.job4j.socialmedia.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    List<UserDto> map(List<User> user);
}
