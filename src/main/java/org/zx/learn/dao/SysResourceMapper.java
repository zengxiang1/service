package org.zx.learn.dao;

import org.zx.learn.model.SysResource;

import java.util.List;

public interface SysResourceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysResource record);

    int insertSelective(SysResource record);

    SysResource selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);
    /**
     * 查询所有菜单
     * @return
     */

    List<SysResource> listAllMenu();

    /**
     * 查询所有顶级资源
     * @return
     */
    List<SysResource> listTopResource();

    /**
     * 查询父资源下的所有子资源
     * @param id
     * @return
     */
    List<SysResource> listResourceByParent(Integer id);

}