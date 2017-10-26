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

    List<SysResource> listAllMenu();
}