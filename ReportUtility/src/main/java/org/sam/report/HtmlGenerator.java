package org.sam.report;



	import java.io.File;
	import java.io.IOException;
	import java.util.List;

	import org.apache.commons.io.FileUtils;
	//import org.pack.data.ReportData;

	import com.webfirmframework.wffweb.interpreter.CodeExecutor;
	import com.webfirmframework.wffweb.tag.html.AbstractHtml;
	import com.webfirmframework.wffweb.tag.html.Body;
	import com.webfirmframework.wffweb.tag.html.Div;
	import com.webfirmframework.wffweb.tag.html.H5;
	import com.webfirmframework.wffweb.tag.html.Head;
	import com.webfirmframework.wffweb.tag.html.Html;
	import com.webfirmframework.wffweb.tag.html.Table;
	import com.webfirmframework.wffweb.tag.html.Td;
	import com.webfirmframework.wffweb.tag.html.Tr;
	import com.webfirmframework.wffweb.tag.html.attribute.global.Style;
	import com.webfirmframework.wffweb.tag.htmlwff.Blank;

	public class HtmlGenerator {

		public void generateReport(List<ReportData> reportDatas,String type) {
			Html html = new Html(null) {

				Head head = new Head(this);
				Body body = new Body(this) {
					
				final Style paragraphStyle = new Style("background:cyan");

					Div div = new Div(this, paragraphStyle) {
						Table table = new Table(this, null);
					
	                    CodeExecutor executor = new CodeExecutor(this) {

	                        @Override
	                        public void execute(AbstractHtml base) {
	                        	
	        					Tr headerRow = new Tr(table, null) {
	        						
	        						Td rowData1 = new Td(this, null) {
	        	                    H5 h1 = new H5(this) {
	        	                        Blank headerContent = new Blank(this,"Job Name");
	        	                    };
	        					};
	        	                    
	        					Td rowData2 = new Td(this, null) {
	        	                    H5 h2 = new H5(this) {
	        	                        Blank headerContent = new Blank(this,"Status");
	        	                    };
	        					};
	        						
	        				};
	                        	
	                        	for(ReportData data : reportDatas) {
	            					Tr row = new Tr(table, null) {
	            						
	            						Td rowData1 = new Td(this, null) {
	            							
	            							Blank name = new Blank(this,data.getName());
	            					};
	            					Td rowData2 = new Td(this, null) {
	            						Blank status = new Blank(this,data.getStatus());
	            						};
	            					};
	                        	}
	                        }
	                    };
					};
				};

			};
			// prepends the doc type <!DOCTYPE html>
			html.setPrependDocType(true);
			createHtmlFile(html.toHtmlString(),type);
			System.out.println(html.toHtmlString());
		};
		
		private void createHtmlFile(String htmlString,String type) {
			File file = new File(type+"report.html");
			try {
				FileUtils.writeStringToFile(file, htmlString, false);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}


