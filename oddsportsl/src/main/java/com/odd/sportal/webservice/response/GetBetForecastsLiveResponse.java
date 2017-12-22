package com.odd.sportal.webservice.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/19.
 */

@Root(name = "SOAP-ENV:Envelope")
@NamespaceList({
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema-instance", prefix = "xsi"),
        @Namespace(reference = "http://www.w3.org/2001/XMLSchema", prefix = "xsd"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/encoding/", prefix = "SOAP-ENC"),
        @Namespace(reference = "http://schemas.xmlsoap.org/soap/envelope/", prefix = "SOAP-ENV")
})
public class GetBetForecastsLiveResponse {
    @Element(name = "Body")
    public GetBetForecastsLiveBody body;
}
