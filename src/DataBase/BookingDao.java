package DataBase;
import Program.Booking;
import Program.Room;
import java.sql.*;
import java.util.ArrayList;

public class BookingDao {

	private Connection con = null;

	public void connect() {
		try {

			con = MySQLJDBCUtil.getConnection();

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

	public Booking getBooking(int id) {
		connect();
		Booking newBooking = new Booking();
		try {

			// get values from DataBase
			String query = "SELECT  b.id, b.check_in, b.check_out, b.nights, b.ppn, b.tax, b.total,"
					+" u.first_name, u.last_name, u.email, u.phone, r.room_number "
					+ "FROM bookings AS b JOIN user AS u " 
					+ "ON b.user_id=u.id" 
					+ " JOIN rooms AS r "
					+ "ON b.room_number=r.room_number" 
					+ " WHERE b.id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			rs.next();

			int bookingId = rs.getInt("id");
			Date checkInDate = rs.getDate("check_in");
			Date checkOutDate = rs.getDate("check_out");
			String firstName = rs.getString("first_name");
			String lastName = rs.getString("last_name");
			String email = rs.getString("email");
			String phone = rs.getString("phone");
			int room_number = rs.getInt("room_number");
			int nights = rs.getInt("nights");
			double ppn = rs.getDouble("ppn");
			double tax = rs.getDouble("tax");
			double total = rs.getDouble("total");

			// Set values on retrieved booking.
			newBooking.setBookingId(bookingId);

			newBooking.setCheckIn(checkInDate);
			newBooking.setCheckOut(checkOutDate);
			newBooking.getUser().setFirstName(firstName);
			newBooking.getUser().setLastName(lastName);
			newBooking.getUser().setEmail(email);
			newBooking.getUser().setPhone(phone);
			newBooking.getRoom().setRoomNumber(room_number);
			newBooking.setNights(nights);
			newBooking.setPPN(ppn);
			newBooking.setTax(tax);
			newBooking.setTotal(total);

			pst.close();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
		}

		return newBooking;
	}

	public int addBooking(Booking newBooking) {
		connect();
		int id = 0;
		try {
			String query = "INSERT into bookings values (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			pst.setInt(1, 0);
			pst.setInt(2, newBooking.getIdExistingUser());
			pst.setInt(3, newBooking.getRoomNumber());
			pst.setDate(4, newBooking.getCheckIn());
			pst.setDate(5, newBooking.getCheckOut());
			pst.setInt(6, 1);
			pst.setInt(7, newBooking.getNights());
			pst.setDouble(8, newBooking.getPPN());
			pst.setDouble(9, newBooking.getTax());
			pst.setDouble(10, newBooking.getTotal());
			pst.executeUpdate();
			ResultSet rs = pst.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
			pst.close();
			con.close();

		} catch (Exception ex) {
			System.out.println(ex);
		}
		return id;
	}

	public void updateBookingStatus(int booking_id, int oneOrZero) {
		connect();
		try {
			String query = "UPDATE bookings SET is_valid=? WHERE id= ?";
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, oneOrZero);
			pst.setInt(2, booking_id);
			pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception ex) {
			System.out.println(ex);
			;
		}
	}

	public boolean checkRoomAvailability(int desiredRoom, Date desiredCheckIn, Date desiredCheckOut)

	{
		// Method to avoid creating a booking on a room that is already booked
		// Based on date
		boolean roomAvailable = true;
		int isValid;

		try {

			String query = "SELECT b.is_valid FROM bookings AS b WHERE"

					/*
					 * Date equal or in between
					 */
					+ " (b.room_number = ? "
					+ "AND b.check_in <= ? "
					+ "AND b.check_out >= ?) "

					/*
					 * Date starting before checkIn and concluding on anytime after checkIn
					 */
					+ "OR "
					+ "(b.room_number = ? "
					+ "AND b.check_in >= ? "
					+ "AND b.check_in < ?) "

					// Date starting after checkIn but before checkOut
					+ "OR "
					+ "(b.room_number = ? " 
					+ "AND b.check_in < ? "
					+ "AND b.check_out > ?)";

			connect();
			PreparedStatement pst = con.prepareStatement(query);
			pst.setInt(1, desiredRoom);
			pst.setDate(2, desiredCheckIn);
			pst.setDate(3, desiredCheckOut);
			pst.setInt(4, desiredRoom);
			pst.setDate(5, desiredCheckIn);
			pst.setDate(6, desiredCheckOut);
			pst.setInt(7, desiredRoom);
			pst.setDate(8, desiredCheckIn);
			pst.setDate(9, desiredCheckIn);
			pst.execute();
			ResultSet rs = pst.getResultSet();
			while (rs.next()) {
				isValid = rs.getInt("is_valid");
				/*
				 * if isValid = 0 then the room has been booked AND the booking has been
				 * cancelled. Therefore, the room is available
				 */
				if (isValid == 1) {
					roomAvailable = false;
				}
				if (isValid == 0) {
					continue;
				}
			}
			con.close();
			pst.close();

		}

		catch (Exception ex) {
			System.out.println(ex);
		}
		return roomAvailable;
	}

	public ArrayList<Room> checkAvailabilityByGuestAndDate(Date desiredCheckIn, Date desiredCheckOut,
			int numberOfGuest) {

		ArrayList<Room> rooms = new ArrayList<Room>();
		int controller = numberOfGuest;
		try {

			// limit the results to numberOfGuest + 1
			while (numberOfGuest < (controller + 2)) {
				String query = "SELECT r.room_number " 
						+ "FROM rooms AS r " 
						+ "JOIN room_type AS rt "
						+ "ON r.room_category=rt.category_name" 
						+ " WHERE rt.max_allowance= ?";

				connect();
				PreparedStatement pst = con.prepareStatement(query);
				pst.setInt(1, numberOfGuest);
				pst.execute();
				ResultSet rs = pst.getResultSet();
				int roomNumber = 0;

				while (rs.next()) {
					// First check if room with assigned roomNumber is FREE
					roomNumber = rs.getInt("room_number");
					boolean roomAvailable = checkRoomAvailability(roomNumber, desiredCheckIn, desiredCheckOut);

					if (roomAvailable == true) {
						RoomDAO rD = new RoomDAO();
						/*
						 * If there is more than one room available, then the next room to show will be
						 * of the same maxGuestallowance AND different category name OR
						 * maxGuestAllowance +1
						 */

						if (rooms.size() > 0) {
							int position = ((rooms.size()) - 1);

							boolean checker = rooms.get(position).getRoomCategoryName()
									.equalsIgnoreCase(rD.getRoom(roomNumber).getRoomCategoryName());
							if (checker) {
								continue;
							} else {

								rooms.add(rD.getRoom(roomNumber));
							}
						} else {
							rooms.add(rD.getRoom(roomNumber));
						}
					}

				}
				con.close();
				pst.close();
				numberOfGuest++;
			};

			// if there is no results return null

			if (numberOfGuest > (controller + 1) && rooms.size() == 0) {
				rooms = null;
			}
		}

		catch (Exception ex) {
			System.out.println(ex);
		}
		return rooms;
	}
}
