package com.mycompany.hr.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import com.mycompany.hr.service.HumanResourceService;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.xpath.XPath;

@Endpoint  
// The HolidayEndpoint is annotated with @Endpoint. This marks the class as a special sort of @Component, suitable for handling XML messages in Spring-WS, 
// and also making it eligible for suitable for component scanning.                                                                              
public class HolidayEndpoint {

  private static final String NAMESPACE_URI = "http://mycompany.com/hr/schemas";

  private XPath startDateExpression;

  private XPath endDateExpression;

  private XPath nameExpression;

  @Autowired
  public HolidayEndpoint(HumanResourceService humanResourceService)                      
      throws JDOMException {

    Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);

    startDateExpression = XPath.newInstance("//hr:StartDate");
    startDateExpression.addNamespace(namespace);

    endDateExpression = XPath.newInstance("//hr:EndDate");
    endDateExpression.addNamespace(namespace);

    nameExpression = XPath.newInstance("concat(//hr:FirstName,' ',//hr:LastName)");
    nameExpression.addNamespace(namespace);
  }

  // The @PayloadRoot annotation tells Spring-WS that the handleHolidayRequest method is suitable for 
  // handling XML messages. The sort of message that this method can handle is indicated by the annotation values, 
  // in this case, it can handle XML elements that have the HolidayRequest local part and the http://mycompany.com/hr/schemas namespace. 
  
  // basically means that whenever an XML message is received with the namespace http://mycompany.com/hr/schemas and the HolidayRequest local name, 
  // it will be routed to the handleHolidayRequest method. 

  // ?????? Meaning of "localPart"
  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "HolidayRequest")
                 
  public @ResponsePayload Element handleHolidayRequest(@RequestPayload Element holidayRequest)               
       {
    return holidayRequest;
  }

  // Using JDOM is just one of the options to handle the XML: other options include DOM, dom4j, XOM, SAX, and StAX, but also marshalling techniques like JAXB, 
  // Castor, XMLBeans, JiBX, and XStream, as is explained in the next chapter. We chose JDOM because it gives us access to the raw XML, 
  // and because it is based on classes (not interfaces and factory methods as with W3C DOM and dom4j), which makes the code less verbose. 
  // We use XPath because it is less fragile than marshalling technologies: we don't care for strict schema conformance, as long as we can find the dates and the name.and because it is 
  // based on classes (not interfaces and factory methods as with W3C DOM and dom4j), which makes the code less verbose. We use XPath because it is less fragile than marshalling technologies:
  //  we don't care for strict schema conformance, as long as we can find the dates and the name.

}