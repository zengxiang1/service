package org.zx.learn.service.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.zx.learn.dao.SysResourceMapper;
import org.zx.learn.dto.SysResourceDTO;
import org.zx.learn.exception.ExceptionMsg;
import org.zx.learn.exception.ServiceException;
import org.zx.learn.model.SysResource;
import org.zx.learn.service.ResourceService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zx 17-11-30
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    @Resource
    private SysResourceMapper sysResourceMapper;
    @Override
    public List<SysResourceDTO> listTopResource() throws ServiceException {
        List<SysResource> resourceList = sysResourceMapper.listTopResource();
        if (resourceList == null || resourceList.size() == 0){
            throw new ServiceException(ExceptionMsg.NO_DATA_ERROR_MSG);
        }
        List<SysResourceDTO> result = new ArrayList<>();
        for (SysResource sysResource : resourceList) {
            SysResourceDTO temp = new SysResourceDTO();
            BeanUtils.copyProperties(sysResource,  temp);
            result.add(temp);
        }
        return result;
    }

    @Override
    public List<SysResourceDTO> listResourceByParent(Integer parendId) throws ServiceException {
        if (parendId == null ){
            throw new ServiceException(ExceptionMsg.NOT_FOUND_ARGUMENT_ERROR_MSG);
        }
        List<SysResource> resourceList = sysResourceMapper.listResourceByParent(parendId);
        if (resourceList == null || resourceList.size() == 0){
            throw new ServiceException(ExceptionMsg.NO_DATA_ERROR_MSG);
        }
        List<SysResourceDTO> result = new ArrayList<>();
        for (SysResource sysResource : resourceList) {
            SysResourceDTO temp = new SysResourceDTO();
            BeanUtils.copyProperties(sysResource,  temp);
            result.add(temp);
        }
        return result;
    }

    @Override
    public int updateResource(SysResourceDTO sysResourceDTO) throws ServiceException {
        if (sysResourceDTO == null){
            throw new ServiceException(ExceptionMsg.NOT_FOUND_ARGUMENT_ERROR_MSG);
        }
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(sysResourceDTO, sysResource);
        int result = sysResourceMapper.updateByPrimaryKeySelective(sysResource);
        if (result == 0){
            throw new ServiceException(ExceptionMsg.NO_CHANGE_ERROR_MSG);
        }
        return result;

    }

    @Override
    public int saveResource(SysResourceDTO sysResourceDTO) throws ServiceException {
        if (sysResourceDTO == null){
            throw new ServiceException(ExceptionMsg.NOT_FOUND_ARGUMENT_ERROR_MSG );
        }
        SysResource sysResource = new SysResource();
        BeanUtils.copyProperties(sysResourceDTO, sysResource);
        int result = sysResourceMapper.insertSelective(sysResource);
        if(result == 0){
            throw new ServiceException(ExceptionMsg.NO_CHANGE_ERROR_MSG);
        }
        return result;
    }

    @Override
    public int deleteResource(List<Integer> ids) throws ServiceException {
        int size = 0;
        for (int i = 0; i < ids.size(); i++){
            List<SysResource> sysResourceList = sysResourceMapper.listResourceByParent(ids.get(i));
            if (sysResourceList != null){
                continue;
            }
            sysResourceMapper.deleteByPrimaryKey(ids.get(i));
            size ++;
        }
        if (size != ids.size() ){
           throw new ServiceException(ExceptionMsg.DATA_COMPLETE_EFFECT );
        }
        return ids.size();
    }
}
