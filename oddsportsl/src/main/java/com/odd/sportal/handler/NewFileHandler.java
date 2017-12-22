package com.odd.sportal.handler;

import com.odd.sportal.utils.BaseHandler;
import com.player.util.L;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

/**
 * Created by 戴尔 on 2017/12/18.
 */

public class NewFileHandler extends BaseHandler {


    private Stack<String> tagStack = new Stack<String>();
    private StringBuffer stringBuffer=new StringBuffer();

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
// TODO Auto-generated method stub
        String chars = new String(ch, start, length).trim();
        if(chars!=null) {
            String tagName = (String)tagStack.peek();//查看栈顶对象而不移除它
            if (tagName.equals("return")) {
                try {
                    if (!chars.equals("")){
                        if (chars.substring(chars.length()-1,chars.length()).equals("\""))
                            out.write(chars+" ");else out.write(chars);
                    }else out.write(chars);
                } catch (IOException e) {
                    L.d("error File");
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        L.d("endDocument");
//

        //刷新IO内存流
        try {
            out.flush();
            //关闭
            out.close();
        } catch (IOException e) {L.d("error File");
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        tagStack.pop();//移除栈顶对象并作为此函数的值返回该对象
    }
    FileWriter out;
    @Override
    public void startDocument() throws SAXException {
        L.d("startDocument： ");
        try {
            File dirFirstFolder = new File(path);
            if (dirFirstFolder.exists()){
                dirFirstFolder.delete();
            }
             out = new FileWriter(new File(path), true);
        } catch (IOException e) {L.d("error File");
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        tagStack.push(qName);
        L.d("qName"+qName);

    }
}
