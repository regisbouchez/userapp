package com.bnpp.userapp.exposition;

import com.bnpp.userapp.domain.User;
import com.bnpp.userapp.infrastructure.UserDAO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserRessource {
    @Autowired
    UserDAO userDAO;

    @RequestMapping(method = RequestMethod.GET, path = {"/user/{userid}"})
    public UserDTO detailUser(@PathVariable("userid") Long userid) {
        return AdaptateurDTO.convertToUserDTO(userDAO.findById(userid).orElse(null));
    }

    @RequestMapping(method = RequestMethod.POST, path = {"/user/"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser (@Valid @RequestBody UserDTO userDTO) {
        userDAO.save(AdaptateurDTO.convertToUser(userDTO));
    }
    @RequestMapping(method = RequestMethod.PUT, path = {"/user/{userid}"})
    public void updateUser (@PathVariable("userid") Long userid, @Valid @RequestBody UserDTO userDTO) throws NotFoundException {
        User user = AdaptateurDTO.convertToUser(userDTO);
        if (userDAO.findById(userid).isPresent()) {
           user.setId(userid);
           userDAO.save(user);}
        else {
            throw new NotFoundException("pas  de user correspondant : " + userid);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, path = {"/user/{userid}"})
    public void deleteUser(@PathVariable("userid") Long userid) throws NotFoundException {
        if (userDAO.findById(userid).isPresent()) {
            userDAO.deleteById(userid);
        } else {
            throw new NotFoundException("le user à supprimer n'existe pas  : " + userid);
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/user/findall"})
    public List<UserDTO> findall() {
        return AdaptateurDTO.convertToUserDTOList(userDAO.findAll());
    }

}
