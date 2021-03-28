package model;

import java.math.BigDecimal;

public class Product {
	
	private String title; 
	private String price;
	private String discount;
	private String url;	
	private String priceWithDelivery;
	
	public Product(String title, String price, String discount, String url, String priceWithDelivery) {
		super();
		this.title = title;
		this.price = price;
		this.discount = discount;
		this.url = url;
		this.priceWithDelivery = priceWithDelivery;
	}
	


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getPriceWithDelivery() {
		return priceWithDelivery;
	}
	public void setPriceWithDelivery(String priceWithDelivery) {
		this.priceWithDelivery = priceWithDelivery;
	}
	
	public String[] getArray() {
		String[] array = {title, price, discount, url, priceWithDelivery};
		return array;
	}
	@Override
	public String toString() {
		return "Product [title=" + title + ", price=" + price + ", discount=" + discount + ", url=" + url
				+ ", priceDelivery=" + priceWithDelivery + "]";
	}
}
