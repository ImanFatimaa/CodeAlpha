package codealphainternshiptasknumber1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.border.EmptyBorder;

public class GradeCalculator extends JFrame {
    private ArrayList<Double> grades;
    private JTextField totalInput, totalMarksInput, gradeInput;
    private JTextArea outputArea;
    private JList<String> resultsList;
    private DefaultListModel<String> listModel;
    private int totalGrades = 0, enteredGrades = 0;
    private int totalMarks = 0;
    private JButton addButton, calculateButton;

    public GradeCalculator() {
        grades = new ArrayList<>();
        listModel = new DefaultListModel<>();

        setTitle("Grade Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        // Input panel for total grades and total marks
        JPanel totalInputPanel = new JPanel();
        totalInputPanel.setLayout(new GridLayout(2, 1));

        // Total grades panel
        JPanel totalGradesPanel = new JPanel(new FlowLayout());
        JLabel totalLabel = new JLabel("Enter Total Number of Grades: ");
        totalInput = new JTextField(10);
        JButton totalGradesButton = new JButton("Submit Grades");

        totalGradesPanel.add(totalLabel);
        totalGradesPanel.add(totalInput);
        totalGradesPanel.add(totalGradesButton);

        // Total marks panel
        JPanel totalMarksPanel = new JPanel(new FlowLayout());
        JLabel totalMarksLabel = new JLabel("Enter Total Marks: ");
        totalMarksInput = new JTextField(10);
        JButton totalMarksButton = new JButton("Submit Marks");

        totalMarksPanel.add(totalMarksLabel);
        totalMarksPanel.add(totalMarksInput);
        totalMarksPanel.add(totalMarksButton);

        totalInputPanel.add(totalGradesPanel);
        totalInputPanel.add(totalMarksPanel);

        pane.add(totalInputPanel, BorderLayout.NORTH);

        // Input panel for grades
        JPanel gradeInputPanel = new JPanel();

        JLabel gradeLabel = new JLabel("Enter Grade: ");
        gradeLabel.setBounds(114, 9, 65, 14);
        gradeInput = new JTextField(10);
        gradeInput.setBounds(194, 6, 86, 20);
        addButton = new JButton("Add Grade");
        addButton.setBounds(285, 5, 83, 23);
        calculateButton = new JButton("Calculate");
        gradeInputPanel.setLayout(null);

        gradeInputPanel.add(gradeLabel);
        
                // Output area
                outputArea = new JTextArea();
                outputArea.setBounds(10, 37, 462, 225);
                gradeInputPanel.add(outputArea);
                outputArea.setEditable(false);
        gradeInputPanel.add(gradeInput);
        gradeInputPanel.add(addButton);

        pane.add(gradeInputPanel, BorderLayout.CENTER);
        JScrollPane scrollPane = new JScrollPane();
        pane.add(scrollPane, BorderLayout.EAST);

        // Panel for displaying results
        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JLabel resultLabel = new JLabel("Calculated Grades:");
        resultPanel.add(resultLabel, BorderLayout.NORTH);

        // JList to display results
        resultsList = new JList<>(listModel);
        resultPanel.add(new JScrollPane(resultsList), BorderLayout.CENTER);

        pane.add(resultPanel, BorderLayout.SOUTH);

        // Disable grade input and calculate button initially
        gradeInput.setEnabled(false);
        addButton.setEnabled(false);
        calculateButton.setEnabled(false);

        // Submit total grades button action
        totalGradesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTotalGrades();
            }
        });

        // Submit total marks button action
        totalMarksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitTotalMarks();
            }
        });

        // Add grade button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addGrade();
            }
        });

        // Calculate button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateGrades();
            }
        });

        // Add the calculate button to a panel at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(calculateButton);
        pane.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void submitTotalGrades() {
        try {
            totalGrades = Integer.parseInt(totalInput.getText());
            if (totalGrades > 0) {
                totalInput.setEnabled(false);
                checkReadyForGradeInput();
                outputArea.append("Total number of grades to be entered: " + totalGrades + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid number greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void submitTotalMarks() {
        try {
            totalMarks = Integer.parseInt(totalMarksInput.getText());
            if (totalMarks > 0) {
                totalMarksInput.setEnabled(false);
                checkReadyForGradeInput();
                outputArea.append("Total marks: " + totalMarks + "\n");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a valid number greater than 0.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkReadyForGradeInput() {
        if (totalGrades > 0 && totalMarks > 0) {
            gradeInput.setEnabled(true);
            addButton.setEnabled(true);
        }
    }

    private void addGrade() {
        try {
            double grade = Double.parseDouble(gradeInput.getText());
            if (grade >= 0 && grade <= totalMarks) {
                grades.add(grade);
                enteredGrades++;
                gradeInput.setText("");
                outputArea.append("Grade added: " + grade + "\n");

                if (enteredGrades == totalGrades) {
                    gradeInput.setEnabled(false);
                    addButton.setEnabled(false);
                    JOptionPane.showMessageDialog(this, "All grades have been entered.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    calculateButton.setEnabled(true);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a grade between 0 and " + totalMarks, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid grade.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void calculateGrades() {
        if (grades.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No grades entered.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double sum = 0;
        double highest = grades.get(0);
        double lowest = grades.get(0);

        for (double grade : grades) {
            sum += grade;
            if (grade > highest) highest = grade;
            if (grade < lowest) lowest = grade;
        }

        double average = sum / grades.size();

        
        outputArea.append("Average: " + String.format("%.2f", average) + "\n");
        outputArea.append("Highest Grade: " + String.format("%.2f", highest)+ "\n");
        outputArea.append("Lowest Grade: " + String.format("%.2f", lowest)+ "\n");
      
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GradeCalculator().setVisible(true);
            }
        });
    }
}
