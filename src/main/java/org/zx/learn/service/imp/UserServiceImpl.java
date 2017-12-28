package org.zx.learn.service.imp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.zx.learn.dao.LocalAuthMapper;
import org.zx.learn.dao.SysResourceMapper;
import org.zx.learn.dao.SysUserMapper;
import org.zx.learn.dto.AuthDTO;
import org.zx.learn.dto.SysResourceDTO;
import org.zx.learn.dto.UserDTO;
import org.zx.learn.exception.ServiceException;
import org.zx.learn.model.LocalAuth;
import org.zx.learn.model.SysResource;
import org.zx.learn.service.UserService;

import javax.annotation.Resource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author xiang zeng
 * @date 2017/10/24
 */
@Service("userService")
public class UserServiceImpl implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
          LocalAuthMapper localAuthMapper;
    @Resource
    private SysResourceMapper sysResourceMapper;
    @Resource
    private  SysUserMapper sysUserMapper;


    @Override
    public AuthDTO getAuthenticateInfo(String s) {
        log.debug("根据用户姓名获取用户认证信息");
        LocalAuth localAuth = localAuthMapper.getAuthByAccountName(s);
        if (localAuth == null) {
            return null;
        }
        AuthDTO authDTO = new AuthDTO();
        BeanUtils.copyProperties(localAuth,authDTO);
        return authDTO;
    }

    @Override
    public List<List<SysResourceDTO>> listAllMenu() {
        List<SysResource> sysResourceList = sysResourceMapper.listAllMenu();
        List<List<SysResourceDTO>> lists = new ArrayList<List<SysResourceDTO>>();
        Map<Integer,List<SysResourceDTO>> listMap = new HashMap<Integer, List<SysResourceDTO>>();
        //遍历菜单
        //一级菜单在第一个元素
        for (SysResource sysResource : sysResourceList) {
            SysResourceDTO sysResourceDTO = new SysResourceDTO();
            BeanUtils.copyProperties(sysResource, sysResourceDTO);

            if (sysResource.getParentId() == null) {
                List<SysResourceDTO> sysResourceDTOS = new ArrayList<SysResourceDTO>();
                sysResourceDTOS.add(sysResourceDTO);
                listMap.put(sysResource.getId(), sysResourceDTOS);
            } else {
                List<SysResourceDTO> sysResourceDTOList = listMap.get(sysResource.getParentId());
                sysResourceDTOList.add(sysResourceDTO);
                listMap.put(sysResource.getParentId(), sysResourceDTOList);
            }
        }
        Collection<List<SysResourceDTO>> listCollections = listMap.values();
        for (List<SysResourceDTO> sysResourceDTOS :listCollections){
            Collections.sort(sysResourceDTOS, new Comparator<SysResourceDTO>() {
                @Override
                public int compare(SysResourceDTO o1, SysResourceDTO o2) {
                    if (o1.getParentId() == null || o1.getParentId() == 0){
                        return -1;
                    } else if ( o2.getResourcePriority() == null || o2.getResourcePriority() == 0){
                        return 1;
                    } else {
                        return (o1.getResourcePriority() < o2.getResourcePriority()) ? -1 : 1;
                    }
                }
            });
            lists.add(sysResourceDTOS);
        }
        Collections.sort(lists, new Comparator<List<SysResourceDTO>>() {
            @Override
            public int compare(List<SysResourceDTO> o1, List<SysResourceDTO> o2) {
                return o1.get(0).getResourcePriority() > o2.get(0).getResourcePriority() ? 1 : -1;
            }
        });
        return lists;
    }

    @Override
    public List<UserDTO> getAllUser(Map<String, String> map) {

        List<UserDTO> resultList = sysUserMapper.getAllUser(map);
        return resultList;
    }

    @Override
    public int deleteUserInfoById(List<Integer> list) throws ServiceException {

        return sysUserMapper.deleteUserInfoById(list);
    }

    @Override
    public int editUserInfoById(UserDTO userDTO) throws ServiceException {

        return sysUserMapper.editUserInfoById(userDTO);
    }

    @Override
    public Map<String, String> addUserInfo(UserDTO userDTO, AuthDTO authDTO) {

        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("sysRole",authDTO.getSysRole());
        paramsMap.put("realName",userDTO.getRealName());
        paramsMap.put("age",userDTO.getAge());
        paramsMap.put("sex",userDTO.getSex());
        paramsMap.put("phoneNumber",userDTO.getPhoneNumber());
        paramsMap.put("address",userDTO.getAddress());
        paramsMap.put("accountName",authDTO.getAccountName());
        paramsMap.put("accountPwd",authDTO.getAccountPwd());

        return sysUserMapper.addUserInfo(paramsMap);
    }
}
