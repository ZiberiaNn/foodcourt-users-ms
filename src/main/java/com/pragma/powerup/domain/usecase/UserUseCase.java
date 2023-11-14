package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.exception.ForbiddenException;
import com.pragma.powerup.domain.exception.invalid.InvalidBirthDateException;
import com.pragma.powerup.domain.exception.invalid.InvalidEmailException;
import com.pragma.powerup.domain.exception.invalid.InvalidPhoneException;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.model.enums.RoleEnum;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final String PHONE_PATTERN =
            "^\\+\\d{1,12}$";
    private static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public UserModel saveOwner(UserModel userModel) {
        if (!emailPattern.matcher(userModel.getEmail()).matches()) {
            throw new InvalidEmailException();
        } else if (!phonePattern.matcher(userModel.getPhone()).matches()) {
            throw new InvalidPhoneException();
        } else if (Period.between(userModel.getBirthDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), LocalDate.now())
                        .getYears() < 18) {
            throw new InvalidBirthDateException();
        }
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userModel.getRoles().forEach(role -> {
            if (role.getName().equalsIgnoreCase(RoleEnum.ADMIN.getName())
                    && loggedUser.getAuthorities().stream().noneMatch(authority ->
                    authority.getAuthority().equalsIgnoreCase(RoleEnum.ADMIN.getNameWithRolePrefix()))
            ) {
                throw new ForbiddenException("Only admins can create admins");
            } else if (role.getName().equalsIgnoreCase(RoleEnum.OWNER.getName())
                    && loggedUser.getAuthorities().stream().noneMatch(authority ->
                    authority.getAuthority().equalsIgnoreCase(RoleEnum.ADMIN.getNameWithRolePrefix()))
            ) {
                throw new ForbiddenException("Only admins can create owners");
            } else if (role.getName().equalsIgnoreCase(RoleEnum.EMPLOYEE.getName())
                    && loggedUser.getAuthorities().stream().noneMatch(authority ->
                    authority.getAuthority().equalsIgnoreCase(RoleEnum.OWNER.getNameWithRolePrefix()))
            ) {
                throw new ForbiddenException("Only owners can create employees");
            }
        });
        return userPersistencePort.saveUser(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public UserModel getUserById(Long userId) {
        return userPersistencePort.getUserById(userId);
    }
    @Override
    public UserModel getUserByIdentityNumber(Integer identityNumber) {
        return userPersistencePort.getUserByIdentityNumber(identityNumber);
    }
    @Override
    public UserModel getUserByEmail(String email) {
        return userPersistencePort.getUserByEmail(email);
    }

}