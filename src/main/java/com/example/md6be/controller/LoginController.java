package com.example.md6be.controller;

import com.example.md6be.model.Address;
import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;
import com.example.md6be.model.Role;
import com.example.md6be.service.IAddressService;
import com.example.md6be.service.IAppUserService;
import com.example.md6be.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/login-register")
public class LoginController {
    @Autowired
    IAppUserService appUserService;

    @Autowired
    ICustomerService customerService;
    @Autowired
    IAddressService addressService;
    @GetMapping
    public ResponseEntity<List<AppUser>>findAll() {
        return new ResponseEntity<>(appUserService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody AppUser appUser) {
        AppUser appUser1 = appUserService.findAppUserByPasswordAndUsername(appUser.getPassword(), appUser.getUsername());
        if (appUser1!=null){
            for (Role role: appUser1.getRoles()) {
                if (role.getName().equals("ROLE_ADMIN")){
                    return new ResponseEntity<>(appUser1, HttpStatus.OK);
                }else {
                    Customer customer = customerService.findCustomerByAppUser(appUser1);
                    System.out.println(customer);
                    if (customer.getIsActive() && customer.getIsAccept()){
                        return new ResponseEntity<>(appUser1, HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get")
    public ResponseEntity<?>findAllByPass(@RequestBody AppUser appUser) {

        AppUser appUser1 = appUserService.findAppUserByPassword(appUser.getPassword());
        if (appUser1 !=null){
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/register-customer")
    public ResponseEntity<?>registerCustomer(@RequestBody Customer customer) {
        AppUser appUser = appUserService.findByUserName(customer.getAppUser().getUsername());
        if (appUser!=null){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }else {
            appUserService.save(customer.getAppUser());
            customerService.save(customer);

            Customer customer1 = customerService.findCustomerByUserName(customer.getAppUser().getUsername());
            Address address = new Address();
            address.setNameAddress(customer1.getAddress());
            address.setCustomer(customer1);
            addressService.save(address);
            return new ResponseEntity<>(customer.getAppUser(), HttpStatus.OK);
        }

    }

    @GetMapping("/get-customer")
    public ResponseEntity<?>findAllCustomer() {
        return new ResponseEntity<>(customerService.findActiveCustomer(), HttpStatus.OK);

    }


}
