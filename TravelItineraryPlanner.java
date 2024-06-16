package codealphainternshiptasknumber1;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class Destination {
    String name;
    String startDate;
    String endDate;
    double budget;

    Destination(String name, String startDate, String endDate, double budget) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
    }
}

public class TravelItineraryPlanner {
    private static ArrayList<Destination> destinations = new ArrayList<>();
    private static JFrame frame;
    private static JTextArea itineraryArea;
    private static JLabel totalBudgetLabel;

    public static void main(String[] args) {
        createAndShowGUI();
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Travel Itinerary Planner");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome to the Travel Itinerary Planner!");
        welcomeLabel.setBounds(50, 10, 400, 25);
        frame.add(welcomeLabel);

        JButton addDestinationButton = new JButton("Add Destination");
        addDestinationButton.setBounds(50, 50, 400, 25);
        frame.add(addDestinationButton);

        JButton generateItineraryButton = new JButton("Generate Itinerary");
        generateItineraryButton.setBounds(50, 90, 400, 25);
        frame.add(generateItineraryButton);

        JButton showMapsButton = new JButton("Show Maps (Placeholder)");
        showMapsButton.setBounds(50, 130, 400, 25);
        frame.add(showMapsButton);

        JButton showWeatherButton = new JButton("Show Weather Info");
        showWeatherButton.setBounds(50, 170, 400, 25);
        frame.add(showWeatherButton);

        JButton calculateBudgetButton = new JButton("Calculate Budget");
        calculateBudgetButton.setBounds(50, 210, 400, 25);
        frame.add(calculateBudgetButton);

        itineraryArea = new JTextArea();
        itineraryArea.setBounds(50, 250, 400, 150);
        frame.add(itineraryArea);

        totalBudgetLabel = new JLabel("Total Budget: $0.00");
        totalBudgetLabel.setBounds(50, 420, 400, 25);
        frame.add(totalBudgetLabel);

        addDestinationButton.addActionListener(e -> addDestination());
        generateItineraryButton.addActionListener(e -> generateItinerary());
        showMapsButton.addActionListener(e -> showMaps());
        showWeatherButton.addActionListener(e -> showWeatherInfo());
        calculateBudgetButton.addActionListener(e -> calculateBudget());

        frame.setVisible(true);
    }

    private static void addDestination() {
        JTextField nameField = new JTextField();
        JTextField startDateField = new JTextField();
        JTextField endDateField = new JTextField();
        JTextField budgetField = new JTextField();

        Object[] fields = {
                "Destination Name:", nameField,
                "Start Date (YYYY-MM-DD):", startDateField,
                "End Date (YYYY-MM-DD):", endDateField,
                "Budget:", budgetField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Add Destination", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String startDate = startDateField.getText();
            String endDate = endDateField.getText();
            double budget;
            try {
                budget = Double.parseDouble(budgetField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid budget. Please enter a valid number.");
                return;
            }

            Destination destination = new Destination(name, startDate, endDate, budget);
            destinations.add(destination);
            JOptionPane.showMessageDialog(frame, "Destination added successfully!");
        }
    }

    private static void generateItinerary() {
        StringBuilder itinerary = new StringBuilder();
        for (Destination destination : destinations) {
            itinerary.append("Destination: ").append(destination.name).append("\n")
                    .append("Start Date: ").append(destination.startDate).append("\n")
                    .append("End Date: ").append(destination.endDate).append("\n")
                    .append("Budget: $").append(destination.budget).append("\n")
                    .append("-------------------------\n");
        }
        itineraryArea.setText(itinerary.toString());
    }

    private static void calculateBudget() {
        double totalBudget = destinations.stream().mapToDouble(destination -> destination.budget).sum();
        totalBudgetLabel.setText("Total Budget: $" + totalBudget);
    }

    private static void showWeatherInfo() {
        StringBuilder weatherInfo = new StringBuilder("Weather Info (Predicted):\n\n");
        Random random = new Random();
        for (Destination destination : destinations) {
            String weatherPrediction = predictWeather(destination.name, random);
            weatherInfo.append("Destination: ").append(destination.name).append("\n")
                    .append("Predicted Weather: ").append(weatherPrediction).append("\n")
                    .append("-------------------------\n");
        }
        JOptionPane.showMessageDialog(frame, weatherInfo.toString());
    }

    private static String predictWeather(String destination, Random random) {
        // Dummy weather prediction based on destination name
        String[] weatherConditions = {"Sunny with occasional clouds", "Partly cloudy", "Rainy with showers", "Snowy"};
        int index = random.nextInt(weatherConditions.length);
        return weatherConditions[index];
    }

    private static void showMaps() {
        JOptionPane.showMessageDialog(frame, "Maps feature is a placeholder and requires integration with a mapping API.");
    }
}
