package com.example.demo;

import org.springframework.data.repository.CrudRepository;

import java.util.HashSet;
import java.util.List;

public interface PotLuckRepository  extends CrudRepository<PotLuck,Long>{

    Iterable<PotLuck> findAllByFoodNameContains(String search);
    //AppUser findAllById(long id);
    //List<AppUser> findByUsername(String userName);
    Iterable<PotLuck> findAllByItemUser(AppUser itemUser);

}
