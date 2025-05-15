package br.edu.unichristus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.edu.unichristus.domain.dto.user.UserDTO;
import br.edu.unichristus.domain.dto.user.UserLowDTO;
import br.edu.unichristus.domain.dto.user.UserRolesDTO;
import br.edu.unichristus.domain.model.User;
import br.edu.unichristus.exception.CommonsException;
import br.edu.unichristus.repository.UserRepository;
import br.edu.unichristus.utils.MapperUtil;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserLowDTO save(UserDTO user){
        if(repository.findByLogin(user.getLogin()).isPresent()){
            throw new CommonsException(HttpStatus.CONFLICT,
                    "unichristus.user.login.conflict",
                    "Usuario já existe!");
        }

        if (user.getName() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.user.name.badrequest",
                    "Nome do usuário é um campo obrigatório");
        }

        var userEntity = MapperUtil.parseObject(user, User.class);
        var savedUser = repository.save(userEntity);
        return MapperUtil.parseObject(savedUser, UserLowDTO.class);
    }

    public UserLowDTO update(Long id, UserDTO userDTO) {
        if (userDTO.getName() == null) {
            throw new CommonsException(HttpStatus.BAD_REQUEST,
                    "unichristus.user.name.badrequest",
                    "Nome do usuário é obrigatório para atualização!");
        }

        var existingUser = repository.findById(id);
        if (existingUser.isEmpty()) {
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.user.update.notfound",
                    "Usuário não encontrado para atualização!");
        }

        var userEntity = existingUser.get();

        var userWithSameLogin = repository.findByLogin(userDTO.getLogin());
        if (userWithSameLogin.isPresent() && !userWithSameLogin.get().getId().equals(id)) {
            throw new CommonsException(HttpStatus.CONFLICT,
                    "unichristus.user.login.conflict",
                    "Login já está em uso por outro usuário!");
        }


        userEntity.setName(userDTO.getName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setLogin(userDTO.getLogin());
        userEntity.setPassword(userDTO.getPassword());

        var updatedUser = repository.save(userEntity);
        return MapperUtil.parseObject(updatedUser, UserLowDTO.class);
    }


    public List<UserLowDTO> findAll(){
        var listUsers = repository.findAll();
        return MapperUtil.parseListObjects(listUsers, UserLowDTO.class);
    }

    public User findById(Long id){
        var userEntity = repository.findById(id);
        if(userEntity.isEmpty()){
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.user.findbyid.notfound",
                    "Usuário não encontrado!");
        }

        return repository.findById(id).get();
    }

    public void delete(Long id){
        var userEntity = repository.findById(id);

        if(userEntity.isEmpty()){
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.user.delete.notfound",
                    "Usuário não encontrado!");
        }
        repository.deleteById(id);
    }

    public UserRolesDTO getRolesByUserId(Long id){
        var userEntity = repository.findById(id);
        if(userEntity.isEmpty()){
            throw new CommonsException(HttpStatus.NOT_FOUND,
                    "unichristus.user.findbyid.notfound",
                    "Usuário não encontrado!");
        }
        var userDTO = MapperUtil.parseObject(userEntity, UserRolesDTO.class);
        userDTO.setRoles(new String[]{"MANAGER", "ADMIN", "COMMON_USER"});

        return userDTO;
    }


}
