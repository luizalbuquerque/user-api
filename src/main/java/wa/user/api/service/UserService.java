package wa.user.api.service;

import wa.user.api.entity.UserEntity;

import java.util.List;

public interface UserService {
    public List<UserEntity> listUser();

    public void newUser();
}
