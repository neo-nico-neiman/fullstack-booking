package DataBase;

import Program.RoomType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoomTypeDAO {
	private Connection con = null;

	public void connect() {
		try {
			con = MySQLJDBCUtil.getConnection();
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public RoomType getRoomType(String categoryName) {
		connect();
		RoomType roomType = new RoomType();
		try {

			// get values from DataBase
			String query = "SELECT * FROM room_type WHERE category_name=" + categoryName;
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			rs.next();
			// Set values on RoomType object
			String category = rs.getString("category_name");
			int maxGuestAllowance = rs.getInt("max_allowance");
			double pricePerNight = rs.getDouble("price_per_night");
			String imgURL = rs.getString("image");
			String roomDescription = rs.getString("room_description");
			String roomDetails = rs.getString("room_details");

			roomType.setRoomType(category, maxGuestAllowance, pricePerNight, imgURL, roomDetails, roomDescription);

			con.close();
			st.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return roomType;
	}

	public void addRoomType(RoomType newRoomType) {
		connect();
		try {
			String query = "INSERT into room_type values (?,?,?)";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, newRoomType.getCategory());
			pst.setDouble(2, newRoomType.getPricePerNight());
			pst.setInt(3, newRoomType.getMaxGuestAAllowance());
			pst.executeUpdate();
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

	public void updateCategoryName(String categoryName, String newName) {
		connect();
		try {
			String query = "UPDATE room_type SET category_name=? WHERE category_name= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, categoryName);
			pst.setString(2, newName);
			pst.executeUpdate();
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
			;
		}
	}

	public void updatePPNight(String categoryName, double pPNight) {
		connect();
		try {
			String query = "UPDATE room_type SET price_per_night=? WHERE category_name= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setDouble(1, pPNight);
			pst.setString(2, categoryName);
			pst.executeUpdate();
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
			;
		}
	}

	public void updateMaxAllowance(String categoryName, int maxAllowance) {
		connect();
		try {
			String query = "UPDATE room_type SET max_allowance=? WHERE category_name= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setDouble(1, maxAllowance);
			pst.setString(2, categoryName);
			pst.executeUpdate();
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
			;
		}
	}

	public void updateImg(String categoryName, String imgURL) {
		connect();
		try {
			String query = "UPDATE room_type SET image=? WHERE category_name= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, imgURL);
			pst.setString(2, categoryName);
			pst.executeUpdate();
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
			;
		}
	}

	public void updateRoomDescription(String categoryName, String roomDescription) {
		connect();
		try {
			String query = "UPDATE room_type SET room_description=? WHERE category_name= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, roomDescription);
			pst.setString(2, categoryName);
			pst.executeUpdate();
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
			;
		}
	}

	public void updateRoomDetails(String categoryName, String roomDetails) {
		connect();
		try {
			String query = "UPDATE room_type SET room_details=? WHERE category_name= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, roomDetails);
			pst.setString(2, categoryName);
			pst.executeUpdate();
			con.close();
			pst.close();
		} catch (Exception ex) {
			System.out.println(ex);
			;
		}
	}

}
