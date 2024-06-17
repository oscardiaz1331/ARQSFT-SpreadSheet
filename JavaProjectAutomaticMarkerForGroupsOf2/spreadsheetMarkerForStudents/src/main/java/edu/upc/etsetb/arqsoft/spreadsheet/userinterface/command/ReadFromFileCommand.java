/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.SavingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IReadFromFile;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esthe
 */
public class ReadFromFileCommand extends Command implements IReadFromFile{
    private Spreadsheet spread;
    
    public ReadFromFileCommand(Spreadsheet spread){
        this.spread=spread;
    }

    @Override
    public void readCommand(String filename) {
        try {
            Scanner scanner = new Scanner(new FileInputStream(filename));
            CommandExecutor rf = new CommandExecutor(spread);
            while (scanner.hasNextLine()) {
                String content = scanner.nextLine();
                rf.execute(content);
            }
            scanner.close();
        } catch (TokenWrittenIncorrectlyException | ContentException | CircularDependencyException | FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } 
    }
}
