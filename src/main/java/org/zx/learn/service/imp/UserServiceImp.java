package org.zx.learn.service.imp;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.zx.learn.dao.LocalAuthMapper;
import org.zx.learn.dao.SysUserMapper;
import org.zx.learn.dto.AuthDTO;
import org.zx.learn.dto.UserDTO;
import org.zx.learn.model.LocalAuth;
import org.zx.learn.model.SysUser;
import org.zx.learn.service.UserService;

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


    @Override
    public AuthDTO getAuthenticateInfo(String s) {
        log.debug("根据用户姓名获取用户认证信息");
        LocalAuth localAuth = localAuthMapper.getAuthByAccountName(s);
        AuthDTO authDTO = new AuthDTO();
        BeanUtils.copyProperties(localAuth,authDTO);
        return authDTO;
    }
}
