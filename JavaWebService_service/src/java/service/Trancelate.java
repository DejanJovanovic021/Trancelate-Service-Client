
package service;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public interface Trancelate {
 
    @WebMethod
    String trancelate(String word, String sourceLang, String targetLang);
}
