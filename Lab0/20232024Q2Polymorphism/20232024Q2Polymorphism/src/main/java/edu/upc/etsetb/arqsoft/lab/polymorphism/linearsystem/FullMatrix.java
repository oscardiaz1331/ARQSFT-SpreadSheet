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
        throw new UnsupportedOperationException("FullMatrix::FullMatrix. Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void setVal(int row, int col, double val) {
        throw new UnsupportedOperationException("FullMatrix::setVal. Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public double getVal(int row, int col) {
        throw new UnsupportedOperationException("FullMatrix::getVal. Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
