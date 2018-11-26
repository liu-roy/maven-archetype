#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.test.service;

import com.qiniu.common.service.BaseService;
import ${package}.business.test.bean.TestDO;
import ${package}.business.test.mapper.TestMapper;

import java.util.List;

/**
 * Created by liuleyi on 2018/6/22 下午4:02.
 */
public interface TestService extends BaseService<TestMapper,TestDO> {

    public List getList(int page, int pageSize);

    public TestDO getId(String id);

    public TestDO saveTest(TestDO test);

    public TestDO updateTest(TestDO testDO);

    public boolean deleteTest(String id);

}
