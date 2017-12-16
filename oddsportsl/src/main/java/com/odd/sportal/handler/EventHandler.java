package com.odd.sportal.handler;

import com.extra.utils.TimeUtils;
import com.odd.sportal.inter.CallBackSuccess;
import com.odd.sportal.model.EventModel;
import com.odd.sportal.utils.BaseHandler;
import com.odd.sportal.utils.DbHelper;
import com.player.util.L;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.EOFException;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by 戴尔 on 2017/12/11.
 */

public class EventHandler extends BaseHandler {


    private static  final String TAG = "EventHandler";

    private Stack<String> tagStack = new Stack<String>();
    private Vector<EventModel> eventModels = new Vector<EventModel>();
    CallBackSuccess callBackSuccess;

    public EventHandler(CallBackSuccess callBackSuccess) {
        this.callBackSuccess = callBackSuccess;
    }

    @Override
    public boolean parse(String xmlString) {
        try {
            super.parserXml(this, xmlString);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {
        L.d("endDocument");
        callBackSuccess.eventEnd();

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void startDocument() throws SAXException {
        L.d("startDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)  {
        try {
            if(qName.equals("ROW")){
                EventModel object=new EventModel();
                object.setLIVE_DT(TimeUtils.getBetTime(attributes.getValue("LIVE_DT")));
                object.setISPLAYED(Long.parseLong(attributes.getValue("ISPLAYED")));
                object.setFILE_NAME(attributes.getValue("FILE_NAME")==null?"":attributes.getValue("FILE_NAME"));
                object.setTEAM2_NAME(attributes.getValue("TEAM2_NAME")==null?"":attributes.getValue("TEAM2_NAME"));
                object.setTEAM1_NAME(attributes.getValue("TEAM1_NAME")==null?"":attributes.getValue("TEAM1_NAME"));
                object.setTNAME(attributes.getValue("TNAME")==null?"":attributes.getValue("TNAME"));
                object.setET_DESCR_EN(attributes.getValue("ET_DESCR_EN")==null?"":attributes.getValue("ET_DESCR_EN"));
                object.setET_NAME(attributes.getValue("ET_NAME")==null?"":attributes.getValue("ET_NAME"));

                object.setHANDICAP2(Double.parseDouble(attributes.getValue("HANDICAP2")==null?"-1":attributes.getValue("HANDICAP2")));
                object.setHANDICAP1(Double.parseDouble(attributes.getValue("HANDICAP1")==null?"-1":attributes.getValue("HANDICAP1")));
                object.setODDS_2(Double.parseDouble(attributes.getValue("ODDS_2")==null?"-1":attributes.getValue("ODDS_2")));
                object.setODDS_X(Double.parseDouble(attributes.getValue("ODDS_X")==null?"-1":attributes.getValue("ODDS_X")));

                object.setODDS_1(Double.parseDouble(attributes.getValue("ODDS_1")==null?"-1":attributes.getValue("ODDS_1")));
                object.setUSE_CLM_LEN(Long.parseLong(attributes.getValue("USE_CLM_LEN")==null?"-1":attributes.getValue("USE_CLM_LEN")));
                object.setEVT_COMMENT_UNC(attributes.getValue("EVT_COMMENT_UNC")==null?"":attributes.getValue("EVT_COMMENT_UNC"));
                object.setLOTOS_CODE(Long.parseLong(attributes.getValue("LOTOS_CODE")==null?"0":attributes.getValue("LOTOS_CODE")));

                object.setEVENT_TYPE(Long.parseLong(attributes.getValue("EVENT_TYPE")==null?"-1":attributes.getValue("EVENT_TYPE")));
                object.setTOURN_ID(Long.parseLong(attributes.getValue("TOURN_ID")==null?"-1":attributes.getValue("TOURN_ID")));
                object.setDRAW_ID(Long.parseLong(attributes.getValue("DRAW_ID")==null?"-1":attributes.getValue("DRAW_ID")));
                object.setDISPLAY_ORDER(Long.parseLong(attributes.getValue("DISPLAY_ORDER")==null?"-1":attributes.getValue("DISPLAY_ORDER")));

                object.setDISPLAY_DATE(TimeUtils.getBetTimeYMD(attributes.getValue("DISPLAY_DATE")==null?"20170101":attributes.getValue("DISPLAY_DATE")));
                object.setTEAM2_ID(Long.parseLong(attributes.getValue("TEAM2_ID")==null?"-1":attributes.getValue("TEAM2_ID")));
                object.setTEAM1_ID(Long.parseLong(attributes.getValue("TEAM1_ID")==null?"-1":attributes.getValue("TEAM1_ID")));
                object.setSTATUS(Long.parseLong(attributes.getValue("STATUS")==null?"-1":attributes.getValue("STATUS")));

                object.setACTUAL_DATE(TimeUtils.getBetTime(attributes.getValue("ACTUAL_DATE")==null?"":attributes.getValue("ACTUAL_DATE")));
                object.setSCD_DATE(TimeUtils.getBetTime(attributes.getValue("SCD_DATE")==null?"":attributes.getValue("SCD_DATE")));
                object.setBET_START_DATE(TimeUtils.getBetTimeYMD(attributes.getValue("BET_START_DATE")==null?"20170101":attributes.getValue("BET_START_DATE")));
                object.setEVENT_CODE(Long.parseLong(attributes.getValue("EVENT_CODE")==null?"-1":attributes.getValue("EVENT_CODE")));

                object.setEVENT_ID(Long.parseLong(attributes.getValue("EVENT_ID")==null?"-1":attributes.getValue("EVENT_ID")));
                DbHelper.insertNewEvent(object);
            }
        }catch (Exception e){
            L.d(e.toString());
        }

    }
}
