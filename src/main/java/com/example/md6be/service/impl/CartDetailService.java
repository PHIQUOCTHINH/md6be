package com.example.md6be.service.impl;

import com.example.md6be.model.CartDetail;
import com.example.md6be.repository.ICartDetailRepository;
import com.example.md6be.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    ICartDetailRepository cartDetailRepository;

    @Override
    public CartDetail save(CartDetail cartDetail) {
        List<CartDetail> cartDetailList = findAllByCartId(cartDetail.getCart().getId());
        for (CartDetail detail : cartDetailList) {
            if (detail.getFood().getId()==(cartDetail.getFood().getId())) {
                detail.setQuantity(detail.getQuantity() + cartDetail.getQuantity());
                return cartDetailRepository.save(detail);
            }
        }

        return cartDetailRepository.save(cartDetail);
    }


    @Override
    public List<CartDetail> getAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public List<CartDetail> findAllByUserId(Long id) {
        return cartDetailRepository.findAllByUserId(id);
    }
    @Override
    public Optional<CartDetail> findById(Long id){
        return cartDetailRepository.findById(id);
    }
    @Override
    public void delete(Long id){
        cartDetailRepository.deleteById(id);
    }
    //gio hang
    public CartDetail getItemById(Long id, Long idCustomer){
        List<CartDetail> cartDetails = findAllByUserId(idCustomer);
        for (CartDetail cartDetail1: cartDetails) {
            if (cartDetail1.getFood().getId().equals(id)){
                return cartDetail1;
            }
        }
        return null;
    }
    // thêm vào giỏ hàng

    public void addItem(CartDetail cartDetail,Long idCustomer){
        if (getItemById(cartDetail.getFood().getId(),idCustomer) != null){
            CartDetail cartDetail1 = getItemById(cartDetail.getFood().getId(),idCustomer);

            assert cartDetail1 != null;
            cartDetail1.setQuantity(cartDetail1.getQuantity() + cartDetail1.getQuantity());

        }else {
            save(cartDetail);
        }
    }
    public void removeItem(Long id, Long idCustomer){
        if (getItemById(id,idCustomer) != null){
            delete(id);
        }
    }

    @Override
    public List<CartDetail> findAllByCartId(Long id) {
        return cartDetailRepository.findAllByCartId(id);
    }
}
