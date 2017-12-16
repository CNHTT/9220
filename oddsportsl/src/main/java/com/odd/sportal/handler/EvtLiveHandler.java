package com.odd.sportal.handler;

import com.extra.utils.DataUtils;
import com.extra.utils.TimeUtils;
import com.odd.sportal.model.EvtLiveModel;
import com.odd.sportal.utils.BaseHandler;
import com.player.util.L;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * Created by 戴尔 on 2017/12/14.
 */

public class EvtLiveHandler extends BaseHandler {
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
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

    }

    @Override
    public void startDocument() throws SAXException {
        L.d("startDocument");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        try {
            if (qName.equals("ROW")){
                EvtLiveModel object = new EvtLiveModel();
                object.setEVENT_ID(DataUtils.getNumberLong("EVENT_ID",attributes));
                object.setEVENT_CODE(DataUtils.getNumberLong("EVENT_CODE",attributes));
                object.setBET_START_DATE(TimeUtils.getBetTime(attributes.getValue("BET_START_DATE")));
                object.setSCD_DATE(TimeUtils.getBetTime(attributes.getValue("SCD_DATE")));

                object.setACTUAL_DATE(TimeUtils.getBetTime(attributes.getValue("ACTUAL_DATE")));
                object.setSTATUS(DataUtils.getNumberLong("STATUS",attributes));
                object.setTEAM1_ID(DataUtils.getNumberLong("TEAM1_ID",attributes));
                object.setTEAM2_ID(DataUtils.getNumberLong("TEAM2_ID",attributes));

                object.setDISPLAY_DATE(TimeUtils.getBetTime(attributes.getValue("DISPLAY_DATE")));
                object.setDRAW_ID(DataUtils.getNumberLong("DRAW_ID",attributes));
                object.setTOURN_ID(DataUtils.getNumberLong("TOURN_ID",attributes));
                object.setEVENT_TYPE(DataUtils.getNumberLong("EVENT_TYPE",attributes));

                object.setLOTOS_CODE(DataUtils.getNumberLong("LOTOS_CODE",attributes));
                object.setEVT_COMMENT_UNC(DataUtils.getNumberString("EVT_COMMENT_UNC",attributes));
                object.setUSE_CLM_LEN(DataUtils.getNumberLong("USE_CLM_LEN",attributes));
                object.setODDS_1(DataUtils.getNumberDouble("ODDS_1",attributes));

                object.setODDS_2(DataUtils.getNumberDouble("ODDS_2",attributes));
                object.setODDS_X(DataUtils.getNumberDouble("ODDS_X",attributes));
                object.setHANDICAP1(DataUtils.getNumberDouble("HANDICAP1",attributes));
                object.setHANDICAP2(DataUtils.getNumberDouble("HANDICAP2",attributes));

                object.setET_NAME(DataUtils.getNumberString("ET_NAME",attributes));
                object.setEt_code(DataUtils.getNumberString("et_code",attributes));
                object.setET_DESCR_EN(DataUtils.getNumberString("ET_DESCR_EN",attributes));
                object.setTNAME(DataUtils.getNumberString("TNAME",attributes));

                object.setTORDER(DataUtils.getNumberLong("TORDER",attributes));
                object.setTWEB_ID(DataUtils.getNumberLong("TWEB_ID",attributes));
                object.setTEAM1_NAME(DataUtils.getNumberString("TEAM1_NAME",attributes));
                object.setTEAM2_NAME(DataUtils.getNumberString("TEAM2_NAME",attributes));


                object.setFILE_NAME(DataUtils.getNumberString("FILE_NAME",attributes));
                object.setIsPlayed(DataUtils.getNumberLong("isPlayed",attributes));
                object.setLIVE_DT(TimeUtils.getBetTime(attributes.getValue("LIVE_DT")));
                object.setDISCIPLINE_ID(DataUtils.getNumberLong("DISCIPLINE_ID",attributes));

                object.setSPORTS_IMAGE(DataUtils.getNumberString("SPORTS_IMAGE",attributes));
                object.setSPORT_ORDER(DataUtils.getNumberLong("SPORT_ORDER",attributes));
                object.setANTEPOST(DataUtils.getNumberLong("ANTEPOST",attributes));
                object.setTOURN_ORDER(DataUtils.getNumberLong("TOURN_ORDER",attributes));









            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
