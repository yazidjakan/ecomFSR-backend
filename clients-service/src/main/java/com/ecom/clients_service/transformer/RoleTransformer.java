package com.ecom.clients_service.transformer;

import com.ecom.clients_service.dto.RoleDto;
import com.ecom.clients_service.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleTransformer extends AbstractTransformer<Role, RoleDto> {
    @Override
    public Role toEntity(RoleDto dto) {
        if(dto == null) {
            return null;
        }else{
            Role entity=new Role();
            entity.setId(dto.id());
            entity.setName(dto.name());
            return entity;
        }
    }

    @Override
    public RoleDto toDto(Role entity) {
        if(entity == null) {
            return null;
        }else{
            RoleDto dto=new RoleDto(
                    entity.getId(),
                    entity.getName()
            );
            return dto;
        }
    }
}
