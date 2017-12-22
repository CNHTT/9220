package com.extra.retrofit;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/18.
 */

@Root(name = "NS1:GetBetEventsResponse")
public class GetBetEventsResponse {

    @Element (name = "return")
    @Namespace(reference = "xsd:string",prefix = "xsi:type")
    private String result;
}
