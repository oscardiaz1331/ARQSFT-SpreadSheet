/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem;

/**
 *
 * @author JuanCarlos
 */
public abstract class LinearSystem {
    
    /**
     * Abstract class that is superclass of two subclasses: EqsLinearSystem and 
     * EigenValsLinearSystem.
     */
    
    /**
     * The matrix of the system: any linear system has a matrix
     */
    protected Matrix matrix;
    
    /**
     * Constructor: sets the attribute matrix to the argument matrix passed
     * @param matrix 
     */
    public LinearSystem(Matrix matrix){
        this.matrix = matrix;
    }

    /**
     * Get the value of matrix
     *
     * @return the value of matrix
     */
    public Matrix getMatrix() {
        return this.matrix;   
    }
    /**
     * Set the value of matrix
     *
     * @param matrix new value of matrix 
     * 
     */
    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }
    
}
