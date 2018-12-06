package com.red.star.wechat.data.utils;

import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;

import java.io.FileNotFoundException;

/**
 * ip换算城市工具类
 *
 * @author xuquanyu
 */
public class IpUtil {
    private static DbSearcher dbSearcher;

    public static void init(String path) {
        try {
            dbSearcher = new DbSearcher(new DbConfig(), path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DbMakerConfigException e) {
            e.printStackTrace();
        }
    }

    public static String getCityInfoByIp(String ip) throws Exception {
        if(CheckUtil.isEmpty(dbSearcher)){
            return  "未知";
        }
        DataBlock dataBlock = dbSearcher.btreeSearch(ip);
        String region = dataBlock.getRegion().substring(0, dataBlock.getRegion().lastIndexOf("|"));
        String city = region.substring(region.lastIndexOf("|") + 1);
        return city;
    }

}
