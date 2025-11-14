package com.app.backend.config;

import com.app.backend.model.User;
import com.app.backend.model.Category;
import com.app.backend.repository.UserRepository;
import com.app.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception{
        System.out.println("Ejecutando DataInitializer...");

        //eliminar y recrear usuarios para asegurar contraseñas correctas
        if(userRepository.existsByUsername("admin")){
            User existingAdmin = userRepository.findByUsername("admin").orElse(null);
            if (existingAdmin != null){
            userRepository.delete(existingAdmin);
            System.out.println("Usuario admin existente eliminado");
        }
    }

    if(userRepository.existsByUsername("coordinador")){
            User existingCoord = userRepository.findByUsername("Coord").orElse(null);
            if (existingCoord != null){
            userRepository.delete(existingCoord);
            System.out.println("Usuario Coordinador existente eliminado");
        }
    }

    //CrearUsuarioAdmin
    User admin = new User();
    admin.setUsername("admin");
    admin.setPassword(passwordEncoder.encode("admin123"));
    admin.setEmail("admin@app.com");
    admin.setRole(User.Role.ADMIN);
    admin.setActive(true);
    userRepository.save(admin);
    System.out.println("Usuario Admin Creado - username: admin, password: admin123");

    //CrearUsuarioAdmin
    User coordinador = new User();
    admin.setUsername("coordinador");
    admin.setPassword(passwordEncoder.encode("coord123"));
    admin.setEmail("coordinador@app.com");
    admin.setRole(User.Role.COORDINADOR);
    admin.setActive(true);
    userRepository.save(coordinador);
    System.out.println("Usuario coordinador Creado - username: coordinadro, password: coord123");
    System.out.println("Data Initializer completado exitosamente");
}
    
}
