package com.kbstar.service;


import com.kbstar.dto.Cart;
import com.kbstar.frame.KBService;
import com.kbstar.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//interface에 적어 넣으면, impl 할때 자동으로 불러옴
@Service
public class CartService implements KBService<String, Cart> {

    @Autowired
    CartMapper mapper;

    /**
     * 등록 및 가입 진행
     * argument : Object
     * return : null
     *
     * @param cart
     */
    @Override
    public void register(Cart cart) throws Exception {
        mapper.insert(cart);
    }

    @Override
    public void remove(String s) throws Exception {
        mapper.delete(s);
    }

    @Override
    public Cart get(String s) throws Exception {
        return mapper.select(s);
    }

    @Override
    public List<Cart> get() throws Exception {
        return mapper.selectall();
    }

    @Override
    public void modify(Cart cart) throws Exception {
        mapper.update(cart);
    }


    public List<Cart> getMyCart(String cid) throws Exception {
        return mapper.getmycart(cid);
    }

}
