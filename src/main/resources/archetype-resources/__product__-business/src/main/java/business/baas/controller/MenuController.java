#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.baas.controller;

import com.google.common.collect.ImmutableMap;
import com.qiniu.common.BaseController;
import com.qiniu.common.constant.ErrorCode;
import com.qiniu.gas.baas.api.v1.dto.MenuDTO;
import ${package}.rs.remote.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu/*")
@CrossOrigin
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @GetMapping("menuList")
    public Object menuList(HttpServletRequest request,@RequestParam("appCode") String appCode){

        String userId = "1";
        String userBody = request.getUserPrincipal().getName();
        if(userBody != null && !"".equals(userBody)){
            userId = userBody.split("&&")[1];
        }else{
            return resultHandler(ErrorCode.UN_AUTH,"fail");
        }

        List<MenuDTO> menuList = menuService.menuList(userId,appCode);

        List<String> menuCodeList = menuService.menuCodeList(userId,appCode);

        Map<String,Object> result = ImmutableMap.<String,Object>builder()
                .put("menuList",menuList)
                .put("menuCodeList",menuCodeList)
                .build();

        return resultHandler(ErrorCode.SUCCESS,"success",result);
    }

    @GetMapping("operatorList")
    public Object operatorList(HttpServletRequest request,@RequestParam("appCode") String appCode,@RequestParam("menuCode") String menuCode){

        String userId;
        String userBody = request.getUserPrincipal().getName();
        if(userBody != null && !"".equals(userBody)){
            userId = userBody.split("&&")[1];
        }else{
            return resultHandler(ErrorCode.UN_AUTH,"fail");
        }

        List<MenuDTO> operatorList = menuService.operatorList(userId,appCode,menuCode);

        List<String> operatorCodeList = menuService.operatorCodeList(userId,appCode,menuCode);

        Map<String,Object> result = ImmutableMap.<String,Object>builder()
                .put("operatorList",operatorList)
                .put("operatorCodeList",operatorCodeList)
                .build();

        return resultHandler(ErrorCode.SUCCESS,"success",result);
    }

}
