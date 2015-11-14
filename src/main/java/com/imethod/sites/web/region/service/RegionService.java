package com.imethod.sites.web.region.service;

import com.imethod.core.log.Logger;
import com.imethod.core.log.LoggerFactory;
import com.imethod.domain.Region;
import com.imethod.sites.web.region.dao.RegionDaO;
import com.imethod.sites.web.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * time : 15/11/14.
 * auth :
 * desc :
 * tips :
 * 1.
 */

@Service
public class RegionService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RegionDaO regionDao;

    private Map<String,Region> getRegionMap(){
        List<Region> regionList = regionDao.listRegion();
        Map<String,Region> regionMap = new LinkedHashMap<>();
        for(Region region : regionList){
            String regionCode = region.getRegionCode();
            Integer regionType = region.getRegionType();
            if(regionType==2){
                regionMap.put(regionCode,region);
            }else{
                region.getRegionMap().put(regionCode,region);
            }

        }
        return regionMap;
    }
}
