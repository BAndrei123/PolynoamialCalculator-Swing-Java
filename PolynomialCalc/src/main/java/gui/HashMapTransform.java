package gui;

import data.models.Polynomial;

import javax.swing.*;

public class HashMapTransform {
    public  static int checkPolynomialPattern(String toCheck, Interface parent, String toShow){
        toCheck=toCheck.replaceAll("\\s","");


        String regexFirstCase="^\\s*([-+]?(\\d*\\.?\\d*)*\\s*x\\s*\\^\\s*\\d+\\s*)+(\\d*\\.?\\d*)*\\s*x?\\s*\\+?\\s*(\\d*\\.?\\d*)*\\s*$";  //if we have more than 2 terms or 1 term in the polynomial for example x^3+2x+1
        String regexSecondCase="^\\s*([-+]?(\\d*\\.?\\d*)*\\s*x\\s*\\^\\s*\\d+\\s*)|([-+]?\\d*x\\s*)|([-+]?\\d*x(\\^\\d+)?\\s*)+([-+]?\\s*\\d+)|(\\d*\\.?\\d*)*\\s*x?\\s*\\+?\\s*(\\d*\\.?\\d*)*\\s*$"; // second case if we have for example -3x+1, 2x, x
        String regexFloatCase= "^([-+]?\\d*\\.?\\d*?x\\^\\d+\\s*)+([-+]?\\d*\\.?\\d*?x(\\^\\d+)?\\s*)+([-+]?\\s*\\d*\\.?\\d+)?$";

        if(toCheck.isEmpty()) {
            JOptionPane.showMessageDialog(parent,"Insert " + toShow,"Try again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        if (!toCheck.matches(regexFirstCase) && !toCheck.matches(regexSecondCase) && !toCheck.matches(regexFloatCase) ){
            JOptionPane.showMessageDialog(parent,toShow + " isn't correct. Insert a polynomial","Try again",JOptionPane.ERROR_MESSAGE);
            return 0;
        }

        return 1;
    }


    //function to transform the String
    public static Polynomial stringToPolynomial(String toConvert) {

        Polynomial result = new Polynomial();
        //replace all the spaces if we have spaces between characters
        toConvert=toConvert.replaceAll("\\s","");
        //replace - with +- to split by +
        toConvert=toConvert.replaceAll("-","+-");
       
        
        String[] terms=toConvert.split("\\+");
        
        for(String term: terms) {
            //if we have the index ^ means we are on the case we have ax^b
            if (!term.isEmpty()) {
                if (term.indexOf("^") >= 1) {
                    double coefficient;
                    //split the term by ^
                    String[] parts = term.split("x\\^");
                    //check if we don't have the coefficient, if it's true we put 1 else we put the coefficient
                    if (parts[0].isEmpty())
                        coefficient = 1d;
                    else {
                        if(parts[0].equals("-"))
                            coefficient=-1d;
                        else
                        coefficient = Double.parseDouble(parts[0]);
                    }
                    //add the power and the coefficient to the polynomial
                    result.getMonomials().put(Integer.parseInt(parts[1].trim()), coefficient);
                }
                //case if we have only x
                if (term.indexOf("^") == -1 && term.indexOf("x") != -1) {
                    double coefficient = 1;
                    //if the length of the term is bigger than one it means we have coefficient else we put 1
                    if (term.length() > 1) {
                        String[] parts = term.split("x");
                        if (parts[0].isEmpty())
                            coefficient = 1d;
                        else {
                            if(parts[0].equals("-"))
                                coefficient=-1d;
                            else
                                coefficient = Double.parseDouble(parts[0]);
                        }
                    }
                    result.getMonomials().put(1, coefficient);
                }
                //case of the empty term
                if (term.indexOf("x") == -1)
                    result.getMonomials().put(0, Double.parseDouble(term));
            }
        }
        return result;
        }

       public static String polynomialtoString(Polynomial polynomial){
        StringBuilder toBuild=new StringBuilder();
        String result;
        boolean isFirstTerm=true;
        if(polynomial.getMonomials().isEmpty())
        {
            toBuild.append("0");
        }
        else {
            for (int power : polynomial.getMonomials().descendingKeySet()) {
                double coefficient = polynomial.getMonomials().get(power);
                if (coefficient != 0) {
                    //if it's not the first term
                    if (!isFirstTerm) {
                        if (coefficient > 0) {
                            toBuild.append("+");
                        } else {
                            toBuild.append("-");
                            coefficient = (-1)*coefficient;
                        }
                    }
                    //if it's the first term
                    
                    else {
                        if (coefficient < 0) {
                            toBuild.append("-");
                            coefficient = (-1)*coefficient;
                        }
                        isFirstTerm = false;
                    }
                    if (coefficient != 1 || power == 0) {
                        toBuild.append(coefficient);
                    }
                    if (power > 0) {
                        toBuild.append("x");
                        if (power > 1) {
                            toBuild.append("^").append(power);
                        }
                    }
                }
            }
        }
        result=toBuild.toString();
        return  result;
       }

}
