package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner{

    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PotLuckRepository potLuckRepository;


    @Override
    public void run(String... strings) throws Exception {

        AppRole role = new AppRole();
        role.setRoleName("USER");
        appRoleRepository.save(role);

        role = new AppRole();
        role.setRoleName("ADMIN");
        appRoleRepository.save(role);

        // A few users
        // User 1
        AppUser user = new AppUser();
        user.setUsername("John");
        user.setPassword("password1");
        user.setFullName("John Doe");
        user.setUserEmail("g1@gmail.com");
        appUserRepository.save(user);
        user.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(user);
        // User 2
        user = new AppUser();
        user.setUsername("Jacob");
        user.setPassword("password2");
        user.setFullName("Jacob Smith");
        user.setUserEmail("g2@gmail.com");
        appUserRepository.save(user);
        user.addRole(appRoleRepository.findAppRoleByRoleName("ADMIN"));
        appUserRepository.save(user);
        // User 3
        user = new AppUser();
        user.setUsername("Joe");
        user.setPassword("password3");
        user.setFullName("Joe Blow");
        user.setUserEmail("g3@gmail.com");
        appUserRepository.save(user);
        user.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(user);
        // User 4
        user = new AppUser();
        user.setUsername("Jane");
        user.setPassword("password4");
        user.setFullName("Jane Pane");
        user.setUserEmail("g4@gmail.com");
        appUserRepository.save(user);
        user.addRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(user);


        //Load 15 initial prodcuts here

        PotLuck newPotluck = new PotLuck();
        newPotluck.setFoodName("Pasta");
        newPotluck.setFoodServes("5");
        newPotluck.setFoodType("Food");
        potLuckRepository.save(newPotluck);

        newPotluck = new PotLuck();
        newPotluck.setFoodName("Coke");
        newPotluck.setFoodServes("8");
        newPotluck.setFoodType("Drink");
        potLuckRepository.save(newPotluck);

        newPotluck = new PotLuck();
        newPotluck.setFoodName("Cake");
        newPotluck.setFoodServes("10");
        newPotluck.setFoodType("Dessert");
        potLuckRepository.save(newPotluck);




        PotLuck potLuck = potLuckRepository.findOne(new Long(1));
        potLuck.addItemUser(appUserRepository.findOne(new Long(1)));
        potLuckRepository.save(potLuck);
        potLuck = potLuckRepository.findOne(new Long(2));
        potLuck.addItemUser(appUserRepository.findOne(new Long(2)));
        potLuckRepository.save(potLuck);
        potLuck = potLuckRepository.findOne(new Long(3));
        potLuck.addItemUser(appUserRepository.findOne(new Long(2)));
        potLuckRepository.save(potLuck);









    }
}
