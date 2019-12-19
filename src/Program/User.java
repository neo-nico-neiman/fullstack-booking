package Program;

public abstract class User {
	
	protected int userId;
	protected String userName;
	protected String password;
	protected String email;
	protected String firstName;
	protected String lastName;
	protected String phone;
	protected String streetNumber;
	protected String streetName;
	protected String streetLineTwo;
	protected String city;
	protected String province;
	protected String postalCode;
	protected String country;
	
	public User() {}
	
	
	
	public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean verifyPassword(String pass) {
    	boolean success=false;
    	if(pass.equals(password)) {
    		success=true;
    	}
        return success;
    }

    private void setPassword(String password) {
        this.password = password;
    }
    
    public boolean setNewPassword(String password, String newPassword) {
    	boolean success=false;
    	
    	if(password.equals(password)) {
    		setPassword(newPassword);
    	}
    	
    	return success;
    }
    public String getPassword () {
    	
    	return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
       
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
        this.phone = (phone);
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetLineTwo() {
        return streetLineTwo;
    }

    public void setStreetLineTwo(String streetLineTwo) {
        this.streetLineTwo = streetLineTwo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
