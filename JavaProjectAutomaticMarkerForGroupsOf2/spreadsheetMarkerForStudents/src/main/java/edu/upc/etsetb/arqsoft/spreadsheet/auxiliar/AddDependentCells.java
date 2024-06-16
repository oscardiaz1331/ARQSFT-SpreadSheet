/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;

/**
 *
 * @author oscar
 */
public class AddDependentCells {
    public static void myDependentCells(Cell cell){
        Content content = cell.getContent();
        if(content instanceof Formula){
            Formula formula = (Formula) content;
            for(FormulaComponent component : formula.getFormulaComponents()){
                if(component instanceof Cell){
                    Cell componentCell = (Cell) component;
                    cell.addDependentCell(componentCell);
                }
            }
        }
    }
}
