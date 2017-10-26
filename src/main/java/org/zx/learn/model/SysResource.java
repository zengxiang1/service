package org.zx.learn.model;

public class SysResource {
    private Integer id;

    private String resourceType;

    private String resourceName;

    private String resourcePath;

    private String resourcePermission;

    private Integer resourcePriority;

    private Integer parentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType == null ? null : resourceType.trim();
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
    }

    public String getResourcePath() {
        return resourcePath;
    }

    public void setResourcePath(String resourcePath) {
        this.resourcePath = resourcePath == null ? null : resourcePath.trim();
    }

    public String getResourcePermission() {
        return resourcePermission;
    }

    public void setResourcePermission(String resourcePermission) {
        this.resourcePermission = resourcePermission == null ? null : resourcePermission.trim();
    }

    public Integer getResourcePriority() {
        return resourcePriority;
    }

    public void setResourcePriority(Integer resourcePriority) {
        this.resourcePriority = resourcePriority;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}