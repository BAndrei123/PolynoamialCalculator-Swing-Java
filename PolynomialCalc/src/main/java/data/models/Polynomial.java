package data.models;

import java.util.TreeMap;

public class Polynomial {
    TreeMap<Integer,Double> monomials =new TreeMap<Integer,Double>();

    public TreeMap<Integer, Double> getMonomials() {
            return monomials;
    }
    public void print(){
        System.out.println(monomials);

    }


    public Double leadingCoefficient() {
        return monomials.get(monomials.lastKey());
    }
}
