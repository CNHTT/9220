package com.odd.sportal.webservice.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by 戴尔 on 2017/12/19.
 */

@Root(name = "GetBetForecastsOutRightResponse")
public class GetBetForecastsOutRightReturn {
    @Element(name = "return")
    public String string;
}
