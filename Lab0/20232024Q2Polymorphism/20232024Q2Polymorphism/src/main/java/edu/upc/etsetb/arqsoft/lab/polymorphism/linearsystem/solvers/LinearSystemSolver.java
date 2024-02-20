/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.solvers;

import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.LinearSystem;

/**
 *
 * @author JuanCarlos
 */
public abstract class LinearSystemSolver {

    protected LinearSystemSolution solution;

    /**
     * Constructor: does nothing
     */
    public LinearSystemSolver() {
        throw new UnsupportedOperationException("LinearSystemSolver::LinearSystemSolver. Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Solves the linear system passed in argument system and assigns its 
     * solution to attribute solution
     * @param system the linear system
     */
    public abstract void solve(LinearSystem system);
    
    /**
     * Getter of solution
     * @return attribute solution
     */
    public LinearSystemSolution getSolution(){
        throw new UnsupportedOperationException("LinearSystemSolution::getSolution. Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
