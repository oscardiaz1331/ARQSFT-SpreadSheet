/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ICommand;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ICreateSpreadSheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IEditCell;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ILoadSpreadSheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IReadFromFile;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ISaveSpreadSheet;

/**
 *
 * @author esthe
 */
public class CommandExecutor {
    private Spreadsheet spreadsheet;

    public CommandExecutor(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public void execute(ICommand command) {
        try {
            if (command instanceof ISaveSpreadSheet) {
                ISaveSpreadSheet saveCommand = (ISaveSpreadSheet) command;
                saveCommand.save(null, spreadsheet);
            } else if (command instanceof IReadFromFile) {
                IReadFromFile readCommand = (IReadFromFile) command;
                readCommand.readCommand(null);
            } else if (command instanceof ICreateSpreadSheet) {
                ICreateSpreadSheet createCommand = (ICreateSpreadSheet) command;
                createCommand.create();
            } else if (command instanceof IEditCell) {
                IEditCell editCommand = (IEditCell) command;
                editCommand.edit(null, null);
            } else if (command instanceof ILoadSpreadSheet) {
                ILoadSpreadSheet loadCommand = (ILoadSpreadSheet) command;
                loadCommand.Load();
            } else {
                System.out.println("Command not recognized or supported.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while executing the command: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
