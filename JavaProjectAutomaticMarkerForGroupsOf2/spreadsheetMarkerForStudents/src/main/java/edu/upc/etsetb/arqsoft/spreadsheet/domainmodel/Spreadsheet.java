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
public class Spreadsheet {
    private List<Cell> cells;
    
    public Spreadsheet(){
        this.cells = new LinkedList<>();
    }
    
    public Spreadsheet(List<Cell> cells){
        this.cells = cells;
    }
    
    public List<Cell> getCells(){
        //Collections.sort(cells);
        return this.cells;
    }
    
    public void editCell(Coordinate coordinate, Content content) throws NoNumberException, CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException, ContentException {
        boolean found = false;
        for (Cell cell : this.cells) {
            if (cell.getCoordinate().equals(coordinate)) {
                cell.setContent(content);
                found = true;
                break;
            }
        }
        if (!found) {
            this.cells.add(new Cell(coordinate, content, this.cells));
        }
    }
    
    public void display() {
        System.out.println("Spreadsheet content:");
        //Collections.sort(cells);
        for (Cell cell : cells) {
            System.out.println(cell.getCoordinate().toString() + ": " + cell.getContentAsString());
        }
    }
}
