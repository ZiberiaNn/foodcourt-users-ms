package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.model.RoleModel;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.Collection;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    @Mapping(source = "rolesIdList", target = "roles", qualifiedByName = "roleIdsToRoleModels")

    UserModel toUserModel(UserRequestDto userRequestDto);
    @Named("roleIdsToRoleModels")
    static Collection<RoleModel> roleIdsToRoleModels(Collection<Long> roleIds) {
        if ( roleIds == null ) {
            return null;
        }
        Collection<RoleModel> roleModels = new ArrayList<>( roleIds.size() );
        for ( Long roleId : roleIds ) {
            roleModels.add( RoleModel.builder().id(roleId).build() );
        }
        return roleModels;
    }
}
