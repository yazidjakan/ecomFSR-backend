package com.ecom.clients_service.service.facade;

import com.ecom.clients_service.dto.UserDto;

public interface UserService extends AbstractService<UserDto, Long> {
    UserDto findByUsername(String username);
}
