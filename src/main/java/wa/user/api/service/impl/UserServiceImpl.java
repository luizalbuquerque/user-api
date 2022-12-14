package wa.user.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wa.user.api.entity.UserEntity;
import wa.user.api.repository.UserRepository;
import wa.user.api.service.UserService;

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
    public void newUser(){
            UserEntity entity = new UserEntity();
            entity.setName(entity.getName());
            entity.setDocument(entity.getDocument());
            entity.setCreatedAt(entity.getCreatedAt());
            userRepository.save(entity);
    }
}
