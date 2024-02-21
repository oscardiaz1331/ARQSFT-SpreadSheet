/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.solvers;

import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.EqsLinearSystem;
import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.LinearSystem;
import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.Vector;

/**
 *
 * @author JuanCarlos
 */
public class GaussEqsLinearSystemSolver extends EqsLinearSystemSolver{
    /**
     * A class that implements the Gauss elimination method for solving a
     * linear equation system
     */
    
    /**
     * Constructor: invokes constructor of superclass
     */
    public GaussEqsLinearSystemSolver() {
       super(); 
    }

    /**
     * Checks that the system passed is an object EqsLinearSystem, and if so,
     * solves the system of linear equations using the Gauss elimination method; 
     * USE THE CODE IN THE FOLLOWING PAGE:
     * https://www.sanfoundry.com/java-program-gaussian-elimination-algorithm/
     *
     * If the argument system is not an object EqsLinearSystem then throw
     * exception IllegalArgumentException with a message indicating that the 
     * argument is not an object EqsLinearSystem
     * 
     * NOTE: YOUR CODE IN solve MUST USE ONLY methods in Matrix and Vector!!!
     * IT MUST BE PROGRAMMED AGAINST ABSTRACTIONS.
     * 
     * @param system the purported EqsLinearSystem
     */
    @Override
    public void solve(LinearSystem system) {
        if(!(system instanceof EqsLinearSystem)){
            throw new IllegalArgumentException("The argument is not an object EqsLinearSystem");
        }
        LinearSystem eqsLinear=system;
        EqsLinearSystem eqsLinearSystem = ((EqsLinearSystem)system);
        int N = 6;
        for(int k = 0; k < N; k++) 
        {
            /** find pivot row **/
            int max = k;
            for (int i = k + 1; i < N; i++) 
                if (Math.abs(eqsLinearSystem.getMatrix().getVal(i, k)) > Math.abs(eqsLinearSystem.getMatrix().getVal(max, k))) 
                    max = i;
 
            /** swap row in A matrix **/
            
            for(int i=0;i<N;i++){
            double temp = eqsLinearSystem.getMatrix().getVal(i, k); 
            eqsLinearSystem.getMatrix().setVal(i, k, eqsLinearSystem.getMatrix().getVal(i, max)); 
            eqsLinearSystem.getMatrix().setVal(i, max, temp);
            }
 
            /** swap corresponding values in constants matrix **/
            double t = eqsLinearSystem.getVector().getVal(k);
            eqsLinearSystem.getVector().setVal(k, eqsLinearSystem.getVector().getVal(k)); 
            eqsLinearSystem.getVector().setVal(max,t);
 
            /** pivot within A and B **/
            for (int i = k + 1; i < N; i++) 
            {
                double factor = eqsLinearSystem.getMatrix().getVal(i, k) / eqsLinearSystem.getMatrix().getVal(k, k);
                eqsLinearSystem.getVector().setVal(i, eqsLinearSystem.getVector().getVal(i)- factor *  eqsLinearSystem.getVector().getVal(k));
                for (int j = k; j < N; j++) 
                    eqsLinearSystem.getMatrix().setVal(i, j,  eqsLinearSystem.getMatrix().getVal(i, j) - factor * eqsLinearSystem.getMatrix().getVal(k, j));
            }
        }
 
        /** back substitution **/
        Vector solution = new Vector(N);
        for (int i = N - 1; i >= 0; i--) 
        {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) 
                sum += eqsLinearSystem.getMatrix().getVal(i, j) * solution.getVal(j);
            solution.setVal(i, (eqsLinearSystem.getVector().getVal(i) - sum) / eqsLinearSystem.getMatrix().getVal(i, i));
        }    
        this.solution = solution;    
        system=eqsLinear;
    }
    
}
