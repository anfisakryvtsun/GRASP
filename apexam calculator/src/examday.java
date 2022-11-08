import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.*;

public class examday {

    public static Object[] ap(boolean year3, ArrayList<String> selected){
        String[] mandatory = {"AP English", "AP Literature", "Writing Rethorics", "AP Seminar"}; //Mandatory
        String[] doubles = {"AP English", "Writing Rethorics", "AP Calculus", "AP Economics", "AP Statistics"}; //subjects that have multiple groups
        ArrayList<String> returnB = new ArrayList<>(); //List that store AP's that the user can still choose
        String[][] apSelect= {{"AP 2-D Art and Design", "AP Biology", "AP Comparative Government and Politics", "AP Economics", "AP Statistics", "AP French"},
                {"AP Calculus", "AP Statistics", "Pre-calculus","Writing Rethorics"},
                {"AP Calculus", "AP Psychology", "AP Art History", "World Language French", "World Language German", "AP Seminar","AP English"},
                {"AP Computer Science A", "AP European History", "AP Human Geography","AP Economics", "AP German", "Writing Rethorics"},
                {"AP Physics", "AP English", "AP Literature", "Leaf Core"}}; //2d array of ap's [number of the group][ap's in the group]
        String[] group = {null,null,null,null,null}; //storing of 5 ap group slots
        for (int i = 0; i < selected.size(); i++) {
            for (int j = 0; j < apSelect.length; j++) {
                for (int k = 0; k < apSelect[j].length; k++) {
                    if (selected.get(i).equals(apSelect[j][k])) {
                        group[j] = apSelect[j][k];
                        if(year3 == true){
                            group[4] = "Leaf Core"; //this whole nested for loop checks
                        }                           //which schedule group is already occupied
                    }
                }
            }
        }
        for(int i = 0; i < apSelect.length;i++){
            if(group[i] == null){
                for(int k = 0; k < apSelect[i].length; k++){
                    returnB.add(apSelect[i][k]);                //if the schedule group is not occupied it adds
                }                                               //it adds all the AP from that group to the list
            }
            for(int j = 0; j < group.length; j++){
                if (group[i]!= null && group[j]!= null && group[j].equals(group[i])&& i!=j)
                {
                    for(int k = 0; k < apSelect[i].length; k++){
                        returnB.add(apSelect[i][k]);
                        returnB.remove(group[j]);                   //it runs a loop if the schedule group needs to be
                    }                                               //occupied by a multiple bcs the other slot is already taken
                }
            }


        }
        for(int i = 0; i < mandatory.length; i++){
            if(!selected.get(0).equals(mandatory[i])){
                returnB.remove(mandatory[i]);              //removes all mandatory subjects from the final list
            }
        }
        for(int i = 0; i < doubles.length; i++){
            if(2 == Collections.frequency(returnB, "a")) {
                returnB.remove(doubles[i]);              //removes one of multiple from the final list
            }
        }
        if(year3 == false){
            returnB.remove("Leaf Core"); //removes leaf core for y4
        }
        return returnB.toArray(); //final list converted to array

    }

