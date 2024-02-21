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
public class GaussJordanEqsLinearSystemSolver extends EqsLinearSystemSolver{
    /**
     * A class that implements the Gauss-Jordan elimination method for solving a
     * linear equation system
     */

    
    /**
     * Constructor: invokes constructor of superclass
     */
    public GaussJordanEqsLinearSystemSolver() {
        super();
    }

    /**
     * Checks that the system passed is an object EqsLinearSystem, and if so, 
     * solves the system of linear equations using the Gauss-Jordan elimination method; 
     * USE THE CODE IN THE FOLLOWING PAGE:
     * https://www.geeksforgeeks.org/program-for-gauss-jordan-elimination-method/
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
        EqsLinearSystem eqsLinear=((EqsLinearSystem)system);
        EqsLinearSystem eqsLinearSystem=new EqsLinearSystem(eqsLinear.getMatrix(), eqsLinear.getVector());
        int i, j, k = 0, c, n=6;

        // Performing elementary operations
        for (i = 0; i < n; i++)
        {
            if (eqsLinearSystem.getMatrix().getVal(i, i) == 0) 
            {
                c = 1;
                while ((i + c) < n && eqsLinearSystem.getMatrix().getVal(i + c, i) == 0) 
                    c++;         
                if ((i + c) == n) 
                {
                    break;
                }
                for (j = i, k = 0; k <= n; k++) 
                {
                    double temp = eqsLinearSystem.getMatrix().getVal(j, k);
                    eqsLinearSystem.getMatrix().setVal(j,k, eqsLinearSystem.getMatrix().getVal(j+c,k));
                    eqsLinearSystem.getMatrix().setVal(j+c,k,temp);
                }
            }
        
            for (j = 0; j < n; j++) 
            {

                // Excluding all i == j
                if (i != j) 
                {

                    // Converting Matrix to reduced row
                    // echelon form(diagonal matrix)
                    double p = eqsLinearSystem.getMatrix().getVal(j,i) / eqsLinearSystem.getMatrix().getVal(i,i);
                
                    for (k = 0; k <= n; k++)                 
                    eqsLinearSystem.getMatrix().setVal(j,k,eqsLinearSystem.getMatrix().getVal(j,k) - eqsLinearSystem.getMatrix().getVal(i,k) * p);             
                }
            }
        }
        int dim=6;
        Vector sol =  new Vector(dim);
        for(i=0;i<dim;i++){
            sol.setVal(i, eqsLinearSystem.getMatrix().getVal(i, dim)/eqsLinearSystem.getMatrix().getVal(i, i));
        }
        this.solution=sol;
    }
    
}
