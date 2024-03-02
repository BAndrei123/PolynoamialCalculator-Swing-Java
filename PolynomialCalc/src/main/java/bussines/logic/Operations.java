package bussines.logic;

import data.models.Polynomial;

public class Operations {
    public static Polynomial addition(Polynomial one, Polynomial two) {
        Polynomial res = new Polynomial();

        //for each exponent from the first polynomials we add the coefficient from the another polynomial if the exponent exists else we asume it's 0
        one.getMonomials().forEach((exponent,coefficient)->{
            double finalcoefficient=coefficient+two.getMonomials().getOrDefault(exponent,0.00);
            if(finalcoefficient!=0)
            res.getMonomials().put(exponent,coefficient+two.getMonomials().getOrDefault(exponent,0.00));
        });

        //we need to put the rest of the exponents that exist in second polynomial but does not exist in the first one
        two.getMonomials().forEach((exponent,coefficient)->{
            if(!one.getMonomials().containsKey(exponent) && coefficient!=0){
                res.getMonomials().put(exponent,coefficient);
            }
        });
        return res; //return the result
    }

    //from the first polynomial we substract the another
    public static Polynomial subtstraction(Polynomial one, Polynomial two){
        Polynomial res=new Polynomial();

        //we use the same approach as in the addition, but we need to remove the exponents where the result coefficient from the subtraction is 0
        one.getMonomials().forEach((exponent,coeffiecient)->{
            res.getMonomials().put(exponent,coeffiecient-two.getMonomials().getOrDefault(exponent,0.00));
            if (coeffiecient-two.getMonomials().getOrDefault(exponent,0.00)==0){
                res.getMonomials().remove(exponent);
            }

        });
        //we need to put the rest of the exponents that exist in second polynomial but does not exist in the first one
        two.getMonomials().forEach((exponent,coefficient)->{
            if(!one.getMonomials().containsKey(exponent)){
                res.getMonomials().put(exponent,-1*coefficient);
            }
        });


        return  res; //return the result
    }

    public static Polynomial multiplication(Polynomial one, Polynomial two){
        Polynomial res=new Polynomial();

    //we multiply each element from  the first polynomial with each element from the second polynomial
    //we add the exponents  then we multiply the coeffiecent, in order to get the correct result we need to see if the exponent repeats and if it repeats we need to add the current sum of the coefficient
        one.getMonomials().forEach((exponentOne,coefficientOne)->{
            two.getMonomials().forEach((exponentTwo,coefficientTwo)->{
                int finalExponent=exponentOne+exponentTwo;
                double finalCoeffiecient=coefficientOne*coefficientTwo;
                res.getMonomials().put(finalExponent,res.getMonomials().getOrDefault(finalExponent,0.00)+finalCoeffiecient);
            });
        });

        return res;
    }
    public static Polynomial derivation(Polynomial one){
        Polynomial res=new Polynomial();

        //we go through the polynomial. We reduce 1 from each exponent, then multiply the coeff with exponent if the exponent it's not 0
        one.getMonomials().forEach((exponent,coefficient)->{
            if(exponent!=0){
                int finalExponent=exponent-1;
                double finalCoeffiecient=coefficient*exponent;
                res.getMonomials().put(finalExponent,finalCoeffiecient);
            }
        });

        return res;
    }

    public static Polynomial integration(Polynomial one){
        Polynomial res=new Polynomial();

//we go through the polynomial. We add 1 to each exponent.We divide the coefficient by the exponent if the exponent it's not 0
        one.getMonomials().forEach((exponent,coefficient)->{
            int finalExponent=exponent+1;
            double finalCoeffiecient=1;
                finalCoeffiecient=coefficient/finalExponent;
            res.getMonomials().put(finalExponent,finalCoeffiecient);
        });
        return res;
    }

    public static  Polynomial[] divide(Polynomial dividend, Polynomial divisior){
        Polynomial res=new Polynomial();
        Polynomial remainder=dividend;



        //we divide the polynomials while the degree of the remainder is bigger than the degree of the divisor
        while(remainder.getMonomials().size()>=divisior.getMonomials().size()){

            //we divide the biggest the term, subtract the powers and divide the coefficients
            int degree=remainder.getMonomials().lastKey()-divisior.getMonomials().lastKey();
            //the coefficient will be the coefficient of the remainder/ the coefficient of the divisor
            double coefficient=remainder.leadingCoefficient()/divisior.leadingCoefficient();

            //create a new polynomial in which we will store each monomial
            Polynomial term=new Polynomial();
            term.getMonomials().put(degree,coefficient);
            //add the current monomial from term
            res=addition(res,term);
            //store dividend from wich we will subtsract the remainder
            Polynomial dividend2=remainder;
            //get the remainder by multiplying the monomial term obtained from the division of the leading terms with the divisor
            remainder=multiplication(divisior,term);
            //subtract to get the new remainder
            remainder=subtstraction(dividend2,remainder);

            //do this until the leading term of the divisor is smaller than the leading term of the remainder
        }
        //return the result
        return new Polynomial[]{res,remainder};
    }

}


