package Entity;

import java.util.ArrayList;

public class promoPackage {
	private ArrayList<Item> items = new ArrayList<Item>();
	private String packageDesc;
	private double price;
	
	public promoPackage (ArrayList<Item> items, String packageDesc, double price) {
		this.items = items;
		this.packageDesc = packageDesc;
		this.price = price;
	}
	
	public ArrayList<Item> getPackage() {
        return items;
    }
	
    public String getDesc() {
        return this.packageDesc;
    }

    public void setDesc(String packageDesc) {
        this.packageDesc = packageDesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
	
	
}
