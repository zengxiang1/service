package org.zx.learn.dao;

import org.zx.learn.model.LocalAuth;

public interface LocalAuthMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(LocalAuth record);

    int insertSelective(LocalAuth record);

    LocalAuth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LocalAuth record);

    int updateByPrimaryKeyWithBLOBs(LocalAuth record);

    int updateByPrimaryKey(LocalAuth record);

    /**
     * 根据账户名获取认证信息
     * @param userName
     * @return
     */
    LocalAuth getAuthByAccountName(String userName);
}