/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem;

import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.solvers.LinearSystemSolution;

/**
 *
 * @author JuanCarlos
 */
public class Vector implements LinearSystemSolution{
    /**
     * Class that implements a Vector using an array unidimensional
     */
    
    double[] vals;
    
    public Vector(int dim){
        vals = new double[dim];
    }
    
    /**
     * Sets the position in [index] to value val
     * @param index the index of the position 
     * @param val the value to be set in the position identified by row and col
     */
    public void setVal(int index, double val){
        this.vals[index] = val;
    }
    
    /**
     * Returns the value in the position [index]
     * @param index the index of the position 
     * @return the value in the position identified by row and col
     */
    public double getVal(int index){
        return this.vals[index];
    }
    
}
