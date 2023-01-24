package com.rest.books.bootrestbooks;

import com.rest.books.bootrestbooks.Entities.Role;
import com.rest.books.bootrestbooks.Repositories.RoleRepo;
import com.rest.books.bootrestbooks.config.AppConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class BootRestBooksApplication implements CommandLineRunner {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;


    @Bean
    public ModelMapper modelMapper(){

        return new ModelMapper();
    }


    public static void main(String[] args) {

        SpringApplication.run(BootRestBooksApplication.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        try {
            Role role1 = new Role(AppConstants.ROLE_ADMIN,"ROLE_ADMIN");


            Role role2 = new Role(AppConstants.ROLE_NORMAL,"ROLE_NORMAL");

            Set<Role> roles = Set.of(role1, role2);
            List<Role> roleList = this.roleRepo.saveAll(roles);

            // to see which roles it saved
            roleList.forEach(r->{
                System.out.println(r.getName());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
