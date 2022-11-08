import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.Locale;
public class GPACalculator extends JFrame
    {
        private JLabel gradeL, semestersL, emptyL; //labels

        private JTextField gradeTF, semestersTF; //creation of text fields

        private JButton addButton, gpaButton, resetButton, exitButton; //creation of buttons

        //buttons
        private AddButtonHandler abHandler; //addition
        private GPAButtonHandler cbHandler; //calculation
        private ResetButtonHandler rbHandler; //reset
        private ExitButtonHandler ebHandler; //exit

        private static final int WIDTH = 2000; //set up of the frame
        private static final int HEIGHT = 1000;

        //variables for calculations
        private static double totalSemesters = 0.0; //semester
        private static double gradePoints = 0.0; //grade
        private static double totalGPA = 0.0; //result

        public GPACalculator()
        {
            //set up(printing)
            //lables
            gradeL = new JLabel("Enter your grade: ", SwingConstants.RIGHT);
            semestersL = new JLabel("Enter number of semesters you were taking this class: ", SwingConstants.RIGHT);
            emptyL = new JLabel("", SwingConstants.RIGHT);

            //text fields
            gradeTF = new JTextField(20);
            semestersTF = new JTextField(20);

            //buttons
            addButton = new JButton("Add Class"); //add
            abHandler = new AddButtonHandler();
            addButton.addActionListener(abHandler);

            gpaButton = new JButton("Click to find out GPA"); //final result
            cbHandler = new GPAButtonHandler();
            gpaButton.addActionListener(cbHandler);

            resetButton = new JButton("Reset"); //reset
            rbHandler = new ResetButtonHandler();
            resetButton.addActionListener(rbHandler);

            exitButton = new JButton("Exit"); //exit
            ebHandler = new ExitButtonHandler();
            exitButton.addActionListener(ebHandler);

            setTitle("GPA Calculator");

            Container pane = getContentPane();

            pane.setLayout(new GridLayout(3, 3));

            pane.add(gradeL); //adding the buttons to the panel
            pane.add(gradeTF);
            pane.add(addButton);
            pane.add(semestersL);
            pane.add(semestersTF);
            pane.add(gpaButton);
            pane.add(emptyL);
            pane.add(resetButton);
            pane.add(exitButton);

            //print
            setSize(WIDTH, HEIGHT);
            setVisible(true);
            setDefaultCloseOperation(EXIT_ON_CLOSE);

        }

        private class AddButtonHandler implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {
                if (gradeTF.getText().equals("") && semestersTF.getText().equals(""))
                {
                    noEntryMsg(); //if no entry, it show user a message
                }
                else
                {
                    //variables for calculation
                    String grade = gradeTF.getText();
                    grade = grade.toLowerCase();
                    double semesters = 0;
                    try{ //checks if user's input was a double
                        semesters =Double.parseDouble(semestersTF.getText())*1;
                    }
                    catch(Exception number){
                        JOptionPane.showMessageDialog(null,
                                number + " is not a number",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    double points = 0.0;

                    try{ //checks if user's input was a grade from the list
                        switch (grade) {
                            case "a+" : points = semesters * 4.30; break;
                            case "a" : points = semesters * 4.0; break;
                            case "a-" : points = semesters * 3.7; break;
                            case "b+" : points = semesters * 3.3; break;
                            case "b" : points = semesters * 3.0; break;
                            case "b-" : points = semesters * 2.7; break;
                            case "c+" : points = semesters * 2.3; break;
                            case "c" : points = semesters * 2.0; break;
                            case "c-" : points = semesters * 1.7; break;
                            case "d+" : points = semesters * 1.3; break;
                            case "d" : points = semesters * 1.0; break;
                            default: points = 0/0; //if nothing applies it will catch an arithmetic Exception
                        }
                    }
                    catch (ArithmeticException exp){
                        JOptionPane.showMessageDialog(null,
                                grade + " is not a grade",
                                "Inane error",
                                JOptionPane.ERROR_MESSAGE); //error message
                    }



                    totalSemesters += semesters;
                    gradePoints += points; //total number from previous calculations

                    gradeTF.setText("");
                    semestersTF.setText("");
                }
            }
        }
        private class ResetButtonHandler implements ActionListener //reset button
        {
            public void actionPerformed(ActionEvent e)
            {
                String msg = "Resetting will delete all previous entries.\n" +
                        "Do you want to continue?";

                int answer = JOptionPane.showConfirmDialog (null,
                        msg, "Click Yes of No",JOptionPane.YES_NO_OPTION); //double check with the user

                if (answer == JOptionPane.YES_OPTION) //reset of the program
                {
                    gradeTF.setText("");
                    semestersTF.setText("");


                    totalSemesters = 0.0;
                    gradePoints = 0.0;
                }

            }
        }
        private class GPAButtonHandler implements ActionListener //calculator
        {
            public void actionPerformed(ActionEvent e)
            {
                if (gradePoints == 0.0)
                {
                    if (gradeTF.getText().equals("") && semestersTF.getText().equals(""))
                    {
                        noEntryMsg(); //no entry message
                    }
                    else
                    {
                        String msg = "Click the ADD button\n and then click GPA again.";

                        JOptionPane.showMessageDialog(null, msg, "Result",
                                JOptionPane.INFORMATION_MESSAGE); //not enough elements given by the user
                    }
                }
                else
                {

                    totalGPA = gradePoints/totalSemesters; //calculation

                    DecimalFormat df = new DecimalFormat("0.000");

                    String outputStr = "GPA: " + df.format(totalGPA) + "\n"; //output

                    JOptionPane.showMessageDialog(null, outputStr, "Result",
                            JOptionPane.INFORMATION_MESSAGE); //result message

                    int answer = JOptionPane.showConfirmDialog (null,
                            "Want to try again?", "Click Yes of No",
                            JOptionPane.YES_NO_OPTION); //continue message

                    if (answer == JOptionPane.YES_OPTION) //if yes -> resets the program
                    {
                        gradeTF.setText("");
                        semestersTF.setText("");

                        totalSemesters = 0.0;
                        gradePoints = 0.0;

                    }
                    else if (answer == JOptionPane.NO_OPTION) //if not -> closes the window
                    {
                        dispose();
                    }
                }
            }
        }

        private class ExitButtonHandler implements ActionListener //exit button
        {
            public void actionPerformed(ActionEvent e)
            {
                dispose(); //closes the window
            }
        }

        public static void noEntryMsg() //no entry message
        {
            String msg = "Enter grade and the number of semesters.";
            JOptionPane.showMessageDialog(null, msg, "Result",
                    JOptionPane.INFORMATION_MESSAGE);
        }
}
