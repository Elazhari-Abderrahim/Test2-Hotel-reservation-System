import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws ParseException {
        HotelService service = new HotelService();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Ajout des chambres
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        // Ajout des utilisateurs
        service.setUser(1, 5000);
        service.setUser(2, 10000);

        // Réservations avec gestion des exceptions
        try {
            Date checkIn = sdf.parse("30/06/2026");
            Date checkOut = sdf.parse("01/07/2026");
            service.bookRoom(1, 2, checkIn, checkOut);
        } catch (Exception e) {
            System.out.println("Erreur de réservation: " + e.getMessage());
        }

        // Affichage des données
        service.printAllUsers();
        service.printAll();

    }
}