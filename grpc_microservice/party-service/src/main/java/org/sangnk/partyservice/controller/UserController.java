package org.sangnk.partyservice.controller;



import lombok.extern.slf4j.Slf4j;
import org.sangnk.partyservice.dto.input.UserCreationInput;
import org.sangnk.partyservice.dto.output.UserDTO;
import org.sangnk.partyservice.service.UserServiceClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    private final UserServiceClient userService;


    public UserController(UserServiceClient userService) {
        this.userService = userService;
    }


    /**
     * Unary synchronous call
     * In case of error - send back a status explicitly
     */
    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody UserCreationInput input) {
        Integer id = userService.create(input.getName());
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }


    /**
     * Unary synchronous call
     */
    @GetMapping("{id}")
    public ResponseEntity<UserDTO> getById(@PathVariable Integer id) {
        UserDTO user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }




    /**
     * Unidirectional server-side streaming
     */
    @GetMapping("all")
    public ResponseEntity<List<UserDTO>> getAll() {
        log.info("All users requested");
        List<UserDTO> all = userService.getAll();
        log.info("All users received");
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    /**
     * Unidirectional client-side streaming
     */
    @DeleteMapping("multiple")
    public ResponseEntity deleteMultiple(@RequestParam List<Integer> ids) {
        log.info("Going to remove multiple users");
        userService.deleteMultiple(ids);
        log.info("Users removed");
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
