/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;

/**
 *
 * @author oscar
 */
public interface Observer {
    public void update() throws CircularDependencyException, WrongSyntaxException, WrongSyntaxException, TokenWrittenIncorrectlyException, ContentException;
}
