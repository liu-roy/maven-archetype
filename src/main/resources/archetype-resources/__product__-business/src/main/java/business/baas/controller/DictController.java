#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.baas.controller;

import ${package}.business.utils.RedisCacheUtils;
import com.qiniu.common.BaseController;
import com.qiniu.common.constant.ErrorCode;
import com.qiniu.gas.baas.api.v1.dto.DictDefineDTO;
import com.qiniu.renter.business.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dict/")
@CrossOrigin
public class DictController extends BaseController {

    @Autowired
    private RedisCacheUtils redisCacheUtils;

    @GetMapping("dictDefineList")
    public Object distDefineList(String typeCode){

        List<DictDefineDTO> defineList = redisCacheUtils.getDictDefineList(typeCode);
        return resultHandler(ErrorCode.SUCCESS,"success",defineList);
    }

}
