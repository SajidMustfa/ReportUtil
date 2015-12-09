package org.sam.report;


import org.dom4j.io.SAXReader;
//import org.pack.businesslib.HtmlGenerator;
//import org.pack.data.ReportData;
import org.dom4j.Document;
import org.dom4j.Element;

import java.net.URL;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;

/**
 * Java client program to demonstrate how to access Hudson remote API.
 *
 * @author Kohsuke Kawaguchi
 * @see http://hudson.gotdns.com/wiki/display/HUDSON/Remote+access+API
 */
public class Main {
    public static void main(String[] args) throws Exception {
        // every Hudson model object exposes the .../api/xml, but in this example
        // we'll just take the root object as an example
       // URL url = new URL("http://deadlock.netbeans.org/hudson/api/xml");
    	URL url = new URL("http://10.240.20.141:8080/view/PowerPath/api/xml");
    	 
    	// URL url = new URL("http://vstfs2015test:8080/tfs/TFS2015_Sandpit/BuildAutomation/_apis/build/definitions?api-version=2.0");
    	 //http://vstfs2015test:8080/tfs/TFS2015_Sandpit/BuildAutomation/_apis/build/definitions?api-version=2.0
    	 
    	 
        // if you are calling security-enabled Hudson and
        // need to invoke operations and APIs that are protected,
        // consult the 'SecuredMain" class
        // in this package for an example using HttpClient.

        // read it into DOM.
        Document dom = new SAXReader().read(url);
        
        List<ReportData> reportDatas = new ArrayList<>();

        // scan through the job list and print its status
        for( Element job : (List<Element>)dom.getRootElement().elements("job")) {
        	
        	//ArrayList<Element> a=new ArrayList<Element>();
        	//a.add(job);
        	//System.out.println(job);
        	
        	ReportData data = new ReportData(job.elementText("name"), 
        			job.elementText("color").equals("blue") ? "Pass" : "Fail");
        	
        	
/*            System.out.println(String.format("Name:%s\t\tStatus:%s",
                job.elementText("name"), job.elementText("color")));
            
            String color= "blue";
			if(job.elementText("color").equals(color)){
				System.out.println("Pass");
			}
			else {
				System.out.println("Fail");
			}*/
        	reportDatas.add(data);
        }
        
        HtmlGenerator htmlGenerator =  new HtmlGenerator();
        htmlGenerator.generateReport(reportDatas);
     
        
    }
}
