/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Cell extends Operand implements Argument{
    private Coordinate coordinate;
    private Content content;
    private HashSet<Cell> dependentCells;
    
    public Cell(Coordinate coord, Content content){
        this.coordinate = coord;
        this.content = content;
        this.dependentCells = new HashSet();
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
    public Content getContent(){
        return this.content;
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
    protected double getNumericValue() {
        return this.content.getNumericValue();
    }

    @Override
    public List<Double> getValue() {
        List<Double> aux = new LinkedList<>();
        aux.add(this.content.getNumericValue());
        return aux;
    }
}
