#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.base.bean;

import lombok.Data;

@Data
public class PageCriteriaBean {

    private String orderBy;
    private String orderType;
    private Integer pageNo;
    private Integer pageSize;

}
