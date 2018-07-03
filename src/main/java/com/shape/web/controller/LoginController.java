package com.shape.web.controller;

import com.shape.query.UserQuery;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.UserService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public JsonResult login(@RequestBody UserQuery userQuery, HttpServletRequest request) {
        return userService.login(userQuery, request);
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.POST)
    public JsonResult loginOut(HttpServletRequest request) {
        return userService.loginOut(request);
    }

    @RequestMapping(value = "/loginInfo", method = RequestMethod.POST)
    public JsonResult loginInfo() {
        JsonResult jsonResult = JsonResult.falseResult();
        CustomUser customUser = WebContext.getUserFromSession();
        if (customUser != null) {
            jsonResult = JsonResult.successResult();
        }
        return jsonResult;
    }
}
