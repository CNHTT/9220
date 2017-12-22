package com.odd.sportal.webservice.response;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/19.
 */

@Root(name = "Body")
public class GetNextLiveEventsBody {
    @Element(name ="GetNextLiveEventsResponse" )
    public GetNextLiveEventsReturn reture;
    @Attribute(required = true)
    private String encodingStyle;
}
