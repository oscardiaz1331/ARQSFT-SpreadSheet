/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import java.util.List;

/**
 *
 * @author oscar
 */
public class Range implements Argument {
    public Cell originCell, destinationCell;
    List<Cell> cells;
    
    public Range(Cell origin, Cell destination){
        this.originCell = origin;
        this.destinationCell = destination;
        this.cells.add(origin);
        //TODO
    }
}
