/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;

/**
 *
 * @author esthe
 */
public class EditCell extends Command{
    private Coordinate cellCoordinate;
    private Content newContent;

    public EditCell(Coordinate cellCoordinate, Content newContent) {
        this.cellCoordinate = cellCoordinate;
        this.newContent = newContent;
    }

    @Override
    public void execute(Spreadsheet spreadsheet, S2VLoader loader, S2VStore store) throws NoNumberException, ContentException, CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException {
        spreadsheet.editCell(cellCoordinate, newContent);
        spreadsheet.display();
    }
}
