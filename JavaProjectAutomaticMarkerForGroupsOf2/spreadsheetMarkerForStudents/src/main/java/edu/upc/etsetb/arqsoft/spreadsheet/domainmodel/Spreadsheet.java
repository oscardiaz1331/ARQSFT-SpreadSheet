/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Spreadsheet {
    private List<Cell> cells;
    
    public Spreadsheet(){
        this.cells = new LinkedList<Cell>();
    }
    
}