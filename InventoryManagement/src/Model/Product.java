package Model;

public class Product {
	private int no;
	private String name;
	private int quantity;

	public Product(String name, int quantity) {
		this.name = name;
		this.no = no;
		this.quantity = quantity;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public String getName() {
		return name;
	}

	public int getNo() {
		return no;
	}

	public int getquantity() {
		return quantity;
	}

	public String toString() {
		return no + " " + name+" "+quantity;
	}
}
