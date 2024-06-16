/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import java.util.LinkedList;
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
        this.cells = new LinkedList<>();
        this.cells.add(origin);
        //TODO
    }

    @Override
    public List<Double> getValue() throws NoNumberException {
        List<Double> aux = new LinkedList<>();
        for(Cell cell : this.cells){
            aux.add(cell.getNumericValue());
        }
        return aux;
    }
    
    
}
