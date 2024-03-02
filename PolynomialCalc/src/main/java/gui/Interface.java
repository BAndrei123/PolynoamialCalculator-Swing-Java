package gui;

import data.models.Polynomial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static bussines.logic.Operations.*;
import static gui.HashMapTransform.*;

public class Interface extends  JDialog{
    private JTextField FirstPolynomialText;
    private JTextField SecondPolynomialText;
    private JPanel GuiDesign;
    private JButton additionButton;
    private JButton subtractionButton;
    private JTextField ResultText;
    private JTextField RemainderText;
    private JButton derivationButton;
    private JButton integrationButton;
    private JButton divisionButton;
    private JButton multiplicationButton;

    public Interface(JFrame parent){


        super(parent);
        setTitle("Log in");
        setContentPane(GuiDesign);
        setMinimumSize(new Dimension(400,520));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        ResultText.setEditable(false);
        RemainderText.setEditable(false);

        final boolean[] messageDerivation = {true};
        final boolean[] messageIntegration = {true};

        additionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemainderText.setText(null);
                Polynomial firstPolynomial=new Polynomial();
                Polynomial secondPolynomial=new Polynomial();

               int patternFirstPolynomial=checkPolynomialPattern(FirstPolynomialText.getText(), Interface.this,"First polynomial");
               //check if the polynomials textfields are polynomials and then do the addition
               if(patternFirstPolynomial==1) {
                   firstPolynomial=stringToPolynomial(FirstPolynomialText.getText());
               }

               int patternSecondPolynomial=checkPolynomialPattern(SecondPolynomialText.getText(), Interface.this,"Second polynomial");
                if(patternSecondPolynomial==1) {
                    secondPolynomial=stringToPolynomial(SecondPolynomialText.getText());
                    Polynomial result=addition(firstPolynomial,secondPolynomial);
                    String show=polynomialtoString(result);
                    ResultText.setText(show);
                }

            }
        });


        subtractionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemainderText.setText(null);
                Polynomial firstPolynomial=new Polynomial();
                Polynomial secondPolynomial=new Polynomial();

                int patternFirstPolynomial=checkPolynomialPattern(FirstPolynomialText.getText(), Interface.this,"First polynomial");
                //check if the polynomials textfields are polynomials and then do the addition
                if(patternFirstPolynomial==1) {
                    firstPolynomial=stringToPolynomial(FirstPolynomialText.getText());
                }

                int patternSecondPolynomial=checkPolynomialPattern(SecondPolynomialText.getText(), Interface.this,"Second polynomial");
                if(patternSecondPolynomial==1) {
                    secondPolynomial=stringToPolynomial(SecondPolynomialText.getText());
                    Polynomial result=subtstraction(firstPolynomial,secondPolynomial);
                    String show=polynomialtoString(result);
                    ResultText.setText(show);
                }


            }
        });

        multiplicationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemainderText.setText(null);
                Polynomial firstPolynomial=new Polynomial();
                Polynomial secondPolynomial=new Polynomial();

                int patternFirstPolynomial=checkPolynomialPattern(FirstPolynomialText.getText(), Interface.this,"First polynomial");
                //check if the polynomials textfields are polynomials and then do the addition
                if(patternFirstPolynomial==1) {
                    firstPolynomial=stringToPolynomial(FirstPolynomialText.getText());
                }
                if(FirstPolynomialText.getText().equals("0") || FirstPolynomialText.getText().equals("0"))
                {
                    ResultText.setText("0");
                }
                else {
                    int patternSecondPolynomial = checkPolynomialPattern(SecondPolynomialText.getText(), Interface.this, "Second polynomial");
                    if (patternSecondPolynomial == 1) {
                        secondPolynomial = stringToPolynomial(SecondPolynomialText.getText());
                        Polynomial result = multiplication(firstPolynomial, secondPolynomial);
                        String show = polynomialtoString(result);
                        ResultText.setText(show);
                    }
                }
            }
        });

        divisionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Polynomial firstPolynomial=new Polynomial();
                Polynomial secondPolynomial=new Polynomial();

                int patternFirstPolynomial=checkPolynomialPattern(FirstPolynomialText.getText(), Interface.this,"First polynomial");
                //check if the polynomials textfields are polynomials and then do the addition
                if(patternFirstPolynomial==1) {
                    firstPolynomial=stringToPolynomial(FirstPolynomialText.getText());
                }
                if(SecondPolynomialText.getText().equals("0")){
                    JOptionPane.showMessageDialog(Interface.this,"Error, division by 0","Try again",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    int patternSecondPolynomial = checkPolynomialPattern(SecondPolynomialText.getText(), Interface.this, "Second polynomial");
                    if (patternSecondPolynomial == 1) {
                        secondPolynomial = stringToPolynomial(SecondPolynomialText.getText());
                        Polynomial[] result = divide(firstPolynomial, secondPolynomial);
                        String showResult = polynomialtoString(result[0]);
                        String showRemainder = polynomialtoString(result[1]);
                        ResultText.setText(showResult);
                        RemainderText.setText(showRemainder);
                    }
                }
            }
        });

        derivationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemainderText.setText(null);
                Polynomial firstPolynomial=new Polynomial();
                Polynomial secondPolynomial=new Polynomial();


                int patternFirstPolynomial=checkPolynomialPattern(FirstPolynomialText.getText(), Interface.this,"First polynomial");

                if(patternFirstPolynomial==1) {
                    firstPolynomial=stringToPolynomial(FirstPolynomialText.getText());

                    if(messageDerivation[0] ==true) {
                        JOptionPane.showMessageDialog(Interface.this, "The polynomial that will be derived will be always the first polynomial");
                        messageDerivation[0] =false;
                    }
                        Polynomial result = derivation(firstPolynomial);
                        String show = polynomialtoString(result);
                        ResultText.setText(show);


                }
            }
        });

        integrationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemainderText.setText(null);
                Polynomial firstPolynomial=new Polynomial();
                Polynomial secondPolynomial=new Polynomial();


                int patternFirstPolynomial=checkPolynomialPattern(FirstPolynomialText.getText(), Interface.this,"First polynomial");

                if(patternFirstPolynomial==1) {
                    firstPolynomial=stringToPolynomial(FirstPolynomialText.getText());

                    if(messageIntegration[0] ==true) {
                        JOptionPane.showMessageDialog(Interface.this, "The polynomial that will be derived will be always the first polynomial");
                        messageIntegration[0] =false;
                    }
                    Polynomial result = integration(firstPolynomial);
                    String show = polynomialtoString(result);
                    ResultText.setText(show);


                }
            }
        });
        setVisible(true);
    }


}
