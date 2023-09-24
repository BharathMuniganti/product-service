package com.products.mapper;


import com.products.dto.UserDTO;
import com.products.entity.User;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper(componentModel="spring")
@Component
public interface UserMapper {

    @Named("entityToDTO")
    UserDTO entityToDTO(User entity);

    @Named("dtoToEntity")
    User dtoToEntity(UserDTO dto);

    @IterableMapping(qualifiedByName = "entityToDTO")
    List<UserDTO> entitiesToDTOs(List<User> entityList);

    @IterableMapping(qualifiedByName = "dtoToEntity")
    List<User> dtosToEntities(List<UserDTO> dtoList);


}
