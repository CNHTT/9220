package com.odd.sportal.handler;

import com.extra.utils.DataUtils;
import com.extra.utils.TimeUtils;
import com.odd.sportal.inter.CallBackSuccess;
import com.odd.sportal.model.EventModel;
import com.odd.sportal.model.GameModel;
import com.odd.sportal.model.NextLiveEventsModel;
import com.odd.sportal.utils.BaseHandler;
import com.odd.sportal.utils.DbHelper;
import com.player.util.L;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.util.Stack;
import java.util.Vector;

/**
 * Created by 戴尔 on 2017/12/19.
 */

public class NextLiveEventsHandler extends BaseHandler {

    private static  final String TAG = "GameHandler";

    private Stack<String> tagStack = new Stack<String>();
    private Vector<EventModel> eventModels = new Vector<EventModel>();
    CallBackSuccess callBackSuccess;
    public NextLiveEventsHandler(CallBackSuccess callBackSuccess) {
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

        if(qName.equals("ROW")){
            NextLiveEventsModel object = new NextLiveEventsModel();
            object.setEVENT_ID(DataUtils.getNumberLong("EVENT_ID",attributes));
            DbHelper.insertNextLiveEvents(object);

        }
    }
}
