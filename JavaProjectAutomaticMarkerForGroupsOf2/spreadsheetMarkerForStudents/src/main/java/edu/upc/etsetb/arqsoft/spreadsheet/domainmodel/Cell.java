/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.AddDependentCells;
import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.CheckerCell;
import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.Recomputator;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Cell extends Operand implements Argument, Comparable<Cell>{
    private Coordinate coordinate;
    private Content content;
    private HashSet<Cell> dependentCells;
    
    public Cell(Coordinate coord, Content content, List<Cell> cells) throws CircularDependencyException, WrongSyntaxException, WrongSyntaxException, TokenWrittenIncorrectlyException, ContentException{
        //CheckerCell.checkExistence(this, cells);
        this.coordinate = coord;
        this.content = content;
        this.content.accept(new Recomputator());
        this.dependentCells = new HashSet();
        AddDependentCells.myDependentCells(this);
    }
    
    public Coordinate getCoordinate(){
        return this.coordinate;
    }
    public void setCoordinate(Coordinate coord){
        this.coordinate = coord;
    }
    public String getStringCoordinate(){
        return this.coordinate.getColumn() + String.valueOf(this.coordinate.getRow());
    }
    public int getRow(){
        return this.coordinate.getRow();
    }
    public String getCol(){
        return this.coordinate.getColumn();
    }
    public void setContent(Content content) throws NoNumberException, CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException, ContentException{
        this.content = content;
        this.dependentCells.clear();
        AddDependentCells.myDependentCells(this);
        this.content.accept(new Recomputator());

    }
    public Content getContent(){
        return this.content;
    }
    
    public String getContentAsString(){
        return this.content.getContent();
    }
    public void getCoordinate(Content content){
        this.content = content;
    }
    
    public void addDependentCell(Cell cell){
        this.dependentCells.add(cell);
    }
    
    public boolean hasDependentCells(){
        return !this.dependentCells.isEmpty();
    }
    
    public HashSet<Cell> getDependentCells(){
        return this.dependentCells;
    }
    
    
    @Override
    public double getNumericValue() throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException{
        return this.content.getNumericValue();
    }
    @Override
    public String getTextValue() throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException{
        return this.content.getTextValue();
    }
    

    @Override
    public List<Double> getValue() throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException {
        List<Double> aux = new LinkedList<>();
        aux.add(this.content.getNumericValue());
        return aux;
    }

    @Override
    public int compareTo(Cell other) {
        return this.coordinate.compareTo(other.getCoordinate());
    }
}
