package com.music.user.service.mapper;

import com.music.user.service.dto.UserDto;
import com.music.user.service.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class DtoMapper {

    public abstract User map(UserDto userDto);

    public abstract UserDto map(User user);
}
