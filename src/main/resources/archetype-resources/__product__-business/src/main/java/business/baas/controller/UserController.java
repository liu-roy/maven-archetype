#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.baas.controller;

import com.qiniu.common.BaseController;
import com.qiniu.common.constant.ErrorCode;
import com.qiniu.gas.baas.api.v1.dto.BaseResultDTO;
import com.qiniu.gas.baas.api.v1.dto.UserInfoDTO;
import ${package}.rs.remote.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @PostMapping(value = "/password")
    @ResponseBody
    public Object changePwd (@RequestParam(value = "userName") String userName
            , @RequestParam(value = "oldPassword") String oldPwd
            , @RequestParam(value = "newPassword") String newPwd) {
        BaseResultDTO data = userService.changePassword(userName, oldPwd, newPwd);
        return this.resultHandler(data.getCode(), data.getMsg());
    }

    @GetMapping(value = "/{userName}")
    @ResponseBody
    public Object userInfo(@PathVariable("userName") String name){
        UserInfoDTO user = userService.getUserInfo(name);
        return this.resultHandler(ErrorCode.SUCCESS, "", user);

    }

}
