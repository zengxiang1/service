package org.zx.learn.service.imp;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zx.learn.dao.LocalAuthMapper;
import org.zx.learn.dao.SysResourceMapper;
import org.zx.learn.dao.SysUserMapper;
import org.zx.learn.dto.AuthDTO;
import org.zx.learn.dto.SysResourceDTO;
import org.zx.learn.dto.UserDTO;
import org.zx.learn.model.LocalAuth;
import org.zx.learn.model.SysResource;
import org.zx.learn.model.SysUser;
import org.zx.learn.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xiang zeng
 * @date 2017/10/24
 */
@Service("userService")
public class UserServiceImp implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserServiceImp.class);
    @Resource
    private LocalAuthMapper localAuthMapper;
    @Resource
    private SysResourceMapper sysResourceMapper;


    @Override
    public AuthDTO getAuthenticateInfo(String s) {
        log.debug("根据用户姓名获取用户认证信息");
        LocalAuth localAuth = localAuthMapper.getAuthByAccountName(s);
        AuthDTO authDTO = new AuthDTO();
        BeanUtils.copyProperties(localAuth,authDTO);
        return authDTO;
    }

    @Override
    public List<SysResourceDTO> listAllMenu() {
        List<SysResource> sysResourceList = sysResourceMapper.listAllMenu();
        if (sysResourceList != null && sysResourceList.size() > 0) {
            List<SysResourceDTO> resultList = new ArrayList<SysResourceDTO>();
            for (SysResource sysResource : sysResourceList){
                SysResourceDTO temp = new SysResourceDTO();
                BeanUtils.copyProperties(sysResource, temp);
                resultList.add(temp);
            }
            return resultList;
        } else {
            return null;
        }


    }
}
