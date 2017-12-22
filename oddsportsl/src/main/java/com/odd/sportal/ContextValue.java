package com.odd.sportal;

/**
 * Created by 戴尔 on 2017/12/12.
 */

public class ContextValue {
    public static final String ISLOADDATA = "ISLOADDATA";
    public static final String ISLOADING ="isloading" ;



    //1.获取Event

    public static final String soapEnvGetBetEvents =
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <urn:GetBetEvents soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";

    public static final String soapEnvGetBetEventsFileName=App.filePath+"ResultGetBetEvents.xml";
    public static final String GetBetEventsFileName=App.filePath+"GetBetEvents.xml";



    //2获取BetGames

    public  static final String soapGetBetGames=
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <urn:GetBetGames soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    public static final String soapGetBetGamesFileName =App.filePath+"GetBetGamesResponse.xml";
    public static final String GetBetGamesFileName =App.filePath+"BetGames.xml";


    //3GetBetForecasts


    public static final String soapGetBetForecasts =
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <urn:GetBetForecasts soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    public static final String soapGetBetForecastsFileName =App.filePath+"GetBetForecastsResponse.xml";
    public static final String GetBetForecastsFileName =App.filePath+"GetBetForecasts.xml";


    //4GetBetForecastsOutRight

    public static final String soapGetBetForecastsOutRight =
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <urn:GetBetForecastsOutRight soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    public static  final String soapGetBetForecastsOutRightFileName=App.filePath+"GetBetForecastsOutRightResponse.xml";
    public static  final String GetBetForecastsOutRightFileName=App.filePath+"GetBetForecastsOutRight.xml";



    //5GetNextLiveEvents

    public static final String soapGetNextLiveEvents =
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <urn:GetNextLiveEvents soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    public static final String soapGetNextLiveEventsFileName  = App.filePath+"GetNextLiveEventsResponse.xml";
    public static final String GetNextLiveEventsFileName=App.filePath +"GetNextLiveEvents.xml";


    //6GetBetEventsLive

    public static  final String soapGetBetEventsLive =
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <urn:GetBetEventsLive soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";


    public static final String soapGetBetEventsLiveFileName  = App.filePath+"GetBetEventsLiveResponse.xml";
    public static final String GetBetEventsLiveFileName=App.filePath +"GetBetEventsLive.xml";




    //7GetBetGamesLive

    public static final String soapGetBetGamesLive =
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <urn:GetBetGamesLive soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    public static final String soapGetBetGamesLiveFileName = App.filePath+"GetBetGamesLiveResponse.xml";
    public static final String GetBetGamesLiveFileName = App.filePath+"GetBetGamesLive.xml";


    //8GetBetForecastsLive

    public static  final String soapGetBetForecastsLive =
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <urn:GetBetForecastsLive soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";

    public static final  String  soapGetBetForecastsLiveFileName = App.filePath+"GetBetForecastsLiveResponse.xml";
    public static final  String  GetBetForecastsLiveFileName = App.filePath+"GetBetForecastsLive.xml";



    //9GetOddsOfTheDay

    public  static final  String soapGetOddsOfTheDay =
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <urn:GetOddsOfTheDay soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";


    public static final String soapGetOddsOfTheDayFileName = App.filePath+"GetOddsOfTheDayResponse.xml";
    public static final String GetOddsOfTheDayFileName = App.filePath+"GetOddsOfTheDay.xml";


    //10 GetCashBack
    public static final String soapGetCashBack=
            "<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:urn=\"urn:APIInterf-IBetAPI\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <urn:GetCashBack soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"/>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
    public static final String soapGetCashBackFileName=App.filePath+"GetCashBackResponse.xml";
    public static final String GetCashBackFileName =App.filePath+"GetCashBack.xml";




}
