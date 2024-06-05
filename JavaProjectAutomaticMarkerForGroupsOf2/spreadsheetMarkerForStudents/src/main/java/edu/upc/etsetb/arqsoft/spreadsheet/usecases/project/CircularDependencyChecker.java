/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import java.util.HashSet;

/**
 *
 * @author oscar
 */
public class CircularDependencyChecker {
    HashSet<String> visited;
    
    public CircularDependencyChecker(){
        this.visited = new HashSet<>();
    }
    
    //Based in DFS
    public void checkCircularDependency(Cell node) throws CircularDependencyException {
        visited.add(node.getStringCoordinate());
        if(node.hasDependentCells()){
            if(visited.contains(node.getStringCoordinate())){
                throw new CircularDependencyException("Circular dependency detected in cell " + node.getCoordinate().toString());
            }
            for(Cell child : node.getDependentCells()){
                this.checkCircularDependency(child);
            }
        }
        visited.remove(node.getStringCoordinate());
    }
}
