package com.app.backend.service;

import com.app.backend.dto.UserCreateRequest;
import com.app.backend.UserUpdateRequest;
import com.app.backend.model.User;
import com.app.backend.repository.UserRepository;
import org.springframework.beans.factory.anotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.list;

@Service 
public class UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new RunTimeException("Usuario no encontrado"));
    }

    public User create(UserCreateRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setActive(request.getActive() != null ? request.getActive(): true);
        return userRepository.save(user);
    }

    public User update(Long id, UserUpdateRequest request){
        User user = findById(id);

        //Validdar que el coordinador no pueda modificar el admin principal

        if(id == 1L && isCoordinador()) {
            throw new RunTimeException("No tienes permiso para modificar el administrador principal");
        }
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        user.setActive(request.getActive());

        if(request.getPassword() != null && !request.getPassword().isEmpty()){
            user.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return userRepository.save(user);
    }

    public Boolean isCoordinador(){
        Authentication authentication = securityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.getAuthorities() != null){
            return authentication.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_COORDINADOR"));
        }
        return false;
    }

    public void delete(Long id){
        User user = findById(id);
        
        // validar que no se elimine el usuario admin principal

        if(id == 1L){
            throw new RunTimeException("No se puede eliminar el administrador principal");
        }

        //validar que el usuario exista
        if(user ==  null){
            throw new RunTimeException("Usuario no encontrado");
        }

        userRepository.delete(user);
    }
}