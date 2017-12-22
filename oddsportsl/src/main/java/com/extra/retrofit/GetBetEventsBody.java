package com.extra.retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/18.
 */

@Root(name = "SOAP-ENV:Body")
public class GetBetEventsBody {

    @Element(name = "NS1:GetBetEventsResponse", required = false)
    public GetBetEventsResponse string;
}
