package com.odd.sportal.webservice.response;

import org.greenrobot.greendao.annotation.Entity;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/19.
 */
@Root (name = "Body")
public class GetBetEventsBody {

    @Element(name ="GetBetEventsResponse" )
    public GetBetEventsReturn reture;
    @Attribute(required = true)
    private String encodingStyle;
}
