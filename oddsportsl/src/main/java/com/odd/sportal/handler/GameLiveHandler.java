package com.odd.sportal.handler;

import com.extra.utils.DataUtils;
import com.extra.utils.TimeUtils;
import com.odd.sportal.inter.CallBackSuccess;
import com.odd.sportal.model.EventModel;
import com.odd.sportal.model.GameLiveModel;
import com.odd.sportal.model.GameModel;
import com.odd.sportal.utils.BaseHandler;
import com.odd.sportal.utils.DbHelper;
import com.player.util.L;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.Serializable;
import java.util.Date;
import java.util.Stack;
import java.util.Vector;

/**
 * Created by 戴尔 on 2017/12/20.
 */

public class GameLiveHandler extends BaseHandler {

    private static  final String TAG = "GameLiveHandler";

    private Stack<String> tagStack = new Stack<String>();
    private Vector<EventModel> eventModels = new Vector<EventModel>();
    CallBackSuccess callBackSuccess;
    public GameLiveHandler(CallBackSuccess callBackSuccess) {
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
        L.d(TAG+"endDocument");
        callBackSuccess.gameEnd();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    }

    @Override
    public void startDocument() throws SAXException {
        L.d(TAG+"startDocument： ");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        try {
            if(qName.equals("ROW")){
                GameLiveModel object = new GameLiveModel();
                object.setID(DataUtils.getNumberLong("ID",attributes));
                object.setEVENT_ID(DataUtils.getNumberLong("EVENT_ID",attributes));
                object.setGAME_ID(DataUtils.getNumberLong("GAME_ID",attributes));
                object.setSTATUS(DataUtils.getNumberLong("STATUS",attributes));

                if (attributes.getValue("START_DATE")!=null)
                    object.setSTART_DATE(TimeUtils.getBetTime(attributes.getValue("START_DATE")));
                if (attributes.getValue("END_DATE")!=null)
                    object.setEND_DATE(TimeUtils.getBetTime(attributes.getValue("END_DATE")));
                object.setEE(DataUtils.getNumberLong("EE",attributes));
                object.setGAME_DESCR(DataUtils.getNumberString("GAME_DESCR",attributes));

                object.setGAME_ORDER(DataUtils.getNumberLong("GAME_ORDER",attributes));
                object.setHANDICAP1(DataUtils.getNumberDouble("HANDICAP1",attributes));
                object.setCHART_NAME(DataUtils.getNumberString("CHART_NAME",attributes));
                object.setSPECIAL(DataUtils.getNumberLong("SPECIAL",attributes));

                object.setISLIVE(DataUtils.getNumberLong("ISLIVE",attributes));
                object.setSC1(DataUtils.getNumberLong("SC1",attributes));
                object.setCHART_DESCR(DataUtils.getNumberString("CHART_DESCR",attributes));
                object.setSC2(DataUtils.getNumberLong("SC2",attributes));

                object.setDISPLAY_FLAG(DataUtils.getNumberLong("DISPLAY_FLAG",attributes));
                object.setGAME_NAME(DataUtils.getNumberString("GAME_NAME",attributes));
                object.setFC_TYPE(DataUtils.getNumberString("FC_TYPE",attributes));
                DbHelper.insertNewGameLiveModel(object);

            }
        }catch (Exception e){
            L.d("GAME  "+e.getMessage());
        }

    }
}
