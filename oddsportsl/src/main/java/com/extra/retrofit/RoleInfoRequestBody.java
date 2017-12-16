package com.extra.retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/16.
 */

@Root(name = "soapenv:Body", strict = false)
public class RoleInfoRequestBody {
    @Element(name = "return", required = false)
    public String string;
}