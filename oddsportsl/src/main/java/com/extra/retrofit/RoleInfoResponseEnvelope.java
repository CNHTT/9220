package com.extra.retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/16.
 */

@Root(name = "SOAP-ENV:Envelope")
@NamespaceList({
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"),
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/encoding/", prefix = "SOAP-ENC"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "SOAP-ENV")
})


public class RoleInfoResponseEnvelope {
    @Element(name = "SOAP-ENV:Body")
    @NamespaceList({
        @Namespace(reference = "urn:APIInterf-IBetAPI", prefix = "NS1"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/encoding/", prefix = "SOAP-ENV:encodingStyle"),
    })
    public GetBetEventsBody body;
}
