package com.imethod.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Map;

/**
 * time : 15/11/14.
 * auth : jqwang
 * desc :
 * tips :
 * 1.
 */
@Entity
public class Region {
    private Long regionId;
    private String regionName;
    private String regionCode;
    private Integer regionType;
    private String parentCode;
    private Integer state;
    private Integer createrId;
    private Date createAt;
    private Integer updaterId;
    private Date updateAt;

    private Map<String,Region> regionMap;

    @Id
    @Column(name = "region_id", nullable = false, insertable = true, updatable = true)
    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    @Basic
    @Column(name = "region_name", nullable = true, insertable = true, updatable = true, length = 100)
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Basic
    @Column(name = "region_code", nullable = true, insertable = true, updatable = true, length = 20)
    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    @Basic
    @Column(name = "region_type", nullable = true, insertable = true, updatable = true)
    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    @Basic
    @Column(name = "parent_code", nullable = true, insertable = true, updatable = true, length = 20)
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    @Basic
    @Column(name = "state", nullable = true, insertable = true, updatable = true)
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "creater_id", nullable = true, insertable = true, updatable = true)
    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    @Basic
    @Column(name = "create_at", nullable = true, insertable = true, updatable = true)
    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Basic
    @Column(name = "updater_id", nullable = true, insertable = true, updatable = true)
    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    @Basic
    @Column(name = "update_at", nullable = true, insertable = true, updatable = true)
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region region = (Region) o;

        if (regionId != null ? !regionId.equals(region.regionId) : region.regionId != null) return false;
        if (regionName != null ? !regionName.equals(region.regionName) : region.regionName != null) return false;
        if (regionCode != null ? !regionCode.equals(region.regionCode) : region.regionCode != null) return false;
        if (regionType != null ? !regionType.equals(region.regionType) : region.regionType != null) return false;
        if (parentCode != null ? !parentCode.equals(region.parentCode) : region.parentCode != null) return false;
        if (state != null ? !state.equals(region.state) : region.state != null) return false;
        if (createrId != null ? !createrId.equals(region.createrId) : region.createrId != null) return false;
        if (createAt != null ? !createAt.equals(region.createAt) : region.createAt != null) return false;
        if (updaterId != null ? !updaterId.equals(region.updaterId) : region.updaterId != null) return false;
        if (updateAt != null ? !updateAt.equals(region.updateAt) : region.updateAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = regionId != null ? regionId.hashCode() : 0;
        result = 31 * result + (regionName != null ? regionName.hashCode() : 0);
        result = 31 * result + (regionCode != null ? regionCode.hashCode() : 0);
        result = 31 * result + (regionType != null ? regionType.hashCode() : 0);
        result = 31 * result + (parentCode != null ? parentCode.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (createrId != null ? createrId.hashCode() : 0);
        result = 31 * result + (createAt != null ? createAt.hashCode() : 0);
        result = 31 * result + (updaterId != null ? updaterId.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        return result;
    }

    public Map<String, Region> getRegionMap() {
        return regionMap;
    }

    public void setRegionMap(Map<String, Region> regionMap) {
        this.regionMap = regionMap;
    }
}