    public static void main(String[] args) {
        //set up of user window
        final JFrame frame = new JFrame();
        frame.setBounds(400, 400, 300, 475);
        JPanel panel = new JPanel();

        //set up of buttons
        JButton button1 = new JButton();
        button1.setPreferredSize(new Dimension(200,80)); //size
        button1.setText("exam days"); //text
        panel.add(button1); //adds it to the panel

        JButton button2 = new JButton();
        button2.setPreferredSize(new Dimension(200,80));
        button2.setText("GPA calculator");
        panel.add(button2);

        JButton button3 = new JButton();
        button3.setPreferredSize(new Dimension(200,80));
        button3.setText("Study hours");
        panel.add(button3);

        JButton button4 = new JButton();
        button4.setPreferredSize(new Dimension(200,80));
        button4.setText("Schedule Maker");
        panel.add(button4);

        JButton button5 = new JButton();
        button5.setPreferredSize(new Dimension(200,80));
        button5.setText("AP difficulty calculator");
        panel.add(button5);

        frame.add(panel);
        frame.setVisible(true);

        button1.addActionListener(new ActionListener() { //button 1 when pressed

            public void actionPerformed(ActionEvent arg0) { //2 arrays; 1. for AP select 2.index connected to ap array with day, date and time
                String[] apSelect = {"AP 2-D Art and Design", "AP Biology", "AP Comparative Government and Politics", "AP Statistics", "AP French", "AP Calculus", "AP Psychology", "AP Art History", "AP Seminar", "AP English", "AP Computer Science A", "AP European History", "AP Human Geography", "AP Economics", "AP German", "AP Physics", "AP Literature", "AP Computer Science Principles"};
                String[][] examInfo = {{"Friday", "May 5, 2023", "deadline for portfolio"}, {"Wednesday", "May 10, 2023", "12 p.m"}, {"Wednesday", "May 3, 2023", "12 p.m"}, {"Thursday", "May 4, 2023", "12 p.m"}, {"Thursday", "May 11, 2023", "8 a.m."}, {"Monday", "May 8, 2023", "8 a.m"}, {"Monday", "May 1, 2023", "8 a.m"}, {"Friday", "May 5, 2023", "12 p.m"}, {"Thursday", "May 4, 2023", "12 p.m"}, {"Tuesday", "May 9, 2023", "8 a.m"}, {"Wednesday", "May 3, 2023", "12 p.m"}, {"Friday", "May 5, 2023", "8 a.m"}, {"Thursday", "May 4, 2023", "8 a.m"}, {"Macroeconomics: Thursday, May 4, 2023, 8 a.m", "Micro: Friday, May 5, 2023, 12 p.m.", ""}, {"Friday", "May 12, 2023", "12 p.m."}, {"Tuesday", "May 9, 2023", "Mechanics: 12 p.m, Electricity and Magnetism: 2 p.m."}, {"Wednesday", "May 3, 2023", "8 a.m"}, {"Monday", "May 8, 2023", "8 a.m"}};
                String subject = (String) JOptionPane.showInputDialog(null, "select an AP subject for exam date",
                        "exam dates", JOptionPane.PLAIN_MESSAGE, null, apSelect, "exam dates");

                //loop for finding the information for selected AP
                for(int i = 0; i<apSelect.length; i++){
                    if(subject.equals(apSelect[i])){
                        JOptionPane.showMessageDialog(null, examInfo[i][0]+"\n"+examInfo[i][1]+"\n"+examInfo[i][2]);

            }
        };

        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                GPACalculator gpaCalculator = new GPACalculator(); //calls GPA calculator class

            }
        });


        button3.addActionListener(new ActionListener() { //study hours calculator button

            public void actionPerformed(ActionEvent arg0) {
                String[] yearsList = {"Year 1","Year 2","Year 3","Year 4"};
                String[] term = {"Term 1","Term 2","Term 3","Term 4"};
                String [][] stuHour= {{"yes","yes","yes","no"},{"yes","yes","no","no"},{"yes","no","no","no"},{"yes","no","no","no"}};
                String years1 = (String) JOptionPane.showInputDialog(null, "Which year are going to?",
                        "Study Hours", JOptionPane.PLAIN_MESSAGE, null, yearsList, "year");
                String term1 = (String) JOptionPane.showInputDialog(null, "Which term is it right now?",
                        "Study Hours", JOptionPane.PLAIN_MESSAGE, null, term, "term"); //user's input
                for(int i = 0;i < term.length; i++){
                    for(int j = 0;j < yearsList.length; j++){
                        if(yearsList[j].equals(years1) && term[i].equals(term1)) {
                            JOptionPane.showMessageDialog(null, stuHour[i][j]); //idk [term][yearList] = decision if the student has study hours or not
                        }
                    }
                }

            }
        });

        button4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                Object[] yearSelect = {"Year 3", "Year 4"}; //options for year select
                int dialogResult = 0; //dialog result is either 0 or 1 (True or false)
                ArrayList<String> selection = new ArrayList<>(); //array list that stores AP's that are already selected
                String year = (String) JOptionPane.showInputDialog(null, "Which year are going to?",
                        "schedule maker", JOptionPane.PLAIN_MESSAGE, null, yearSelect, "select year");
                String mandatory; //selection of mandatory subjects
                if (year.equals("Year 3")) { //year 3
                    Object[] subjectM3 = {"Writing and Rethorics", "AP Seminar"};
                    mandatory = (String) JOptionPane.showInputDialog(null, "Choose between one of mandatory subjects",
                            "schedule maker", JOptionPane.PLAIN_MESSAGE, null, subjectM3, "select subject");
                } else { //year 4
                    Object[] subjectM4 = {"AP Literature", "AP English"};
                    mandatory = (String) JOptionPane.showInputDialog(null, "Choose between one of mandatory subjects",
                            "schedule maker", JOptionPane.PLAIN_MESSAGE, null, subjectM4, "select subject");

                }
                selection.add(mandatory);
                for(int i = 0; i < 4; i++){ //loop for selecting AP's
                    if(!((year.equals("Year 3")&& i ==3)|| dialogResult == 1)){
                        String subject = (String) JOptionPane.showInputDialog(null, "Choose between one of mandatory subjects",
                                "schedule maker", JOptionPane.PLAIN_MESSAGE, null, ap(year.equals("Year 3"),selection), "select subject");
                        selection.add(subject);
                        if((year.equals("Year 3")&& i < 2)||(year.equals("Year 4")&& i < 4)){
                            dialogResult = JOptionPane.showConfirmDialog (null, "Would you like to add more?","Warning",JOptionPane.YES_NO_OPTION);}
                    }
                }
                JOptionPane.showMessageDialog(null, "Your courses are" + selection,
                        "DONE!", //final message
                        JOptionPane.INFORMATION_MESSAGE);

            }
        });

        button5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                JResortCalculator aFrame = new JResortCalculator(); //calling resortCalculator
                aFrame.setSize(300, 475); //set up of the frame
                aFrame.setVisible(true);

            }

        });
            }

});
}}
