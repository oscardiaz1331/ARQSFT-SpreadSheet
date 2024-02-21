/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem;

/**
 *
 * @author JuanCarlos
 */
public interface Matrix {
    
  
    /**
     * Sets the position in [row][col] to value val
     * @param row the row of the position 
     * @param col the column of the position
     * @param val the value to be set in the position identified by row and col
     */
    public abstract void setVal(int row, int col, double val);
    
    /**
     * Returns the value in the position identified by row and col
     * @param row the row of the position 
     * @param col the column of the position
     * @return the value in the position identified by row and col
     */
    public abstract double getVal(int row, int col);
}
