package org.zx.learn.dao;

import org.zx.learn.dto.UserDTO;
import org.zx.learn.model.SysUser;

import java.util.List;
import java.util.Map;

public interface SysUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<UserDTO> getAllUser(Map<String , String> paramsMap);

    int deleteUserInfoById(List<Integer> ids);
}