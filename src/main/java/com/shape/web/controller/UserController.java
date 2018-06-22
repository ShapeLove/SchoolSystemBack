package com.shape.web.controller;

import com.shape.entity.User;
import com.shape.web.dto.CustomUser;
import com.shape.web.dto.JsonResult;
import com.shape.web.service.UserService;
import com.shape.web.util.WebContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/resetpwd", method = RequestMethod.POST)
    public JsonResult resetPwd(@RequestBody User user) {
        CustomUser customUser = WebContext.getUserFromSession();
        user.setId(customUser.getId());
        return userService.resetPassword(user);
    }

    @RequestMapping(value = "/userinfo", method = RequestMethod.POST)
    public JsonResult<CustomUser> userInfo() {
        JsonResult jsonResult = JsonResult.falseResult();
        try {
            CustomUser customUser = WebContext.getUserFromSession();
            jsonResult = JsonResult.successResult();
            jsonResult.setData(customUser);
        }catch (Exception e) {
            jsonResult.setMessage("系统异常");
        }
        return jsonResult;
    }
}
