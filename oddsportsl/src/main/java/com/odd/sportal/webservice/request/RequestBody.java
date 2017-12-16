package com.odd.sportal.webservice.request;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * 用户角色返回body
 * Created by SmileXie on 16/7/15.
 */
@Root(name = "soapenv:Body", strict = false)
public class RequestBody implements Serializable {

    @Element(name = "getWeatherbyCityName", required = false)
    public RequestModel getWeatherbyCityName;
}
