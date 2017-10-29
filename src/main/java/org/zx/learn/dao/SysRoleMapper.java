package org.zx.learn.dao;

import java.util.List;
import org.zx.learn.dto.SysRoleDTO;
import org.zx.learn.model.SysRole;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    /**
     * 获取所有角色
     * @return
     */
    List<SysRole> listAllRole();

    /**
     * 根据id删除角色
     * @param ids
     * @return
     */
    int deleteRoleById(List<Integer> ids);
}