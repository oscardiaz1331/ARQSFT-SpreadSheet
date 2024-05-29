/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public class Coordinate {
    private int row;
    private int column;
    
    public Coordinate(int row, int column){
        this.row = row;
        this.column = column;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public int getColumn(){
        return this.column;
    }
    
    public void setColumn(int column){
        this.column = column;
    }
    
}
