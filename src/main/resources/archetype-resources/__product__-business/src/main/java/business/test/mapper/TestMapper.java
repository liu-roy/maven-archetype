#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.test.mapper;

import com.qiniu.common.mapper.BaseMapper;
import ${package}.business.test.bean.TestDO;

import java.util.List;
import java.util.Map;


public interface TestMapper extends BaseMapper<TestDO> {
    public List<TestDO> getList(Map<String, Object> params);
}
