package com.odd.sportal.webservice.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * 用户角色返回body
 * Created by SmileXie on 16/7/15.
 */
@Root(name = "Body")
public class WeatherResponseBody implements Serializable {

    @Element(name = "getWeatherbyCityNameResponse", required = false)
    public WeatherResponseModel getWeatherbyCityNameResponse;

}
