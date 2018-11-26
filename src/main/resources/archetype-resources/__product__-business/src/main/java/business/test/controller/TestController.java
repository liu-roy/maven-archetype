#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.test.controller;

import com.qiniu.common.BaseController;
import ${package}.business.test.bean.TestDO;
import ${package}.business.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test/*")
public class TestController extends BaseController {

    @Autowired
    TestService testService;

    @Autowired
    RedisTemplate redisTemplate;
    /**
     *
     * @return
     */
    @GetMapping("list")
    @ResponseBody
    public Object test(){

        List<TestDO> list = this.testService.getList(1,1);
        return "success";
    }

    @GetMapping("getById")
    @ResponseBody
    public Object getById(String id){
        TestDO list = this.testService.getId(id);
        return "success";
    }

    @GetMapping("delete")
    @ResponseBody
    public Object delete(String id){
        this.testService.deleteTest(id);
        return "success";
    }

    @GetMapping("update")
    @ResponseBody
    public Object update(String id){
        TestDO test = this.testService.getId(id);
        test.setName("updated");
        this.testService.updateTest(test);
        return "success";
    }

    @GetMapping("save")
    @ResponseBody
    public Object testSave(){
        TestDO test = new TestDO();
        test.setId((int)(Math.random()*100));
        test.setName("nnn");
        this.testService.saveTest(test);
        return "success";
    }
}
