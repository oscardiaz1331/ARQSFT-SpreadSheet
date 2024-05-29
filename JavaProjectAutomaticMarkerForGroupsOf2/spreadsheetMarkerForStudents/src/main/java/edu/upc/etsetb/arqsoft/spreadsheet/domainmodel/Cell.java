/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public class Cell implements Argument, Operand{
    private Coordinate coordinate;
    
    public Cell(Coordinate coord){
        this.coordinate = coord;
    }
}
