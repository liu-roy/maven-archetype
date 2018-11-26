#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.test.bean;

import com.qiniu.common.bean.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by liuleyi on 2018/6/20 下午5:00.
 */
@Data
public class TestDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -7797206860861886927L;
    private int id;
    private String name;
}
