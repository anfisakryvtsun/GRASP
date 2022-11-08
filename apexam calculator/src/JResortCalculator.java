import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class JResortCalculator extends JFrame implements ItemListener{
        //variables for levels of difficulty
        final int BASE_PRICE = 0;
        final int EASY = 1;
        final int MEDIUM = 2;
        final int HARD = 3;
        int totalPrice = BASE_PRICE;

        //check boxes
        JCheckBox ecoBox = new JCheckBox("Economics"+"("+EASY+")", false);
        JCheckBox geoBox = new JCheckBox("Human Geo"+"("+EASY+")", false);
        JCheckBox compBox = new JCheckBox("Computer Science A"+"("+MEDIUM+")", false);
        JCheckBox psychBox = new JCheckBox("Psychology"+"("+MEDIUM+")", false);
        JCheckBox artBox = new JCheckBox("Art Design" +"("+MEDIUM+")", false);
        JCheckBox statBox = new JCheckBox("Statistics" +"("+MEDIUM+")", false);
        JCheckBox hisBox = new JCheckBox("Ap Euro His" +"("+HARD+")", false);
        JCheckBox precalcBox = new JCheckBox("Pre-calculus" +"("+HARD+")", false);
        JCheckBox ahisBox = new JCheckBox("Pre-calculus" +"("+HARD+")", false);
        JCheckBox bioBox = new JCheckBox("Calculus" +"("+HARD+")", false);
        JCheckBox calcBox = new JCheckBox("Biology" +"("+HARD+")", false);
        //text
        JLabel resortLabel = new JLabel("AP DIFFICULTY CALCULATOR");
        JLabel optionExplainLabel = new JLabel("");
        JLabel optionExplainLabel2 = new JLabel("Check off APs you want to take.");
                JLabel priceLabel = new JLabel("The estimated difficulty of your AP classes is:");

        JTextField totPriceField = new JTextField(10);//result field

        // custom constructor for this class
        public JResortCalculator()
        {
            //prints the checkboxes
            super("Resort Price Calculator");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new FlowLayout());
            add(resortLabel);
            add(optionExplainLabel);
            add(optionExplainLabel2);
            add(geoBox);
            add(ecoBox);
            add(compBox);
            add(statBox);
            add(psychBox);
            add(artBox);
            add(hisBox);
            add(precalcBox);
            add(calcBox);
            add(bioBox);
            add(ahisBox);
            add(priceLabel);
            add(totPriceField);


            // when app loads, set the totalPrice into the field
            totPriceField.setText("" + totalPrice);

            // add event listeners to the check boxes
            geoBox.addItemListener(this);
            bioBox.addItemListener(this);
            calcBox.addItemListener(this);
            statBox.addItemListener(this);
            precalcBox.addItemListener(this);
            ahisBox.addItemListener(this);
            geoBox.addItemListener(this);
            compBox.addItemListener(this);
            psychBox.addItemListener(this);
            artBox.addItemListener(this);
            hisBox.addItemListener(this);
        }

        // Event handling method
        @Override
        public void itemStateChanged(ItemEvent event)
        {
            Object source = event.getSource();
            int select = event.getStateChange();

            // if statement that decides which checkbox was the source of the ItemEvent
            if((source == geoBox)||(source == ecoBox))
            {
                if(select == ItemEvent.SELECTED)
                {
                    totalPrice += EASY;
                }
                else
                {
                    totalPrice -= EASY;
                }
            }
            else if((source == psychBox)||(source == artBox)||(source == compBox)||(source == statBox))
            {
                if(select == ItemEvent.SELECTED)
                {
                    totalPrice += MEDIUM;
                }
                else
                {
                    totalPrice -= MEDIUM;
                }
            }
            else if ((source == hisBox)||(source == bioBox)||(source == ahisBox)||(source == precalcBox)||(source == calcBox))
            {
                if(select == ItemEvent.SELECTED)
                {
                    totalPrice += HARD;
                }
                else
                {
                    totalPrice -= HARD;
                }
            }

            if (totalPrice<5)
            {
                totPriceField.setText("too easy");
            }
            else if ((totalPrice>6)||(totalPrice<=8))
            {
                totPriceField.setText("just right");
            }
            else if (totalPrice>9)
            {           totPriceField.setText("just hard"); //printing the total time/life cost of AP's
            }
        }
}
