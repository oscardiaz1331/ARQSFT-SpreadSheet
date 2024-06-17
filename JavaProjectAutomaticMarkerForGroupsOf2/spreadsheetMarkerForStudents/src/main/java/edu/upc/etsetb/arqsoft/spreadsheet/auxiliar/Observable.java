/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import java.util.List;
import java.util.Set;


/**
 *
 * @author oscar
 */
public interface Observable {    
    public void registerObserver(Cell cell);
    public void registerObservers(Set<Cell> cells, Cell cell);
    public void removeObserver(Cell cell);
    public void removeObservers();
    public void notifyObserver()throws CircularDependencyException, WrongSyntaxException, WrongSyntaxException, TokenWrittenIncorrectlyException, ContentException;
}
