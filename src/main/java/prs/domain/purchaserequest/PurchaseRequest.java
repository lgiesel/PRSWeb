package prs.domain.purchaserequest;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

//import prs.domain.purchaserequest.PurchaseRequestLineItem;
import prs.domain.status.Status;
import prs.domain.user.User;

@Entity
@Table(name="purchaserequest")
public class PurchaseRequest implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="UserID")
	private User user;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("Justification")
	private String justification;
	@JsonProperty("DateNeeded")
	@Column(name="dateneeded") //Make variable all lowercase because camel case JPA puts a space btween words; it is NOT the DB col name ; joincolumnn is DB name
	private Timestamp dateNeeded;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")	
	@JsonProperty("DeliveryMode")
	@Column(name="deliverymode")
	private String deliveryMode;
	@ManyToOne
	@JoinColumn(name="statusid")
//	@JsonProperty("StatusID")
	private Status status;
	@JsonProperty("Total")
	private double total;
	@JsonProperty("SubmittedDate")
	@Column(name="submitteddate")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")	
	private Timestamp submittedDate;
	@JsonProperty("ReasonForRejection")
//	@Column(name="reasonforrejection")
	private String reasonForRejection;
	@JsonProperty("UpdatedByUser")
//	@Column(name="updatedbyuser")
	private int updatedByUser;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL) 
	@JoinColumn(name="purchaserequestid")
	private List<PurchaseRequestLineItem> prli;
	
	public PurchaseRequest() {
		id = 0;
		user = new User();
		description = "";
		justification = "";
		dateNeeded = Timestamp.valueOf(LocalDateTime.now());;
		deliveryMode = "";
		status = new Status();
		total = 0.0;
		submittedDate = null;
		reasonForRejection = "";
		updatedByUser = 1;
		prli = new ArrayList<PurchaseRequestLineItem>(); 		
	}
		
	public PurchaseRequest(int id, User user, String description, String justification, Timestamp dateNeeded,
			String deliveryMode, Status status, double total, Timestamp submittedDate, String reasonForRejection,
			int updatedByUser) {
		this.id = id;
		this.user = user;
		this.description = description;
		this.justification = justification;
		this.dateNeeded = dateNeeded;
		this.deliveryMode = deliveryMode;
		this.status = status;
		this.total = total;
		this.submittedDate = submittedDate;
		this.reasonForRejection = reasonForRejection;
		this.updatedByUser = updatedByUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
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

	public String getReasonForRejection() {
		return reasonForRejection;
	}

	public void setReasonForRejection(String reasonForRejection) {
		this.reasonForRejection = reasonForRejection;
	}

	public int getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(int updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	public List<PurchaseRequestLineItem> getPrli() {
			return prli;
	}

	public void setPrli(ArrayList<PurchaseRequestLineItem> prli) {
		this.prli = prli;
	}
	
//	public void addPurchaseRequestLineItem(PurchaseRequestLineItem prli) {
//		this.prli.add(prli);
//	}

	@Override
	public String toString() {
		return "\nPurchaseRequest= [" + 
	              "\nid=" + id +  ", description=" + description + ", " + 
	              "justification=" + justification + 
	              ", dateNeeded= " + dateNeeded +
	              ", deliveryMode=" + deliveryMode +  
	              ", total=" + total + 
	              ", submittedDate=" + submittedDate +
	              ", reasonForRejection=" + reasonForRejection +
	              ",\n" + getUser() +  
	              ",\n" + getStatus() + 
	              "\nLineItems: [" +  prli +"\n" + 
	              "]\n";
	}	

}
