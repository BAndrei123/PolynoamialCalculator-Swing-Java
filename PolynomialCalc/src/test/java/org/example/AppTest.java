package org.example;

import data.models.Polynomial;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import static bussines.logic.Operations.*;
import static gui.HashMapTransform.polynomialtoString;

/**
 * Unit test for simple App.
 */
public class  AppTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        //addition
        Polynomial addition1 =new Polynomial();     //-x^3+17/9x^2+2
        Polynomial addition2 =new Polynomial();     //4x^2+1/6
        addition1.getMonomials().put(3,-1d);
        addition1.getMonomials().put(2, (double) (17/9));
        addition1.getMonomials().put(0,2d);

        addition2.getMonomials().put(2,4d);
        addition2.getMonomials().put(0,(double)(1/6));
        Polynomial resultTrue=addition(addition1,addition2);        //-x^3+53/9x^2+13/6


        Polynomial resultExpected=new Polynomial();
        resultExpected.getMonomials().put(3,-1d);
        resultExpected.getMonomials().put(2,(double)(53/9));
        resultExpected.getMonomials().put(0,(double)(13/6));

        String resultT=polynomialtoString(resultTrue);
        String resultE=polynomialtoString(resultExpected);
        assertEquals(resultE,resultT);

        //subtraction
        Polynomial subtraction1=new Polynomial();       //x^2+3x-33
        Polynomial subtraction2=new Polynomial();       //x^2+5x+1
        subtraction1.getMonomials().put(2,1d);
        subtraction1.getMonomials().put(1,3d);
        subtraction1.getMonomials().put(0,-33d);

        subtraction2.getMonomials().put(2,1d);
        subtraction2.getMonomials().put(1,5d);
        subtraction2.getMonomials().put(0,1d);

        Polynomial resultTtrueS=subtstraction(subtraction1,subtraction2); //-2x-32

        String resutltTS=polynomialtoString(resultTtrueS);

        Polynomial resultExpectedS=new Polynomial();
        resultExpectedS.getMonomials().put(1,-2d);
        resultExpectedS.getMonomials().put(0,-34d);
        String resultES=polynomialtoString(resultExpectedS);

        assertEquals(resultES,resutltTS);

        //multiplication
        Polynomial multiplication1=new Polynomial();
        Polynomial multiplication2=new Polynomial();

        multiplication1.getMonomials().put(1,1d);
        multiplication1.getMonomials().put(0,2d);

        multiplication2.getMonomials().put(1,1d);
        multiplication2.getMonomials().put(0,2d);

        Polynomial resultTrueM=multiplication(multiplication1,multiplication2);
        String resultTM=polynomialtoString(resultTrueM);

        Polynomial resultExpcetedM=new Polynomial();
        resultExpcetedM.getMonomials().put(2,1d);
        resultExpcetedM.getMonomials().put(1,4d);
        resultExpcetedM.getMonomials().put(0,4d);

        String resultEM=polynomialtoString(resultExpcetedM);
        assertEquals(resultEM,resultTM);

        //division
        Polynomial division1=new Polynomial();
        Polynomial division2=new Polynomial();

        division1.getMonomials().put(2,1d);
        division1.getMonomials().put(1,5d);
        division1.getMonomials().put(0,6d);

        division2.getMonomials().put(1,1d);
        division2.getMonomials().put(0,2d);

        Polynomial[] resultTrueD=divide(division1,division2);
        Polynomial resultD=resultTrueD[0];
        String resultTD=polynomialtoString(resultD);

        Polynomial resultExpectedD=new Polynomial();
        resultExpectedD.getMonomials().put(1,1d);
        resultExpectedD.getMonomials().put(0,3d);

        String resultED=polynomialtoString(resultExpectedD);

        assertEquals(resultED,resultTD);

        Polynomial derivation1=new Polynomial(); //x^2+5x+6
        derivation1.getMonomials().put(2,1d);
        derivation1.getMonomials().put(1,5d);
        derivation1.getMonomials().put(0,6d);

        Polynomial resultTrueDe=derivation(derivation1);
        String resultTDe=polynomialtoString(resultTrueDe);

        Polynomial resultExpectedDe=new Polynomial();
        resultExpectedDe.getMonomials().put(1,2d);
        resultExpectedDe.getMonomials().put(0,5d);
        String resultEDe=polynomialtoString(resultExpectedDe);

        assertEquals(resultEDe,resultTDe);

        Polynomial integration1=new Polynomial(); //x^2+5x+6
        integration1.getMonomials().put(2,1d);
        integration1.getMonomials().put(1,5d);
        integration1.getMonomials().put(0,6d);
//
        Polynomial resultTrueI=integration(integration1);
        String resultTI=polynomialtoString(resultTrueI);

        Polynomial resultExpectedI=new Polynomial();
        resultExpectedI.getMonomials().put(3,1/3d);
        resultExpectedI.getMonomials().put(2,5/2d);
        resultExpectedI.getMonomials().put(1,6d);
        String resultEI=polynomialtoString(resultExpectedI);

        assertEquals(resultEI,resultTI);
    }
}
