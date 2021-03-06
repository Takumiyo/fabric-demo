package com.example.springbootfabricdemo.web;

import com.example.springbootfabricdemo.entity.User;
import com.example.springbootfabricdemo.dto.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @GetMapping("/info/{userId}")
    public Response<User> getAccountInfo(@PathVariable String userId) {

        return Response.newSuccInstance(new User());
    }

}
