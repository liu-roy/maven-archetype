#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.test.service.impl;

import com.qiniu.common.service.impl.BaseServiceImpl;
import ${package}.business.test.bean.TestDO;
import ${package}.business.test.mapper.TestMapper;
import ${package}.business.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuleyi on 2018/6/22 下午4:03.
 */
@Service
public class TestServiceImpl extends BaseServiceImpl<TestMapper,TestDO> implements TestService {

    @Autowired
    TestMapper testMapper;

    @Override
    public TestMapper getDAO() {
        return testMapper;
    }

    /**
     *
     * @param page
     * @param pageSize
     * @return
     */
    @Cacheable(key = "'testList-'.concat(T(String).valueOf(${symbol_pound}page)).concat('-').concat(T(String).valueOf(${symbol_pound}pageSize))",value = {"testCache"})
    @Override
    public List getList(int page, int pageSize) {
        Map<String,Object> params = new HashMap<>();
        params.put("page",page);
        params.put("pageSize",pageSize);
        return testMapper.getList(params);
    }

    @Cacheable(key = "'test_'.concat(${symbol_pound}p0)", value = {"testCache"})
    @Override
    public TestDO getId(String id){
        TestDO a = testMapper.getById(id);
        return a;
    }

    @CachePut(key = "'test_'.concat(${symbol_pound}testDO.id)",value = {"testCache"})
    @Override
    public TestDO saveTest(TestDO testDO){
        this.testMapper.save(testDO);
        return testDO;
    }

    @CachePut(key = "'test_'.concat(${symbol_pound}testDO.id)",value = {"testCache"})
    @Override
    public TestDO updateTest(TestDO testDO){
        this.testMapper.update(testDO);
        return testDO;
    }

    @CacheEvict(key = "'test_'.concat(${symbol_pound}p0)",value = {"testCache"})
    @Override
    public boolean deleteTest(String id){
        return this.testMapper.delete(id) > 0 ? true : false;
    }

}
