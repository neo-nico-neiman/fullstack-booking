package DataBase;

import Program.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class RoomDAO {
    private Connection con=null;
    public void connect(){
        try {
            con=MySQLJDBCUtil.getConnection();
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public Room getRoom (int roomNumber){
        connect();
        Room room= new Room();
        try {

            //get values from room
            String query= "SELECT  r.is_available, rt.category_name, rt.price_per_night, rt.max_allowance, rt.image, rt.room_description, rt.room_details " +
                    "FROM rooms AS r JOIN room_type AS rt " +
                    "ON r.room_category=rt.category_name"+
                    " WHERE r.room_number="+roomNumber;
            Statement st = con.createStatement();
            ResultSet rs =st.executeQuery(query);
            rs.next();            
            room.setRoomNumber(roomNumber);
            room.setRoomType(rs.getString("category_name"), rs.getInt("max_allowance"), rs.getDouble("price_per_night"), rs.getString("image"), rs.getString("room_description"), rs.getString("room_details"));
            room.setTinyIntToIsAvailable(rs.getInt("is_available"));
            con.close();
            st.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }

        return room;
    }
    
    public void addRoom(Room newRoom){
        connect();
        try {
            String query = "INSERT into rooms values (?,?,?)";
            PreparedStatement pst= con.prepareStatement(query);
            pst.setInt(1,newRoom.getRoomNumber());
            pst.setString(2,newRoom.getCategory());
            pst.setInt(3,newRoom.getTinyIntTranslate());

            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
    
    public void updateRoomNumber(int roomNumber, int newRoomNumber){
        connect();
        try{ String query="UPDATE room SET room_number=? WHERE room_number=?";
            PreparedStatement pst= con.prepareStatement(query);
            pst.setInt(1,newRoomNumber);
            pst.setInt(2,roomNumber);
            pst.executeUpdate();
            con.close();
            pst.close();

        } catch (Exception ex) {
            System.out.println(ex);;
        }

    }
    public void updateIsAvailable(int roomNumber, int oneOrZero){
        connect();
        try {
            String query="UPDATE room SET is_available=? WHERE room_number= ?";
            PreparedStatement pst= con.prepareStatement(query);
            pst.setInt(1,oneOrZero);
            pst.setInt(2,roomNumber);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println(ex);;
        }
    }
    public void updateCategory(int roomNumber, String roomCategory){
        connect();
        try {
            String query="UPDATE rooms SET room_category=? WHERE room_number= ?";
            PreparedStatement pst= con.prepareStatement(query);
            pst.setInt(1,roomNumber);
            pst.setInt(2,roomNumber);
            pst.executeUpdate();
            con.close();
            pst.close();
        } catch (Exception ex) {
            System.out.println(ex);;
        }
    }

}
