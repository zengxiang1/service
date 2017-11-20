package org.zx.learn.service.imp;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.zx.learn.dao.SysRoleMapper;
import org.zx.learn.dto.SysRoleDTO;
import org.zx.learn.exception.ExceptionMsg;
import org.zx.learn.exception.ServiceException;
import org.zx.learn.model.SysRole;
import org.zx.learn.service.RoleService;

/**
 * Created by xiang zeng on 2017/10/28.
 *
 * @author xiang zeng
 */
@Service("roleService")
public class RoleServiceImp implements RoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImp.class);

    @Override
    public List<SysRoleDTO> listAllRole() throws ServiceException{
        List<SysRole> sysRoleList = sysRoleMapper.listAllRole();
        if (sysRoleList == null || sysRoleList.size() == 0) {
            throw new ServiceException(ExceptionMsg.NO_DATA_ERROR_MSG);
        }
        List<SysRoleDTO> resultList = new ArrayList<SysRoleDTO>();
        for (SysRole sysRole : sysRoleList) {
            SysRoleDTO temp = new SysRoleDTO();
            BeanUtils.copyProperties(sysRole, temp);
            resultList.add(temp);
        }
        return resultList;
    }

    @Override
    public SysRoleDTO getRoleById() {
        return null;
    }

    @Override
    public void updateRole(SysRoleDTO sysRoleDTO) {

    }

    @Override
    public int deleteRoleById(List<Integer> list) {
        return sysRoleMapper.deleteRoleById(list);
    }

    @Override
    public List<SysRoleDTO> listTopRole() throws ServiceException {

        List<SysRole> sysRoleList = sysRoleMapper.listAllTopRole();
        if (sysRoleList == null || sysRoleList.size() == 0) {
            throw new ServiceException(ExceptionMsg.NO_DATA_ERROR_MSG);
        }
        List<SysRoleDTO> resultList = new ArrayList<SysRoleDTO>();
        for (SysRole sysRole : sysRoleList) {
            SysRoleDTO temp = new SysRoleDTO();
            BeanUtils.copyProperties(sysRole, temp);
            resultList.add(temp);
        }
        return resultList;

    }

    @Override
    public List<SysRoleDTO> listRoleByParent(Integer id) throws ServiceException {

        List<SysRole> sysRoleList =  sysRoleMapper.listRoleByParent(id);
        if (sysRoleList == null || sysRoleList.size() == 0) {
            throw new ServiceException(ExceptionMsg.NO_DATA_ERROR_MSG);
        }
        List<SysRoleDTO> resultList = new ArrayList<SysRoleDTO>();
        for (SysRole sysRole : sysRoleList) {
            SysRoleDTO temp = new SysRoleDTO();
            BeanUtils.copyProperties(sysRole, temp);
            resultList.add(temp);
        }
        return resultList;
    }

    @Override
    public int saveSysRole(SysRoleDTO sysRoleDTO) throws ServiceException {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDTO, sysRole);
        int row = sysRoleMapper.insertSelective(sysRole);
        if (row == 0){
            throw new ServiceException(ExceptionMsg.NO_CHANGE_ERROR_MSG);
        }
        return row;
    }

    @Override
    public int updateSysRole(SysRoleDTO sysRoleDTO) throws ServiceException {
        log.info("insert role :{}",sysRoleDTO.toString());
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(sysRoleDTO, sysRole);
        int row = sysRoleMapper.updateByPrimaryKeySelective(sysRole);
        if( row == 0 ){
            throw new ServiceException(ExceptionMsg.NO_CHANGE_ERROR_MSG);
        }
        return row;
    }
}
