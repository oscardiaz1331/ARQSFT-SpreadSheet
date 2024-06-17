/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Range implements Argument {
    List<Cell> cells;
    
    public Range(Cell origin, Cell destination, List<Cell> cells){
        this.cells = new LinkedList<>();
        Collections.sort(this.cells);
        //Searched in internet an optimized way
        int start = Collections.binarySearch(cells, origin);
        int end = Collections.binarySearch(cells, destination);
        this.cells.addAll(cells.subList(start, end + 1));
    }

    @Override
    public List<Double> getValue() throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException {
        List<Double> aux = new LinkedList<>();
        for(Cell cell : this.cells){
            aux.add(cell.getNumericValue());
        }
        return aux;
    }
    public List<Cell> getCells(){
        return this.cells;
    }
}
