/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Function;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Range;
import java.util.List;

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
                }else if(component instanceof Function){
                    Function function = (Function) component;
                    functionCase(cell, function);
                }
            }
        }
    }
    
    private static void functionCase(Cell cell, Function function){
        for(Argument arg: function.getArguments()){
            if(arg instanceof Cell){
                Cell componentCell = (Cell) arg;
                cell.addDependentCell(componentCell);
            }
            else if(arg instanceof Function){
                Function argFunction = (Function) arg;
                functionCase(cell, argFunction);
            }
            else if(arg instanceof Range){
                Range range = (Range) arg;
                for(Cell rangeCell : range.getCells()){
                    cell.addDependentCell(rangeCell);
                }
            }
        }
    }
}
