package wa.user.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import wa.user.api.dto.UserDTO;
import wa.user.api.entity.UserEntity;
import wa.user.api.exeption.BusinessException;
import wa.user.api.repository.UserRepository;
import wa.user.api.service.UserService;
import wa.user.api.util.ConstantUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> listUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO newUser(UserDTO user) {
       try {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setDocument(user.getDocument());
        userRepository.save(entity);
        return user;
        } catch (
            DataIntegrityViolationException e) {
            throw new BusinessException(ConstantUtils.USUARIO_DUPLICADO);
         }
    }

}
