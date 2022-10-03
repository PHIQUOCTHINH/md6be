package com.example.md6be.controller;

import com.example.md6be.model.Address;
import com.example.md6be.model.AppUser;
import com.example.md6be.model.Customer;
import com.example.md6be.model.Merchant;
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

//    @PostMapping("/login")
//    public UserToken login(@RequestBody AppUser appUser) {
//        try {
//            // Tạo ra 1 đối tượng Authentication.
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            String token = jwtService.createToken(authentication);
//            AppUser appUser1 = appUserService.findByUserName(appUser.getUsername());
//
//
//            for (Role element : appUser1.getRoles()) {
//                System.out.println(element);
//                if ((element.getName().equals("ROLE_CUSTOMER") || element.getName().equals("ROLE_ADMIN")) && appUser1.getRoles().size() == 1) {
//                    return new UserToken(appUser1.getId(), appUser1.getUsername(), token, appUser1.getRoles());
//                } else {
//                    Merchant merchant = merchantService.findByAppUser(appUser1);
//                    if (merchant.getIsAccept()) {
//                        return new UserToken(appUser1.getId(), appUser1.getUsername(), token, appUser1.getRoles());
//                    } else {
//                        return null;
//                    }
//                }
//            }
//
//        } catch (Exception e) {
//            return null;
//        }
//        return null;
//    }

    @PostMapping("/registerMerchant")
    public ResponseEntity<AppUser> register(@RequestBody Merchant merchant){
        appUserService.save(merchant.getAppUser());
        merchantService.save(merchant);
        return new ResponseEntity<>(merchant.getAppUser(), HttpStatus.OK);
    }

    @PostMapping("/registerCustomer")
    public ResponseEntity<AppUser> register(@RequestBody Customer customer){
        appUserService.save(customer.getAppUser());
        customerService.save(customer);
        sendMailController.sendEmail(customer.getAppUser());
        // tạo phương thức tìm Customer theo userName
        Customer customer1 = customerService.findCustomerByUserName(customer.getAppUser().getUsername());
        // add vào address để tạo đối tượng
        Address address = new Address();
        address.setNameAddress(customer1.getAddress());
        address.setCustomer(customer1);
        addressService.save(address);
        return new ResponseEntity<>(customer.getAppUser(), HttpStatus.OK);
    }
    @PostMapping("/checkUserName")
    public ResponseEntity<Boolean> register(@RequestBody String userName){
        AppUser appUser = appUserService.findByUserName(userName);
        if (appUser != null) {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
    }

    @PostMapping("/getMerchant")
    public ResponseEntity<Merchant> getSellerByAppUser(@RequestBody String userName){
        AppUser appUser = appUserService.findByUserName(userName);
        Merchant merchant = merchantService.findByAppUser(appUser);
        return new ResponseEntity<>(merchant, HttpStatus.OK);
    }

    @PostMapping("/getCustomer")
    public ResponseEntity<Customer> getCustomerByAppUser(@RequestBody String userName){
        AppUser appUser = appUserService.findByUserName(userName);
        Customer customer = customerService.findCustomerByAppUser(appUser);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
