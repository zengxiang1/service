package org.zx.learn.service.imp;

import org.springframework.stereotype.Service;
import org.zx.learn.dao.SysAuthMapper;
import org.zx.learn.exception.ServiceException;
import org.zx.learn.service.AuthService;

import javax.annotation.Resource;

@Service("authService")
public class AuthServiceImpl implements AuthService{

    @Resource
    private SysAuthMapper sysAuthMapper;

    @Override
    public int checkAuth(String s) throws ServiceException {
        return sysAuthMapper.checkAuth(s);
    }
}
