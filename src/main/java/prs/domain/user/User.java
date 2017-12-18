package prs.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonProperty("Username")
	private String username;
	@JsonProperty("Password")
	private String password;
//	@Column(name="firstname")	//Remove all of these except for boolean
	@JsonProperty("FirstName")
	private String firstName;
	@JsonProperty("LastName")
	private String lastName;
	@JsonProperty("Phone")
	private String phone;
	@JsonProperty("Email")
	private String email;
	@Column(name="isreviewer")
	@JsonProperty("IsReviewer")
	private boolean reviewer;
	@Column(name="isadmin")	//Keep the boolean Column annotation
	@JsonProperty("IsAdmin")
	private boolean admin;
	@Column(name="isactive")	
	@JsonProperty("IsActive")
	private boolean active;
	@JsonProperty("UpdatedByUser")
	private int updatedByUser;
//	@Column(name="datecreated")
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
//	private TimeStamp dateCreated;
	
	public User() {
		id = 0;
		username = "";
		password = "";
		firstName = "";
		lastName = "";
		phone = "";
		email = "";
		reviewer = false;
		admin = false;
		active = true;
		updatedByUser = 1;	
	}
			
	public User(int id, String username, String password, String firstName, String lastName, String phone, String email,
			boolean reviewer, boolean admin, boolean active, 
			int updatedByUser) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.reviewer = reviewer;
		this.admin = admin;
		this.active = active;
		this.updatedByUser = updatedByUser;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isReviewer() {
		return reviewer;
	}

	public void setReviewer(boolean reviewer) {
		this.reviewer = reviewer;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}	

	public int getUpdatedByUser() {
		return updatedByUser;
	}

	public void setUpdatedByUser(int updatedByUser) {
		this.updatedByUser = updatedByUser;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + ", reviewer=" + reviewer
				+ ", admin=" + admin + ", active=" + active +  
				", updatedByUser=" + updatedByUser + "]";
	}
	
}
