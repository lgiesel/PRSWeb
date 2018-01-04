package prs.domain.purchaserequest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PurchaseRequestLineItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int purchaseRequestID;
	private int productID;
	private int quantity;
//	private int updatedByUser;
	
	public PurchaseRequestLineItem() {
		id = 0;
		purchaseRequestID = 0;
		productID = 0;
		quantity = 0;
//		updatedByUser = 1;		
	}
	
	public PurchaseRequestLineItem(int id, int purchaseRequestID,  
			int productID, int quantity//, int updatedByUser
			) {
		this.id = id;
		this.purchaseRequestID = purchaseRequestID;
		this.productID = productID;
		this.quantity = quantity;
//		this.updatedByUser = updatedByUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseRequestID() {
		return purchaseRequestID;
	}
	public void setPurchaseRequestID(int purchaseRequestID) {
		this.purchaseRequestID = purchaseRequestID;
	}
	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

//	public int getUpdatedByUser() {
//		return updatedByUser;
//	}
//
//	public void setUpdatedByUser(int updatedByUser) {
//		this.updatedByUser = updatedByUser;
//	}

	@Override
	public String toString() {
		return "\nPurchaseRequestineItem= [id=" + id + //", purchaseRequest=" + purchaserequest + 
				", productID=" + productID + 
				  ", quantity=" + quantity //+ ", updatedByUser= " + updatedByUser
				  + "]";
	}	

}
