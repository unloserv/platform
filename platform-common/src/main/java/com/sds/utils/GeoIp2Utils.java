package com.sds.utils;

import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;
import java.io.InputStream;
import java.net.InetAddress;

/**
 * @author cs
 * @date 2020/8/18
 * @description
 */
public class GeoIp2Utils {

  public static CityResponse getCityByIp(String ip) throws Exception {
    InputStream cityDatabase = GeoIp2Utils.class.getResourceAsStream("/GeoLite2-City.mmdb");
    DatabaseReader cityInfo     = new DatabaseReader.Builder(cityDatabase).withCache(new CHMCache()).build();
    return cityInfo.city(InetAddress.getByName(ip));
  }

}
