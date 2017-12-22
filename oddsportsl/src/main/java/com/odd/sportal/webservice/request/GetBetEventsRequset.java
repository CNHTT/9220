package com.odd.sportal.webservice.request;

/**
 * Created by 戴尔 on 2017/12/18.
 */

public class GetBetEventsRequset {
    private String s;


    @Override
    public String toString() {
        return "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <urn:GetBetEventsLive soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
    }
}
