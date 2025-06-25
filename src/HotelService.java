import java.util.*;
import java.text.SimpleDateFormat;

public class HotelService {
    private ArrayList<Room> rooms = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Booking> bookings = new ArrayList<>();
    private int bookingCounter = 1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void setUser(int userId, int balance) {
        User existingUser = findUser(userId);
        if (existingUser != null) {
            existingUser.setBalance(balance);
        } else {
            users.add(new User(userId, balance));
        }
    }

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        Room existingRoom = findRoom(roomNumber);
        if (existingRoom != null) {
            existingRoom.setType(roomType);
            existingRoom.setPricePerNight(roomPricePerNight);
        } else {
            rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
        }
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) throws Exception {
        User user = findUser(userId);
        Room room = findRoom(roomNumber);

        if (user == null || room == null) {
            throw new Exception("User or Room not found");
        }

        if (checkIn.after(checkOut)) {
            throw new Exception("Invalid date range");
        }

        if (isRoomBooked(roomNumber, checkIn, checkOut)) {
            throw new Exception("Room already booked for these dates");
        }

        int nights = (int) ((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
        int totalCost = nights * room.getPricePerNight();

        if (user.getBalance() < totalCost) {
            throw new Exception("Insufficient balance");
        }

        user.setBalance(user.getBalance() - totalCost);
        bookings.add(new Booking(bookingCounter++, user, room, checkIn, checkOut));
    }

    public void printAll() {
        System.out.println("=== Rooms ===");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            Room r = rooms.get(i);
            System.out.printf("Room %d | Type: %s | Price/night: %d%n",
                    r.getRoomNumber(), r.getType(), r.getPricePerNight());
        }

        System.out.println("\n=== Bookings ===");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            Booking b = bookings.get(i);
            System.out.printf("Booking %d | User: %d | Room: %d | Check-in: %s | Check-out: %s%n",
                    b.getBookingId(), b.getUser().getUserId(), b.getRoom().getRoomNumber(),
                    dateFormat.format(b.getCheckIn()), dateFormat.format(b.getCheckOut()));
        }
    }

    public void printAllUsers() {
        System.out.println("=== Users ===");
        for (int i = users.size() - 1; i >= 0; i--) {
            User u = users.get(i);
            System.out.printf("User %d | Balance: %d%n", u.getUserId(), u.getBalance());
        }
    }

    private User findUser(int userId) {
        for (User user : users) {
            if (user.getUserId() == userId) {
                return user;
            }
        }
        return null;
    }

    private Room findRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }

    private boolean isRoomBooked(int roomNumber, Date checkIn, Date checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoom().getRoomNumber() == roomNumber &&
                    !(checkOut.before(booking.getCheckIn()) || checkIn.after(booking.getCheckOut()))) {
                return true;
            }
        }
        return false;
    }
}