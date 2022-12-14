package wa.user.api.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wa.user.api.dto.UserDTO;
import wa.user.api.entity.UserEntity;
import wa.user.api.exeption.ResourceNotFoundException;
import wa.user.api.repository.UserRepository;
import wa.user.api.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<UserEntity> list() {
        return userService.listUser();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO newUser(@RequestBody UserDTO user) {
        return userService.newUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable("id") long id) {
        userRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);}

    @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") long id, @RequestBody UserEntity userAtualizado) {
        UserEntity _user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Não foi encontrado o Cartão com id = " + id));

        _user.setName(userAtualizado.getName());
        _user.setDocument(userAtualizado.getDocument());

        return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
    }

}


