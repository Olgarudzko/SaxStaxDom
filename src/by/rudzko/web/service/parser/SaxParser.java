package by.rudzko.web.service.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxParser extends DefaultHandler{
	
	private String level="";

    private String text;

    private StringBuilder result=new StringBuilder();

    public StringBuilder getParsedData() {
        return result;
    }

    @Override
    public void startDocument() throws SAXException {
    }

    @Override
    public void endDocument() throws SAXException {
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) throws SAXException {

    	result.append(level).append(qName.toUpperCase());
        for (int i = 0; i <attr.getLength() ; i++) {
            result.append(Constant.SPACE).append(attr.getLocalName(i)).append(Constant.ATTR_VALUE).append(attr.getValue(i));
        }
        result.append(Constant.LINE_BRAKER);
        level+=Constant.LEVEL;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (text!=null && !text.isEmpty()) {
        	result.append(level).append(text).append('\n');
        }
        level=level.substring(Constant.PADDING);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        text = new String(ch,start,length).trim();
    }
}
