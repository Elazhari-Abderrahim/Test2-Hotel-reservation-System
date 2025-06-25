import java.util.Date;

public class Booking {
    private int bookingId;
    private User user;
    private Room room;
    private Date checkIn;
    private Date checkOut;

    public Booking(int bookingId, User user, Room room, Date checkIn, Date checkOut) {
        this.bookingId = bookingId;
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    // Getters
    public int getBookingId() { return bookingId; }
    public User getUser() { return user; }
    public Room getRoom() { return room; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }
}