package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    AppRoleRepository appRoleRepository;

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PotLuckRepository potLuckRepository;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

    @RequestMapping("/")
    public String showIndex(Model model){
        model.addAttribute("potLuckItems", potLuckRepository.findAll());
        return "mainPage";

    }

    @RequestMapping("/userpage")
    public String productList(Model model, Authentication authentication){
        AppUser user = appUserRepository.findAppUserByUsername(authentication.getName());
        model.addAttribute("potLuckItems", potLuckRepository.findAllByItemUser(user));
        return "mainPage";
    }

    @RequestMapping("/appuserform")
    public String userRegistration(Model model){
        model.addAttribute("appuser",new AppUser());
        return "appuserform";
    }

    @RequestMapping(value="/appuserform",method= RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("appuser") AppUser appuser, BindingResult result, Model model){
        model.addAttribute("appuser",appuser);
        if(result.hasErrors()){
            return "appuserform";
        }else{
            model.addAttribute("message","User Account Successfully Created");
            appUserRepository.save(appuser);
        }
        return "redirect:/";
    }
    @GetMapping("/addfoodpage")
    public String addProduct(Model model){
        PotLuck potLuck = new PotLuck();
        potLuckRepository.save(potLuck);
        model.addAttribute("users", appUserRepository.findAll());
        model.addAttribute("potluckitem", potLuck);
        return "addFoodPage";
    }
    @PostMapping("/processfooditem")
    public String processItem(@Valid @ModelAttribute("product") PotLuck potLuck, Model model, BindingResult result, Authentication authentication){
        if(result.hasErrors()){
            return "addFoodPage";
        }
        else{
            /*if (potLuck.getItemUser() == null){*/
                potLuck.addItemUser(appUserRepository.findAppUserByUsername(authentication.getName()));
                potLuckRepository.save(potLuck);

                
            return "redirect:/";
        }
    }
    @PostMapping("/search")
    public String search(HttpServletRequest request, Model model){

        String searchString = request.getParameter("search");
        Iterable<PotLuck> potLuckList = potLuckRepository.findAllByFoodNameContains(searchString);
        model.addAttribute("potLuckItems", potLuckList);
        return "mainPage";
    }

    @RequestMapping("/remove/{id}")
    public String processCheckout(@PathVariable("id") long id, Model model) {
        potLuckRepository.delete(id);
        model.addAttribute("potLuckItems", potLuckRepository.findAll());
        return "mainPage";
    }
    @GetMapping("/admintools")
    public String admintools(Model model) {
        model.addAttribute("users", appUserRepository.findAll());
        return "adminTools";
    }
    @RequestMapping("/promote/{id}")
    public String promoteUser(@PathVariable("id") long id, Model model) {
        AppUser appUser = appUserRepository.findOne(id);
        appUser.addRole(appRoleRepository.findAppRoleByRoleName("ADMIN"));
        appUser.removeRole(appRoleRepository.findAppRoleByRoleName("USER"));
        appUserRepository.save(appUser);
        model.addAttribute("users", appUserRepository.findAll());
        return "adminTools";
    }

}

