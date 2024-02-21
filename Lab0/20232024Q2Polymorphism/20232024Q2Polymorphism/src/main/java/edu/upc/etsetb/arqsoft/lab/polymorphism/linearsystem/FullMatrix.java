/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem;

/**
 *
 * @author JuanCarlos
 */
public class FullMatrix implements Matrix{
    /**
     * An implementation of Matrix using a square bidimensional array of doubles
     */

    private double[][] values;
    
    
    /**
     * Constructor: creates a square matrix of dim x dim 
     * @param dim dimension of the matrix
     */    
    public FullMatrix(int dim){
        this.values = new double[dim][dim];
    }
    
    @Override
    public void setVal(int row, int col, double val) {
        this.values[row][col] = val;
    }

    @Override
    public double getVal(int row, int col) {
        return this.values[row][col];
    }
    
}
