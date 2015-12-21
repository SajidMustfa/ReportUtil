package org.sam.json;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonReader {

	public static void main(String[] args) throws MalformedURLException, JSONException, IOException {
		// TODO Auto-generated method stub
		//JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL("http://10.240.20.141:8080/api/json?pretty=true"))).nextValue();
		JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL("http://vstfs2015test:8080/tfs/TFS2015_Sandpit/Radiology/_apis/build/definitions?api-version=2.0"))).nextValue();
		
		//JSONObject jo = (JSONObject) new JSONTokener(IOUtils.toString(new URL("http://vstfs2015test:8080/tfs/TFS2015_Sandpit/BuildAutomation/_apis/build/definitions?api-version=2.0"))).nextValue();
		
		
		int len=jo.getJSONArray("value").length();
		
	/*	for(int i=0;i<=len-1;i++){
			System.out.println(jo.getJSONArray("jobs").get(i));
		}
		*/
		
		JSONArray jsonArray = jo.getJSONArray("value");
		

		for(int i=0;i<=len-1;i++){
			JSONObject objectInArray = jsonArray.getJSONObject(i);
			String[] elementNames = JSONObject.getNames(objectInArray);
			System.out.printf(" ELEMENTS IN CURRENT OBJECT:\n", elementNames.length);
			for (String elementName : elementNames)
		      {
		        String value = objectInArray.getString(elementName);
		        System.out.printf("key=%s, value=%s\n", elementName, value);
		      }
		      System.out.println();
		    }
			
		}
		/*JSONArray ja=jo.getJSONArray("jobs");
		
		for(JSONArray j:ja){
			
		}*/
		//System.out.println(jo.getJSONArray("jobs").get(1).);
		//System.out.println(jo.getString("nodeDescription"));
		//System.out.println(jo.);
	}


