public class Room {
    private int roomNumber;
    private RoomType type;
    private int pricePerNight;

    public Room(int roomNumber, RoomType type, int pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    // Getters et setters
    public int getRoomNumber() { return roomNumber; }
    public RoomType getType() { return type; }
    public int getPricePerNight() { return pricePerNight; }
    public void setType(RoomType type) { this.type = type; }
    public void setPricePerNight(int pricePerNight) { this.pricePerNight = pricePerNight; }
}