#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.base.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


/**
 * Created by liuleyi on 2018/8/28 下午4:26
 */
@Configuration
@PropertySource("classpath:qiniu-sdk.properties")
@Data
public class QiniuSdkProperties {

    /* 文件上传 */
    @Value("${symbol_dollar}{qiniu.storage.ak}")
    private String storageAK;
    @Value("${symbol_dollar}{qiniu.storage.sk}")
    private String storageSK;
    @Value("${symbol_dollar}{qiniu.storage.host}")
    private String storageHost;
    @Value("${symbol_dollar}{qiniu.storage.bucket}")
    private String storageBucket;

    /* 人脸 */
    @Value("${symbol_dollar}{qiniu.atlab.face.host}")
    private String atlabHost;
    @Value("${symbol_dollar}{qiniu.atlab.ak}")
    private String atlabAK;
    @Value("${symbol_dollar}{qiniu.atlab.sk}")
    private String atlabSK;
    @Value("${symbol_dollar}{qiniu.atlab.group.new}")
    private String groupNew;
    @Value("${symbol_dollar}{qiniu.atlab.group.delete}")
    private String groupDelete;
    @Value("${symbol_dollar}{qiniu.atlab.group}")
    private String groupShow;
    @Value("${symbol_dollar}{qiniu.atlab.face.search}")
    private String faceSearch;
    @Value("${symbol_dollar}{qiniu.atlab.face.add}")
    private String faceAdd;
    @Value("${symbol_dollar}{qiniu.atlab.face.delete}")
    private String faceDelete;
    @Value("${symbol_dollar}{qiniu.atlab.face.detect}")
    private String faceDetect;
    @Value("${symbol_dollar}{qiniu.atlab.face.sim}")
    private String faceSim;
    @Value("${symbol_dollar}{qiniu.atlab.face.show}")
    private String faceShow;


    public String getGroupNewUrl() {
        return this.getAtlabHost() + groupNew;
    }


    public String getGroupDeleteUrl() {
        return this.getAtlabHost() + groupDelete;
    }


    public String getGroupShowUrl() {
        return this.getAtlabHost() +  groupShow;
    }

    public String getFaceSearchUrl() {
        return this.getAtlabHost() + faceSearch;
    }


    public String getFaceAddUrl() {
        return this.getAtlabHost() + faceAdd;
    }


    public String getFaceDeleteUrl() {
        return this.getAtlabHost() + faceDelete;
    }


    public String getFaceDetectUrl() {
        return this.getAtlabHost() + faceDetect;
    }

    public String getFaceSimUrl() {
        return this.getAtlabHost() + faceSim;
    }


    public String getFaceShowUrl() {
        return this.getAtlabHost() + faceShow;
    }

}
