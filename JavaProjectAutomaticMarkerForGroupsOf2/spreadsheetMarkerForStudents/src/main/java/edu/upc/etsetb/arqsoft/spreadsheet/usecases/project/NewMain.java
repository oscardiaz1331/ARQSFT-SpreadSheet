/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.NumericalContent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.TextContent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Value;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.NonExistentCell;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxExpression;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class NewMain {

    /**
     * @param args the command line arguments
     * @throws edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException
     */
    public static void main(String[] args) throws TokenWrittenIncorrectlyException, WrongSyntaxExpression, NonExistentCell, CircularDependencyException {
        // TODO code application logic here
        LinkedList<Cell> cells = new LinkedList<>();
        cells.add(new Cell(new Coordinate(42,"A"),new NumericalContent(new Number(1))));
        cells.add(new Cell(new Coordinate(6,"B"),new NumericalContent(new Number(1))));
        FormulaComputator comp = new FormulaComputator(cells); 
        Number result = (Number) comp.compute("=(2*((1.9+A42*6))*B6*6+SUM(A42:B6;A42;3;SUM(A42:B6;B6;3;B6)))");
        System.out.println(result.getNumericValue());
    }
    
}
