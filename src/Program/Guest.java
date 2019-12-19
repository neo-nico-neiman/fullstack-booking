package Program;

public class Guest extends User{

	public Guest() {}
	
	public Guest (String uname, String pass) {
		super();
		this.userName=uname;
		this.password=pass;
	}

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone=" + phone +
                ", streetNumber='" + streetNumber + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetLineTwo='" + streetLineTwo + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", Country='" + country + '\'' +
                '}';
    }
}
