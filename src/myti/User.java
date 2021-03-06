package myti;

public class User {

	
	private String id;
	private String email;
	private String name;
	private UserTypeEnum type;
	public User(String id, String email, String name, UserTypeEnum type) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public UserTypeEnum getType() {
		return type;
	}
	public void setType(UserTypeEnum type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", name=" + name + ", type=" + type + "]";
	}
	
	
	
	
	
}
