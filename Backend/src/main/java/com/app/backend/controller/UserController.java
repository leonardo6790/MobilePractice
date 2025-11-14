package com.app.backend.controller;

import com.app.backend.dto.UserCreateRequest;
import com.app.backend.dto.UserUpdateRequest;
import com.app.backend.model.Category;
import com.app.backend.service.CategoryService;
import com.app.backend.dto.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.Http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.acces.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserService userService;
    
    @GetMapping
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<List<User>> getAllUsers(){
    }

    @GetMapping("/{id}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<User> createUser(@RequestBody UserCreateRequest request){
        return ResponseEntity.ok(userService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorized("hasAnyRole('ADMIN','COORDINADOR')")
    public ResponseEntity<?> updateUser(@PathVariable Long id,@Valid @RequestBody UserUpdateRequest request) {
        try{
            return ResponseEntity.ok(userService.update(id,user));
        } catch(RuntimeException e){
            if(e.getMessage().contains("no tiene permisos")){
                return ResponseEntity.status(403).body(new MessageResponse(e.getMessage()));
            }
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorized("hasAnyRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteuser(@PathVariable Long id) {
        try{
            userService.delete(id);
            return ResponseEntity.ok(MessageResponse("Usuario eliminado exitosamente"));
        } catch(RuntimeException e){
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    } 
}




