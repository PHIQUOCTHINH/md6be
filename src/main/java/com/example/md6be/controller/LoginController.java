package com.example.md6be.controller;

import com.example.md6be.model.*;
import com.example.md6be.service.IAddressService;
import com.example.md6be.service.IAppUserService;
import com.example.md6be.service.ICustomerService;
import com.example.md6be.service.IMerchantService;
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
    @Autowired
    IMerchantService merchantService;
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
                }else if (role.getName().equals("ROLE_CUSTOMER")){
                    Customer customer = customerService.findCustomerByAppUser(appUser1);
                    System.out.println(customer);
                    if (customer.getIsActive() && customer.getIsAccept()){
                        return new ResponseEntity<>(appUser1, HttpStatus.OK);
                    }
                }else if (role.getName().equals("ROLE_MERCHANT")){
                    Merchant merchant = merchantService.findByAppUser(appUser1);
                    if (merchant.getIsActive() && merchant.getIsAccept()){
                        return new ResponseEntity<>(appUser1, HttpStatus.OK);
                    }
                }
            }
        }
        return null;
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

    @PostMapping("/register-merchant")
    public ResponseEntity<?>registerMerchant(@RequestBody Merchant merchant) {
        AppUser appUser = appUserService.findByUserName(merchant.getAppUser().getUsername());
        if (appUser!=null){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }else {
            appUserService.save(merchant.getAppUser());
            merchantService.save(merchant);
            return new ResponseEntity<>(merchant.getAppUser(), HttpStatus.OK);
        }
    }

    @GetMapping("/get-customer")
    public ResponseEntity<?>findAllCustomer() {
        return new ResponseEntity<>(customerService.findActiveCustomer(), HttpStatus.OK);

    }

}
