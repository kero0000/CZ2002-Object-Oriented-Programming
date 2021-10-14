package Entity;

public class Promotion {
	private String packageName;
	private int price;
	private String descriptions;
	
	public Promotion(){
		super();
	}
	
	public Promotion(String packageName, int price, String descriptions) {
		this.descriptions = descriptions;
		this.packageName = packageName;
		this.price = price;
	}
	
	public String getPackageName() {
		return this.packageName;
	}
	
	public void setPackageName(String packageName) {
		this.packageName  = packageName;
	}
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDecriptions() {
		return this.descriptions;
	}
	
	public void setDescriptions(String descriptions) {
		this.descriptions  = descriptions;
	}
}
	
	
