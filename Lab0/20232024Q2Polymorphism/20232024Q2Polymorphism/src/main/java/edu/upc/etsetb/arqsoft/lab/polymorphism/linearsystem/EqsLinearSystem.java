/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem;

/**
 *
 * @author JuanCarlos
 */
public class EqsLinearSystem extends LinearSystem{
    
    /**
     * Implements a system of linear equations
     */
    
    /**
     * The vector of the system of linear equations
     */
    private Vector vector;
    
    /**
     * Constructor: sets the attribute matrix to argument matrix, and the 
     * attribute vector to argument vector
     * @param matrix the matrix of the system
     * @param vector the vector of the system
     */
    public EqsLinearSystem(Matrix matrix,Vector vector) {
        super(matrix);
        throw new UnsupportedOperationException("EqsLinearSystem::EqsLinearSystem. Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    /**
     * Getter for attribute vector: returns the attribute vector
     * @return the attribute vector
     */
    public Vector getVector(){
        throw new UnsupportedOperationException("EqsLinearSystem::getVector. Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
