package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.opencsv.CSVWriter;

import model.Product;

public class WebScraper {
	private String baseUrl = "https://www.allegro.pl/kategoria/";
	private List<String[]> listProducts;
	private String title;
	private String price;
	private String discount;
	private String priceWithDelivery;
	private String  url; 
	
	private WebClient client;
	
	
	public WebScraper() {

	
	}
	public void parsing() {
		client = new WebClient();
		client.getOptions().setCssEnabled(false);
		client.getOptions().setJavaScriptEnabled(false);
		
		listProducts = new ArrayList<String[]>();
		int i = 1;
		try {
			String categoryUrl = baseUrl + "elektronika?string=bargain_zone&bmatch=cl-e2101-d3681-c3682-ele-1-1-0304";
			selectionFromCategory(categoryUrl);
			categoryUrl = baseUrl + "sport-i-turystyka?string=bargain_zone&bmatch=e2101-d3681-c3682-spo-1-1-0304";
			selectionFromCategory(categoryUrl);
			categoryUrl = baseUrl+ "motoryzacja?string=bargain_zone&bmatch=cl-e2101-d3681-c3682-aut-1-1-0304";
			selectionFromCategory(categoryUrl);
			}
			catch(Exception e){
			e.printStackTrace();
		}

	}
	private void selectionFromCategory(String category) throws Exception {
		HtmlPage page = client.getPage(category);
		List<HtmlElement> items = (List<HtmlElement>) page.getByXPath("//article[@class='mx7m_1 mnyp_co mlkp_ag']") ;
		if(items.isEmpty()){
			System.out.println("No items found !");
		}else{
			for(HtmlElement htmlItem : items){
				title = ((HtmlElement) htmlItem.getFirstByXPath(".//h2[@class='mgn2_14 m9qz_yp mqu1_16 mp4t_0 m3h2_0 mryx_0 munh_0']")).asText();
				url = ((HtmlAnchor) htmlItem.getFirstByXPath(".//h2[@class='mgn2_14 m9qz_yp mqu1_16 mp4t_0 m3h2_0 mryx_0 munh_0']/a")).getHrefAttribute();
				price = ((HtmlElement) htmlItem.getFirstByXPath(".//div[@class='msa3_z4 _9c44d_2K6FN']")).asText();
				discount = ((HtmlElement) htmlItem.getFirstByXPath(".//div/div/span[@class='_9c44d_1uHr2']")).asText();
				priceWithDelivery = ((HtmlElement) htmlItem.getFirstByXPath("//div/div[@class='_9c44d_1xKGX']")).asText();
				
				
				listProducts.add(new Product(title, price, discount, url, priceWithDelivery).getArray());

				System.out.println(new Product(title, price, discount, url, priceWithDelivery).toString());
			
				csvWriterAll(listProducts);

			}
		}
		
	}
	private void csvWriterAll(List<String[]> listProducts) throws Exception {
		
		 CSVWriter writer = new CSVWriter(new FileWriter(new File("Results.csv")));
	     writer.writeAll(listProducts,true);
	     writer.writeNext(new String[] {});
	     writer.close();
	     
	}

}
