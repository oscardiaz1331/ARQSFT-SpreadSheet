/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.Observable;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author oscar
 */
public class ObservableCell implements Observable{
    private List<Cell> cells;

    public ObservableCell(){
        this.cells = new ArrayList<>();
    }
    @Override
    public void registerObserver(Cell obs) {
        this.cells.add(obs);
    }

    @Override
    public void registerObservers(Set<Cell> obs, Cell cell) {
        for(Cell observableCell : obs){
            observableCell.getObserver().registerObserver(cell);
        }
    }
    
    @Override
    public void removeObserver(Cell obs) {
        this.cells.remove(obs);
    }

    @Override
    public void removeObservers() {
        this.cells.clear();
    }
    
    @Override
    public void notifyObserver() throws CircularDependencyException, WrongSyntaxException, WrongSyntaxException, TokenWrittenIncorrectlyException, ContentException{
        for(Cell cell : this.cells){
            cell.update();
        }
    }
    
    
}
