package com.example.md6be.controller;

import com.example.md6be.model.*;
import com.example.md6be.service.impl.AddressService;
import com.example.md6be.service.impl.AppUserService;
import com.example.md6be.service.impl.CustomerService;
import com.example.md6be.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
//@RequestMapping("/api/login")
public class LoginController {
//    @Autowired
//    JwtService jwtService;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    AppUserService appUserService;

    @Autowired
    MerchantService merchantService;

    @Autowired
    CustomerService customerService;

    @Autowired
    AddressService addressService;

    @Autowired
    SendMailController sendMailController;
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
