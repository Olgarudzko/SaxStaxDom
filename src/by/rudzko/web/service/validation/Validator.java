package by.rudzko.web.service.validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final String TAG_OPENED = "<[^/?][^>]+>";
    private static final String TAG_CLOSED = "<[/][^>]+>";
    private static final int TAG_NAME = 0;
    private static final String ONE_LEVEL_NOT_ENOUGH = "XML should consist of at least two levels";
    private static final String TAG_NOT_FOUND = "Matching opening tag not found: ";
    private static final String WRONG_MAIN_TAG = "Wrong use of the main tag";
    private static final String FORBIDDEN_SEQUENCE = "Forbidden sequence: ";
    private static final String ILLEGAL_AMOUNT_OF_TAGS = "Not equal amount of opening and closing tags.";
    private static final String CHARSET = "UTF-8";
    private static final String SPACES_BETWEEN_TAGS = ">(\\s)+<";
    private static final String TAGS_WITHOUT_SPACES = "><";
    
    public static void isXmlValid(File source) throws XmlValidationException {

    	try {
	    	String xml = readXmlFromFile(source);
	      	Pattern openTagPattern = Pattern.compile(TAG_OPENED);
	        Pattern closeTagPattern = Pattern.compile(TAG_CLOSED);
	        Matcher openTag = openTagPattern.matcher(xml);
	        Matcher closeTag = closeTagPattern.matcher(xml);
	
	        List<String> tags = new ArrayList<String>();
	        while (openTag.find()) {
	            String found = openTag.group();
	            String tag = found.substring(1, found.length() - 1).trim()
	                    .split("\\s")[TAG_NAME].intern();
	            tags.add(tag);
	        }
	
	        if (tags.size() <= 1) {
	            throw new XmlValidationException(ONE_LEVEL_NOT_ENOUGH);
	        }
	
	        int closed = 0;
	        String lastCloseTag = null;
	        while (closeTag.find()) {
	            String found = closeTag.group();
	            lastCloseTag = found.substring(2, found.length() - 1).trim();
	            if (!tags.contains(lastCloseTag)) {
	                throw new XmlValidationException(TAG_NOT_FOUND + found);
	            }
	            closed++;
	        }
	
	        if (!isRootTagValid(tags, lastCloseTag)) {
	            throw new XmlValidationException(WRONG_MAIN_TAG);
	        }
	
	        if (tags.size() != closed){
	            throw new XmlValidationException(ILLEGAL_AMOUNT_OF_TAGS);
	        }
	
	        String emptyTag=hasEmptyTags(xml);
	        if (emptyTag!=null){
	            throw new XmlValidationException(FORBIDDEN_SEQUENCE+emptyTag);
	        }
    	} catch (IOException e) {
    		throw new XmlValidationException(e);
    	}
    }

    private static boolean isRootTagValid(List<String> tags, String rootTag) {

        if (!tags.get(0).equals(rootTag)) {
            return false;
        }

        int mainTagOccurence = 0;
        for (String tag : tags) {
            if (tag.equals(rootTag)) {
                mainTagOccurence++;
            }
        }

        return mainTagOccurence == 1;
    }


    private static String hasEmptyTags(String source) {

        Pattern openCloseTagPattern = Pattern.compile(TAG_OPENED + TAG_CLOSED);
        Matcher openCloseTag = openCloseTagPattern.matcher(source);
        String found=null;
        if (openCloseTag.find()) {
            found = openCloseTag.group().trim();
            return found;
        }

        return null;
    }
    
    private static String readXmlFromFile(File source) throws IOException{

        FileInputStream catalogueReader = new FileInputStream(source);
        byte[] content = new byte[catalogueReader.available()];
        catalogueReader.read(content);
        String parsed = new String(content, CHARSET);
        return parsed.replaceAll(SPACES_BETWEEN_TAGS, TAGS_WITHOUT_SPACES);
    } 
    
    private Validator(){}
}
