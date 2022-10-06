package com.example.md6be.controller;

import com.example.md6be.model.Customer;
import com.example.md6be.model.Merchant;
import com.example.md6be.service.impl.AppUserService;
import com.example.md6be.service.impl.CustomerService;
import com.example.md6be.service.impl.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin")
public class AdminController {
@Autowired
    AppUserService appUserService;
@Autowired
    MerchantService merchantService;
@Autowired
    SendMailController sendMailController;
@Autowired
    CustomerService customerService;

// quan ly merchant
    //xem danh sach merchant doi chap nhan
    @GetMapping("/accept-merchant")
    private ResponseEntity<List<Merchant>> showWaitingMerchant(){
        List<Merchant> merchants = merchantService.getWaitingAcceptMerchant();
        if (merchants.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(merchants,HttpStatus.OK);
    }
    //chap nhan merchant

    @PostMapping("/accept-merchant/{id}")
    private ResponseEntity<Merchant> acceptMerchant(@PathVariable Long id ){
        Optional<Merchant> merchant = merchantService.findById(id);
        Merchant newMerchant = merchant.get();
        newMerchant.setIsAccept(true);
        merchantService.save(newMerchant);
        sendMailController.sendEmail(newMerchant.getAppUser());
        return new ResponseEntity<>(newMerchant,HttpStatus.CREATED) ;
    }
    // xoa 1 merchant( co the ko dung)
    @DeleteMapping("/delete/{id}")
    private ResponseEntity<String> deleteMerchant(@PathVariable Long id){
        merchantService.deleteSeller(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    // show merchant da duoc chap nhan
    @GetMapping("/showMerchant")
    private ResponseEntity<List<Merchant>> showListMerchant() {
        List<Merchant> merchants = merchantService.showActiveSeller(true);
        return new ResponseEntity<>(merchants, HttpStatus.ACCEPTED);
    }
    // tim merchant theo id
    @GetMapping("/merchant/{id}")
    private ResponseEntity<Merchant> findMerchantById(@PathVariable Long id){
        Merchant merchant = merchantService.findById(id).get();
        return new ResponseEntity<>(merchant,HttpStatus.ACCEPTED);
    }
    // ban và active tài khoản merchant
    @PostMapping("/active-ban-merchant/{id}")
    private ResponseEntity<Merchant> activeBanMerchant(@PathVariable Long id){
        Optional<Merchant> merchant = merchantService.findById(id);
        Merchant activeBan = merchant.get();
        if (activeBan.getIsActive()){
            activeBan.setIsActive(false);
        } else {
            activeBan.setIsActive(true);
        }
        merchantService.save(activeBan);
        sendMailController.sendEmailBan(merchant.get());
        return new ResponseEntity<>(activeBan,HttpStatus.OK);
    }
    // sắp xếp tên merchant theo thứ tự
    @GetMapping("/merchant-name-up")
    private ResponseEntity<List<Merchant>> filterMerchantByNameUp(){
        List<Merchant> merchants = merchantService.filterMerchantByNameUp();
        return new ResponseEntity<>(merchants,HttpStatus.OK);
    }
    @GetMapping("/merchant-name-down")
    private ResponseEntity<List<Merchant>> filterMerchantByNameDown(){
        List<Merchant> merchants = merchantService.filterMerchantByNameDown();
        return new ResponseEntity<>(merchants,HttpStatus.OK);
    }
    //Customer
    // show all customer
    @GetMapping("/customer")
    private ResponseEntity<List<Customer>> showListCustomer(){
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    // chấp nhận tài khoản customer
    @PostMapping("/accept-customer/{id}")
    private ResponseEntity<Customer> acceptCustomer(@PathVariable Long id ){
        Optional<Customer> customer = customerService.findCustomerById(id);
        Customer newCustomer = customer.get();
        newCustomer.setIsAccept(true);
        customerService.save(newCustomer);
        sendMailController.sendEmailC(customer.get().getAppUser());
        return new ResponseEntity<>(newCustomer,HttpStatus.CREATED) ;
    }
    // hiển thị list customer đang đợi accept
    @GetMapping("/accept-customer-list")
    private ResponseEntity<List<Customer>> showWaitingCustomer(){
        List<Customer> customers = customerService.getWaitingAcceptCustomer();
        if (customers.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }
    // show list customer đã được accept
    @GetMapping("/showCustomers")
    private ResponseEntity<List<Customer>> showListCustomerAccept() {
        List<Customer> customers = customerService.findCustomerByIsAccept(true);
        return new ResponseEntity<>(customers, HttpStatus.ACCEPTED);
    }
    // ban và active tài khoản customer
    @PostMapping("/active-ban-customer/{id}")
    private ResponseEntity<Customer> activeBanCustomer(@PathVariable Long id){
        Optional<Customer> customer = customerService.findCustomerById(id);
        Customer activeBan = customer.get();
        if (activeBan.getIsActive()){
            activeBan.setIsActive(false);
        } else {
            activeBan.setIsActive(true);
        }
        customerService.save(activeBan);
        sendMailController.sendEmailBanCustomer(customer.get());
        return new ResponseEntity<>(activeBan,HttpStatus.OK);
    }
    // tìm tài khoản customer theo id
    @GetMapping("/customer/{id}")
    private ResponseEntity<Customer> findCustomerById(@PathVariable Long id){
        Customer customer = customerService.findCustomerById(id).get();
        return new ResponseEntity<>(customer,HttpStatus.ACCEPTED);
    }
    @GetMapping("/find-merchant-by-phone-number/{phone}")
    public ResponseEntity< List<Merchant>> findByNumberPhone(@PathVariable String phone){
        List<Merchant> merchants = merchantService.findMerchantByPhoneNumber(phone);
      return new ResponseEntity<>(merchants,HttpStatus.OK);
    }
}
