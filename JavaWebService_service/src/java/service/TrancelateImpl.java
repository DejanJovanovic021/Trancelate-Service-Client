
package service;

import java.io.IOException;
import javax.jws.WebService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



@WebService(endpointInterface = "service.Trancelate")
public class TrancelateImpl implements Trancelate {
       
        String result;
        String serbian;
        String english;
        
        

    @Override
    public String trancelate(String word, String sourceLang, String targetLang) {
        
        try{
            
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse("D:/Zavrseni zadaci/8. Java web servisi i XML/JavaWebServisiAssignment_2/JavaWebService_service/src/java/service/dictionary.xml");

            XPathFactory xpf = XPathFactory.newInstance();
            XPath xpath = xpf.newXPath();
            XPathExpression exp = xpath.compile("trancelate/word");

            NodeList res = (NodeList)exp.evaluate(doc, XPathConstants.NODESET);
            
            for (int i = 0; i < res.getLength(); i++){
                Element el = (Element) res.item(i);
                 
                serbian = el.getElementsByTagName("serbian").item(0).getTextContent();
                english = el.getElementsByTagName("english").item(0).getTextContent();

            
                if(word != null || sourceLang.equals("serbian") || targetLang.equals("english")){
                    
                    serbian = el.getElementsByTagName("serbian").item(0).getTextContent();
                    
                    if(word.equals(serbian)){
                        return result = el.getElementsByTagName("english").item(0).getTextContent();
                    }
                    else{
                        result = "Required word does not exist in dictionary!!! Please try again!!!";
                    }
                    
                }
                if(word != null || sourceLang.equals("english") || targetLang.equals("serbian")){
                    
                    english = el.getElementsByTagName("english").item(0).getTextContent();

                    if(word.equals(english)){
                        return result = el.getElementsByTagName("serbian").item(0).getTextContent();
                    }
                    else{
                        result = "Required word does not exist in dictionary!!!";
                    }
                }
            }
        }
        catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException ex) {
                
            ex.printStackTrace();
        }
        return result;
       
    }    
}
    

