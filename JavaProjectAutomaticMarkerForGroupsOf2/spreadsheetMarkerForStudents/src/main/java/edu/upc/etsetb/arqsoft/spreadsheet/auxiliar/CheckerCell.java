/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import java.util.List;

/**
 *
 * @author oscar
 */
public class CheckerCell {
    public static void checkExistence(Cell checkCell, List<Cell> cells) throws CircularDependencyException, TokenWrittenIncorrectlyException, WrongSyntaxException, ContentException{
        Coordinate coord = checkCell.getCoordinate();
        for(Cell cell : cells){
            if(cell.toString().equals(coord.toString())){
                if(cell.getContentAsString().isEmpty() || cell.getContentAsString().isBlank()){
                    cell.setContent(checkCell.getContent());
                }
            }
        }
    }
}
