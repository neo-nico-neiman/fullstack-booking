package Program;

import java.sql.Date;
import java.time.LocalDate;

public class Booking {

    private int bookingId;
    private User newUser;
    private int idExistingUser;
    private Room bookedRoom;
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;
    private int nights;
    private double ppn;
    private double tax;
    private double total;
    

    public Booking(){
        this.newUser= new Guest();
        this.bookedRoom=new Room();
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId(){
        return bookingId;
    }
    public void setNewUser(User newUser){
        this.newUser=newUser;

    }
    public User getUser(){
        return newUser;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date date) {

        this.checkIn = date;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date date) {
        this.checkOut = date;
    }

   

    private void setRoom(Room bookedRoom){
        this.bookedRoom=bookedRoom;
    }
    public Room getRoom(){
        return bookedRoom;
    }

    public int getIdExistingUser() {
        return idExistingUser;
    }

    public void setIdExistingUser(int idExistingUser) {
        this.idExistingUser = idExistingUser;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
    
    public void setNights (int nights) {
    	this.nights=nights;
    }
    
    public int getNights () {
    	return nights;
    }
    
    public void setPPN (double ppn) {
    	this.ppn=ppn;
    }
    
    public double getPPN() {
    	return ppn;
    }
    
    public void setTax (double tax){
    	this.tax=tax;
    }
    
    public double getTax() {
    	return tax;
    }
    
    public void setTotal (double total) {
    	this.total=total;
    }
    
    public double getTotal() {
    	return total;
    }

    public void checkAvailability(boolean isAvailable, Room desiredRoom){
        if(isAvailable){
            setRoom(desiredRoom);
        }else{
            System.out.println("Room Not Available");
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", newUser=" + newUser +
                ", bookedRoom=" + bookedRoom +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ",total="+ total+
                '}';
    }
}
