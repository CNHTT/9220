package com.extra.retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/16.
 */
@Root(name = "soapenv:Envelope")
@NamespaceList({
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"),
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/encoding/", prefix = "enc"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "soapenv")
})
public class RoleInfoRequestEnvelope {
    @Element(name = "soapenv:Body", required = false)
    public RoleInfoRequestBody body;

    @Element(name = "soapenv:Header", required = false)
    public String aHeader="";

}