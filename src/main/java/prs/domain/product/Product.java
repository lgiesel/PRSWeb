package prs.domain.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

import prs.domain.vendor.Vendor;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@ManyToOne
//	@JoinColumn(name="vendorID")	
	@JoinColumn(name="vendorid")	//vendorid matches column in DB table
	private Vendor vendor;
	@Column(name="partnumber")
	@JsonProperty("PartNumber")
    private String partNumber;
	@JsonProperty("Name")
    private String name;
	@JsonProperty("Price")
    private double price;
	@JsonProperty("Unit")
    private String unit;
	@Column(name="photopath")
	@JsonProperty("PhotoPath")
    private String photoPath;
	
	public Product() {
		id = 0;
		vendor = new Vendor();
		partNumber = "";
		name = "";
		price = 0.0;
		unit = "";
		photoPath = "";		
	}			
	
	public Product(int id, Vendor vendor, String partNumber, String name, double price, String unit, String photoPath) {
		this.id = id;
		this.vendor = vendor;
		this.partNumber = partNumber;
		this.name = name;
		this.price = price;
		this.unit = unit;
		this.photoPath = photoPath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendorID(Vendor vendor) {
		this.vendor = vendor;
	}

	public String getPartNumber() {
		return partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", vendor=[" + vendor + "], partNumber=" + partNumber + ", name=" + name
				+ ", price=" + price + ", unit=" + unit + ", photoPath=" + photoPath + "]";
	}
   
}
