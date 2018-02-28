package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PotLuck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String foodName;

    private String foodServes;

    private String foodType;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppUser> itemUser;

    public void addItemUser(AppUser s){this.itemUser.add(s);}

    public PotLuck() {
        this.itemUser=new HashSet<>();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodServes() {
        return foodServes;
    }

    public void setFoodServes(String foodServes) {
        this.foodServes = foodServes;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public Set<AppUser> getItemUser() {
        return itemUser;
    }

    public void setItemUser(Set<AppUser> itemUser) {
        this.itemUser = itemUser;
    }

    @Override
    public String toString() {
        return "PotLuck{" +
                "id=" + id +
                ", foodName='" + foodName + '\'' +
                ", foodServes='" + foodServes + '\'' +
                ", foodType='" + foodType + '\'' +
                ", itemUser=" + itemUser +
                '}';
    }
}
