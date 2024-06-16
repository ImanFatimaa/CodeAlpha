package codealphainternshiptasknumber1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Room {
    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }
}

class Reservation {
    Room room;
    String customerName;
    String startDate;
    String endDate;

    Reservation(Room room, String customerName, String startDate, String endDate) {
        this.room = room;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}

public class HotelReservationSystem {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();

    private static JFrame frame;
    private static JComboBox<String> roomTypeComboBox;
    private static JTextField customerNameField;
    private static JTextField startDateField;
    private static JTextField endDateField;
    private static JTextArea reservationArea;

    public static void main(String[] args) {
        frame = new JFrame("Hotel Reservation System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        addRooms();

        JLabel customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeComboBox = new JComboBox<>();
        for (Room room : rooms) {
            roomTypeComboBox.addItem(room.type);
        }
        JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD):");
        startDateField = new JTextField(10);
        JLabel endDateLabel = new JLabel("End Date (YYYY-MM-DD):");
        endDateField = new JTextField(10);
        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(new ReserveButtonListener());

        reservationArea = new JTextArea(10, 50);
        reservationArea.setEditable(false);

        frame.add(customerNameLabel);
        frame.add(customerNameField);
        frame.add(roomTypeLabel);
        frame.add(roomTypeComboBox);
        frame.add(startDateLabel);
        frame.add(startDateField);
        frame.add(endDateLabel);
        frame.add(endDateField);
        frame.add(reserveButton);
        frame.add(new JScrollPane(reservationArea));

        frame.setVisible(true);
    }

    private static void addRooms() {
        rooms.add(new Room("Single", 100.0));
        rooms.add(new Room("Double", 150.0));
        rooms.add(new Room("Suite", 250.0));
    }

    private static class ReserveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String customerName = customerNameField.getText();
            String roomType = (String) roomTypeComboBox.getSelectedItem();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();

            Room selectedRoom = null;
            for (Room room : rooms) {
                if (room.type.equals(roomType)) {
                    selectedRoom = room;
                    break;
                }
            }

            if (selectedRoom != null) {
                Reservation reservation = new Reservation(selectedRoom, customerName, startDate, endDate);
                reservations.add(reservation);
                updateReservationArea();
                JOptionPane.showMessageDialog(frame, "Reservation made successfully!");
            } else {
                JOptionPane.showMessageDialog(frame, "Room type not available.");
            }
        }
    }

    private static void updateReservationArea() {
        StringBuilder reservationsText = new StringBuilder();
        for (Reservation reservation : reservations) {
            reservationsText.append("Customer Name: ").append(reservation.customerName).append("\n")
                .append("Room Type: ").append(reservation.room.type).append("\n")
                .append("Start Date: ").append(reservation.startDate).append("\n")
                .append("End Date: ").append(reservation.endDate).append("\n")
                .append("Price per Night: $").append(reservation.room.price).append("\n")
                .append("----------\n");
        }
        reservationArea.setText(reservationsText.toString());
    }
}
