package DataBase;
import Program.Guest;
import Program.User;
import java.sql.*;

public class GuestDAO {
	private Connection con = null;

	public void connect() {

		try {
			con = MySQLJDBCUtil.getConnection();
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public User getUser(int id) {
		connect();
		User guest = new Guest();
		try {

			// get values from DataBase
			String query = "SELECT * FROM user WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			
			while(rs.next()) {

				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String userName = rs.getString("user_name");
				String phone = rs.getString("phone");
				String streetNumber = rs.getString("street_number");
				String streetName = rs.getString("street_name");
				String streetSecondLine = rs.getString("street_line_two");
				String city = rs.getString("city");
				String province = rs.getString("province");
				String postalCode = rs.getString("postal_code");
				String country = rs.getString("country");
	
				// Set values on newly created guest.
	
				guest.setLastName(lastName);
				guest.setFirstName(firstName);
				guest.setPhone(phone);
				guest.setEmail(email);
				guest.setStreetNumber(streetNumber);
				guest.setStreetName(streetName);
				guest.setStreetLineTwo(streetSecondLine);
				guest.setCity(city);
				guest.setProvince(province);
				guest.setPostalCode(postalCode);
				guest.setCountry(country);
			}
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return guest;
	}

	public User getUsername(String uName) {
		connect();
		User guest = new Guest();
		try {

			// get values from DataBase
			String query = "SELECT * FROM user WHERE user_name= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, uName);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			while(rs.next()) {
				String userName = rs.getString("user_name");
				String password = rs.getString("password");

				// Set values on newly created guest.
				guest = new Guest(userName, password);
				
			}

			
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return guest;
	}

	public int addUser(User newGuest) {
		connect();
		int id = 0;
		try {
			String query = "INSERT into user values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, 0);
			pst.setString(2, newGuest.getFirstName());
			pst.setString(3, newGuest.getLastName());
			pst.setString(4, newGuest.getPhone());
			pst.setString(5, newGuest.getEmail());
			pst.setString(6, newGuest.getUserName());
			pst.setString(7, newGuest.getPassword());
			// pst.setString(8, newGuest.getStreetNumber());
			pst.setString(8, "none");
			pst.setString(9, newGuest.getStreetName());
			// pst.setString(10, newGuest.getStreetLineTwo());
			pst.setString(10, "none");
			pst.setString(11, newGuest.getCity());
			// pst.setString(12, newGuest.getProvince());
			pst.setString(12, "none");
			pst.setString(13, newGuest.getPostalCode());
			pst.setString(14, newGuest.getCountry());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getInt(1);
			}

			con.close();
			pst.close();
			rs.close();
			
		} catch (Exception ex) {
			System.out.println(ex);

		}
		return id;
	}

	public int getUserId(String email) {
		connect();
		int id = 0;

		try {

			String query = "SELECT id FROM user WHERE user_name= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.execute();
			ResultSet rs = pst.getResultSet();

			if (rs.next()) {
				
				id = rs.getInt("id");
			}

			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return id;
	}

	public void updateFirstName(int id, String firstName) {
		connect();
		
		try {
			String query = "UPDATE user SET first_name=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, firstName);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			
			System.out.println(ex);
			
		}
	}

	public void updateLastName(int id, String lastName) {
		connect();
		
		try {
			String query = "UPDATE user SET last_name=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, lastName);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
			
		}
	}

	public void updatePhone(int id, long phone) {
		connect();
		
		try {
			String query = "UPDATE user SET phone=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setLong(1, phone);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
			
		}
	}

	public void updateEmail(int id, String email) {
		connect();
		
		try {
			String query = "UPDATE user SET email=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, email);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		
		}
	}

	public void updateUserName(int id, String userName) {
		connect();
		
		try {
			
			String query = "UPDATE user SET user_name=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, userName);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void updatePassword(int id, String password) {
		connect();
		
		try {
			String query = "UPDATE user SET password=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, password);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void updateStreetNumber(int id, int streetNumber) {
		connect();
		
		try {
			String query = "UPDATE user SET street_number=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, streetNumber);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void updateStreetName(int id, String streetName) {
		connect();
		
		try {
			String query = "UPDATE user SET street_name=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, streetName);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);	
		}
	}

	public void updateStreetSecondLine(int id, String streetLineTwo) {
		connect();
		
		try {
			String query = "UPDATE user SET street_line_two=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, streetLineTwo);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void updateCity(int id, String city) {
		connect();
		
		try {
			String query = "UPDATE user SET city=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, city);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void updateProvince(int id, String province) {
		connect();
		
		try {
			String query = "UPDATE user SET province=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, province);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void updatePostalCode(int id, String postalCode) {
		connect();

		try {
			String query = "UPDATE user SET postal_code=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, postalCode);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void updateCountry(int id, String country) {
		connect();
		
		try {
			String query = "UPDATE user SET country=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, country);
			pst.setInt(2, id);
			pst.executeUpdate();
			con.close();
			pst.close();
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
