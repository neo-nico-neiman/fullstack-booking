package Program;

import DataBase.BookingDao;
import DataBase.RoomDAO;
import DataBase.RoomTypeDAO;
import DataBase.GuestDAO;

import java.sql.Date;
import java.time.LocalDate;

public class Main {

    public static void main (String[]args){

        RoomTypeDAO rtDAO= new RoomTypeDAO();
        RoomType rType= new RoomType();
        Room room= new Room();
        RoomDAO rDAO= new RoomDAO();
        Guest user= new Guest();
        GuestDAO uDao= new GuestDAO();
        Booking newBooking= new Booking();
        BookingDao newBDAO= new BookingDao();


//        rType.setPricePerNight(10.50);
//        rType.setMaxGuestAAllowance(1);
//        rType.setCategory("single");
//        rtDAO.addRoomType(rType);
//        room.setRoomType(rType);
//        room.setAvailable(true);
//        room.setRoomNumber(101);
//        rDAO.addRoom(room);
//
//        rType.setPricePerNight(20.50);
//        rType.setMaxGuestAAllowance(2);
//        rType.setCategory("double");
//        rtDAO.addRoomType(rType);
//        room.setRoomType(rType);
//        room.setAvailable(true);
//        room.setRoomNumber(102);
//        rDAO.addRoom(room);
//
//        rType.setPricePerNight(40.50);
//        rType.setMaxGuestAAllowance(3);
//        rType.setCategory("triple");
//        rtDAO.addRoomType(rType);
//        room.setRoomType(rType);
//        room.setAvailable(true);
//        room.setRoomNumber(103);
//        rDAO.addRoom(room);
//        System.out.println(rDAO.getRoom(105));

//        int roomNumber=105;
//        room.setAvailable(true);
//        room.setRoomNumber(roomNumber);
//        room.setRoomCategoryName("triple");
//        rDAO.addRoom(room);
//
//        user.setFirstName("Pedro");
//        user.setLastName("Real");
//        user.setEmail("pedrito@gmail.com");
//        user.setStreetName("Sanat alora alto");
//        user.setStreetNumber("200 A");
//        user.setCity("Tijauna");
//        user.setPhone(521585558);
//        user.setProvince("Tejaco");
//        user.setPostalCode("L0OP78");
//        user.setCountry("Guatemala");
//        user.setPassword("123456");
//        uDao.addUser(user);
//        newBooking.setCheckIn(LocalDate.of(2019,10,2));
//        newBooking.setCheckOut(LocalDate.of(2019,10,9));
//        newBooking.setIdExistingUser(2);
//        newBooking.setRoomNumber(101);
//        newBDAO.addBooking(newBooking);
//        Date checkIn= Date.valueOf("2019-11-8");
//        Date checkOut= Date.valueOf("2019-11-9");
//        System.out.println(newBDAO.checkRoomAvailability(101,checkIn,checkOut));
////        System.out.println(checkIn);
        
//        RoomType r= new RoomType();
//        r.setRoomType("Catalina", 3, 110.23);
//        RoomTypeDAO rD= new RoomTypeDAO();
//        rD.addRoomType(r);
//
//        Guest newGuest= new Guest();
//        newGuest.setFirstName("Renato");
//      
//
//        User guest= new Guest ();
//        guest.setCity("T");
//        
//        GuestDAO n= new GuestDAO();
//        n.addUser(newGuest);
        

    }
}
