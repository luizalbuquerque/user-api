package wa.user.api.service;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import wa.user.api.dto.UserDTO;
import wa.user.api.entity.UserEntity;
import wa.user.api.exeption.BusinessException;
import wa.user.api.exeption.DatabaseException;
import wa.user.api.exeption.ResourceNotFoundException;
import wa.user.api.repository.UserRepository;
import wa.user.api.util.ConstantUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Transactional(readOnly = true)
    public List<UserEntity> listUser() {
        return userRepository.findAll();
    }

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

    @Transactional(readOnly = true)
    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ConstantUtils.USUARIO_NAO_ENCONTRADO));
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Entity not found with id " + id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    @Transactional
    public UserDTO update(Long id, UserDTO dto) {
        try {
            UserEntity entity = userRepository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            userRepository.save(entity);
            return dto;
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Entity not found with id " + id);
        }
    }

    private void copyDtoToEntity(UserDTO dto, UserEntity entity) {
        entity.setName(dto.getName());
        entity.setDocument(dto.getDocument());
        }
}
