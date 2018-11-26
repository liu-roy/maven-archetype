#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by liuleyi on 2018/8/9 下午5:34
 */
@SpringBootApplication(scanBasePackages = {"com.qiniu.common.boot", "${package}"})
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients
@EnableScheduling
@MapperScan(value = {"${package}.**.mapper"})
public class Application {
    public static void main(String []args){
        SpringApplication.run(Application.class, args);
    }
}
