package com.odd.sportal.webservice.response;



import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * 用户角色返回
 * Created by SmileXie on 16/7/15.
 */

@Root(name = "getWeatherbyCityNameResponse")
@Namespace(reference = "http://WebXml.com.cn/")
public class WeatherResponseModel implements Serializable {

    @ElementList(name = "getWeatherbyCityNameResult")
    public List<String> result;

}
