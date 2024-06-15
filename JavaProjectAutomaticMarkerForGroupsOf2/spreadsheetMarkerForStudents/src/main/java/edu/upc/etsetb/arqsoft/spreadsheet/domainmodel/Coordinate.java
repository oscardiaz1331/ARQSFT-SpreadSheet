/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public class Coordinate implements Comparable<Coordinate>{
    private int row;
    private String column;
    
    public Coordinate(int row, String column){
        this.row = row;
        this.column = column;
    }
    
    public int getRow(){
        return this.row;
    }
    
    public void setRow(int row){
        this.row = row;
    }
    
    public String getColumn(){
        return this.column;
    }
    
    public void setColumn(String column){
        this.column = column;
    }
    
    @Override
    public String toString(){
        return this.column + String.valueOf(this.row);
    }
    
    public boolean equals(Coordinate coord){
        return this.column.equals(coord.column) && this.row == coord.row;
    }

    //Searched in internet the best comparation optimized
    @Override
    public int compareTo(Coordinate other) {
        int rowComparison = Integer.compare(this.row, other.row);
        if (rowComparison != 0) {
            return rowComparison;
        }
        return this.column.compareTo(other.column);
    }
}
