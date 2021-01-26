package com.example.sprint4.controller;

import com.example.sprint4.dto.RoleDTO;
import com.example.sprint4.dto.UserManagerDTO;
import com.example.sprint4.model.Cart;
import com.example.sprint4.model.Role;
import com.example.sprint4.model.User;
import com.example.sprint4.service.RoleService;
import com.example.sprint4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    @GetMapping("list")
    public ResponseEntity<List<UserManagerDTO>> getListUser() {
        List<User> cartList = userService.findAll();
        List<UserManagerDTO> userManagerDTOS = new ArrayList<>();
        for (User user : cartList) {
            UserManagerDTO userManagerDTO = new UserManagerDTO();
            userManagerDTO.setIdUser(user.getIdUser());
            userManagerDTO.setFullName(user.getFullName());
            userManagerDTO.setUsername(user.getUsername());
            userManagerDTO.setPassword(user.getPassword());
            userManagerDTO.setImage(user.getImage());
            userManagerDTO.setEmail(user.getEmail());
            userManagerDTO.setAddress(user.getAddress());
            userManagerDTO.setNumberPhone(user.getNumberPhone());
            userManagerDTOS.add(userManagerDTO);
        }
        return new ResponseEntity<>(userManagerDTOS, HttpStatus.OK);
    }

    @GetMapping("{idUser}")
    public ResponseEntity<UserManagerDTO> getUser(@PathVariable Long idUser) {
        User user = userService.findById(idUser);
        UserManagerDTO userManagerDTO = new UserManagerDTO();
        userManagerDTO.setIdUser(user.getIdUser());
        userManagerDTO.setFullName(user.getFullName());
        userManagerDTO.setUsername(user.getUsername());
        userManagerDTO.setPassword(user.getPassword());
        userManagerDTO.setImage(user.getImage());
        userManagerDTO.setEmail(user.getEmail());
        userManagerDTO.setAddress(user.getAddress());
        userManagerDTO.setNumberPhone(user.getNumberPhone());
        return new ResponseEntity<>(userManagerDTO, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<Void> createUser(@RequestBody UserManagerDTO userManagerDTO) {
//        User user = userService.findById(idUser);
        User user = new User();
//        user.setIdUser(userManagerDTO.getIdUser());
        user.setFullName(userManagerDTO.getFullName());
        user.setUsername(userManagerDTO.getUsername());
        user.setPassword(userManagerDTO.getPassword());
        user.setRole(roleService.findById((long) 1));
        user.setImage(userManagerDTO.getImage());
        user.setEmail(userManagerDTO.getEmail());
        user.setAddress(userManagerDTO.getAddress());
        user.setNumberPhone(userManagerDTO.getNumberPhone());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("edit/{idUser}")
    public ResponseEntity<Void> updateUser(@RequestBody UserManagerDTO userManagerDTO,@PathVariable Long idUser) {
        User user = userService.findById(idUser);
        user.setIdUser(userManagerDTO.getIdUser());
        user.setFullName(userManagerDTO.getFullName());
        user.setUsername(userManagerDTO.getUsername());
        user.setPassword(userManagerDTO.getPassword());
        user.setRole(roleService.findById((long) 1));
        user.setImage(userManagerDTO.getImage());
        user.setEmail(userManagerDTO.getEmail());
        user.setAddress(userManagerDTO.getAddress());
        user.setNumberPhone(userManagerDTO.getNumberPhone());
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("listRole")
    public ResponseEntity<List<RoleDTO>> getAllRole() {
        List<Role> roles = roleService.findAll();
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for (Role role: roles){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setIdRole(role.getIdRole());
            roleDTO.setRoleName(role.getRoleName());
            roleDTOS.add(roleDTO);
        }
        return new ResponseEntity<>(roleDTOS, HttpStatus.OK);
    }

    @DeleteMapping("delete/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        if (idUser == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        userService.deleteById(idUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/change-image")
    public ResponseEntity<Void> updateImage(@RequestParam("idUser") Long idUser, @RequestParam("image") String image) {
        User user = userService.findById(idUser);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            user.setImage(image);
            userService.save(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
