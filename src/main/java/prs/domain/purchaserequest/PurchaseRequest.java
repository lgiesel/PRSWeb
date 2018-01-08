package prs.domain.purchaserequest;

import java.sql.Timestamp;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PurchaseRequest {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int userID;
	private String description;
	private String justification;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")	
//	@Temporal(TemporalType.DATE)	
	private Timestamp dateNeeded;
	private String deliveryMode;
	private int statusID;
	private double total;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")	
	private Timestamp submittedDate;
//	private String reasonForRejection;
//	private int updatedByUser;

	public PurchaseRequest() {
		id = 0;
		userID = 0;
		description = "";
		justification = "";
//		dateNeeded = Timestamp.valueOf(LocalDateTime.now());
		dateNeeded = new Timestamp(System.currentTimeMillis());
		deliveryMode = "";
		statusID = 0;
		total = 0.0;
		submittedDate = new Timestamp(System.currentTimeMillis());
//		reasonForRejection = "";
//		updatedByUser = 1;
	}
		
	public PurchaseRequest(int id, int userID, String description, String justification, Timestamp dateNeeded,
			String deliveryMode, int statusID, double total, Timestamp submittedDate) {
		this.id = id;
		this.userID = userID;
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.statusID = statusID;
		this.total = total;
		this.submittedDate = submittedDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Timestamp getDateNeeded() {
		return dateNeeded;
	}

	public void setDateNeeded(Timestamp dateNeeded) {
		this.dateNeeded = dateNeeded;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Timestamp getSubmittedDate() {
		return submittedDate;
	}

	public void setSubmittedDate(Timestamp submittedDate) {
		this.submittedDate = submittedDate;
	}

//	public String getReasonForRejection() {
//		return reasonForRejection;
//	}

//	public void setReasonForRejection(String reasonForRejection) {
//		this.reasonForRejection = reasonForRejection;
//	}

//	public int getUpdatedByUser() {
//		return updatedByUser;
//	}

//	public void setUpdatedByUser(int updatedByUser) {
//		this.updatedByUser = updatedByUser;
//	}

	@Override
	public String toString() {
		return "\nPurchaseRequest= [" + 
	              "\nid=" + id +  ", userID=" + userID + 
	              ", description=" + description + ", " + 
	              ", justification=" + justification + 
	              ", dateNeeded= " + dateNeeded +
	              ", deliveryMode=" + deliveryMode +  
	              ", statusID=" + statusID + 
	              ", total=" + total + 
	              ", submittedDate=" + submittedDate +
//	              ", reasonForRejection=" + reasonForRejection +
//	              ", updatedByUser=" + updatedByUser +
	              "]\n";
	}	

}
