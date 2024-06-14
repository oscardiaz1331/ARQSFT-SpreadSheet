/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.Checker;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author oscar
 */
public class CircularDependencyChecker implements Checker{
    private HashSet<String> visited;
    private List<FormulaComponent> components;
    
    public CircularDependencyChecker(List<FormulaComponent> components){
        this.visited = new HashSet<>();
        this.components = components;
    }
    

    @Override
    public void check() throws CircularDependencyException{
        for(FormulaComponent comp :this.components){
            if(comp instanceof Cell){
                this.DFS((Cell)comp);
            }
        }
    }
    
    //Based in DFS
    private void DFS(Cell node) throws CircularDependencyException {
        visited.add(node.getStringCoordinate());
        if(node.hasDependentCells()){
            if(visited.contains(node.getStringCoordinate())){
                throw new CircularDependencyException("Circular dependency detected in cell " + node.getCoordinate().toString());
            }
            for(Cell child : node.getDependentCells()){
                this.DFS(child);
            }
        }
        visited.remove(node.getStringCoordinate());
    }
}
