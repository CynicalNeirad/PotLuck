package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;

public interface AppUserRepository  extends CrudRepository<AppUser,Long>{

    AppUser findAppUserByUsername(String username);
   // AppUser findAllByUsername(String username);
}
