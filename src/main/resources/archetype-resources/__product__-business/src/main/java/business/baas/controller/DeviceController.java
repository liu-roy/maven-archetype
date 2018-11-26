#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.business.baas.controller;


import com.google.common.collect.Lists;
import com.qiniu.common.BaseController;
import com.qiniu.common.constant.ErrorCode;
import com.qiniu.gas.baas.api.v1.dto.CameraDTO;
import com.qiniu.gas.baas.api.v1.dto.ResTreeDTO;
import ${package}.rs.remote.DeviceService;
import ${package}.rs.remote.ResTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/device/")
@CrossOrigin
public class DeviceController extends BaseController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ResTreeService resTreeService;

    @GetMapping("list")
    public Object deviceList(HttpServletRequest request){

        String userId = "1";
        String userBody = request.getUserPrincipal().getName();
        if(userBody != null && !"".equals(userBody)){
            userId = userBody.split("&&")[1];
        }else{
            return resultHandler(ErrorCode.UN_AUTH,"fail");
        }
        List<CameraDTO> cameraList = this.deviceService.getDeviceCameraByUserid(userId);
        return resultHandler(ErrorCode.SUCCESS,"success",cameraList);
    }

    /**
     * 资源树
     * @return
     */
    @GetMapping("resTree")
    public Object resTree(HttpServletRequest request){

        String userId;
        String userBody = request.getUserPrincipal().getName();
        if(userBody != null && !"".equals(userBody)){
            userId = userBody.split("&&")[1];
        }else{
            return resultHandler(ErrorCode.UN_AUTH,"fail");
        }

        List<ResTreeDTO> resultTree = Lists.newArrayList();

        //先根据用户id 获取资源权限(TREENODE)列表
        List<String> relatedRes = this.resTreeService.getRelatedResByUserId(userId);

        if (relatedRes != null){
            List<String> idList = Lists.newArrayList();

            relatedRes.stream().forEach(t->{
                String path = this.resTreeService.getResTreePath(t);
                if (path != null && !"".equals(path)){
                    idList.addAll(Arrays.asList(path.split(",")));
                }
            });

            List<String> distIdList = idList.stream().distinct().collect(Collectors.toList());

            //获取资源树(已选中的)
            List<ResTreeDTO> resTree = this.resTreeService.getResTreeListByIdList(distIdList);

            //整理成树形
            resultTree = this.recusionTree("-1",resTree,relatedRes);

        }

        return resultTree;
    }

    private List<ResTreeDTO> recusionTree(String pId, List<ResTreeDTO> allList, List<String> relatedRes){

        List<ResTreeDTO> result = new ArrayList<ResTreeDTO>();
        for (ResTreeDTO tree : allList) {
            if(pId.equals(tree.getPId())){
                ResTreeDTO data = new ResTreeDTO(tree.getId(),tree.getPId(),tree.getLabel(),0,new ArrayList<>());
                data.setChildren(recusionTree(tree.getId(),allList,relatedRes));
                result.add(data);
            }
        }

        //如果当前节点在资源权限内，就找出他所有的设备
        if (relatedRes.contains(pId)){
            //根据treenode获取treenode下的设备
            List<CameraDTO> cameraList = this.deviceService.getDeviceCameraByTreeNode(pId);
            cameraList.stream().forEach(t->{
                ResTreeDTO data = new ResTreeDTO(String.valueOf(t.getId()),pId,t.getName(),1,new ArrayList<>());
                result.add(data);
            });
        }

        return result;
    }

}
