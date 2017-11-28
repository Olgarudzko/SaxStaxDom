package by.rudzko.web.service.parser;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
public class DomParser {
	private static StringBuilder result = new StringBuilder();

    public static StringBuilder getParsedData() {
        return result;
    }
    
    public static void parseDom(Node node) {
    	innerParsing(Constant.EMPTY_STRING, node);
    }
    
    private static void innerParsing(String prefix, Node node) {
    	String name = node.getNodeName();
    	if (name!=null && !name.trim().isEmpty() && !name.equals(Constant.WHITESPACES)) {
    		result.append(prefix).append(name.toUpperCase()).append(Constant.LINE_BRAKER);
    		prefix = prefix.concat(Constant.LEVEL);
    	}
        String text = node.getNodeValue();
	        if (text!=null && !text.trim().isEmpty()){
	            result.append(prefix).append(text.trim()).append(Constant.LINE_BRAKER);
	        }
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
        	innerParsing(prefix, children.item(i));
        }
    }
}
