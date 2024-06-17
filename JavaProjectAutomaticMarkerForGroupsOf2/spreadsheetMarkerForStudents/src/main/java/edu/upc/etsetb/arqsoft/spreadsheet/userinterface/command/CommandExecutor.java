/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token;
import static edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token.TokenType.COMMAND_CREATE;
import static edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token.TokenType.COMMAND_EDIT_CELL;
import static edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token.TokenType.COMMAND_LOAD;
import static edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token.TokenType.COMMAND_READ_COMMAND;
import static edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token.TokenType.COMMAND_SAVE;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Tokenizer;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ICommand;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ICreateSpreadSheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IEditCell;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ILoadSpreadSheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IReadFromFile;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ISaveSpreadSheet;
import java.util.List;

/**
 *
 * @author esthe
 */
public class CommandExecutor {
    private Spreadsheet spreadsheet;
    private List<Token> tokens;

    public CommandExecutor(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }

    public void execute(String content) throws TokenWrittenIncorrectlyException, ContentException, CircularDependencyException {
        
        Tokenizer tokenizer = new Tokenizer(Tokenizer.TokenizerType.COMMAND);
        this.tokens = tokenizer.tokenize(content);
        CommandChecker checker = new CommandChecker(tokens);
        Token first = tokens.getFirst();
        switch(first.token){
            case COMMAND_READ_COMMAND:
                this.readCommand();
                break;
            case COMMAND_CREATE:
                this.commandCreate();
                break;
            case COMMAND_EDIT_CELL:
                this.commandEdit();
                break;
            case COMMAND_LOAD:
                this.commandLoad();
                break;
            case COMMAND_SAVE:
                this.commandSave();
                break;
        }
        this.spreadsheet.display();
    }

    private void readCommand(){
        ReadFromFileCommand rf = new ReadFromFileCommand(this.spreadsheet);
        rf.readCommand(this.tokens.get(2).sequence);
    }

    private void commandCreate(){
        CreateSpreadsheetCommand c = new CreateSpreadsheetCommand();
        this.spreadsheet = c.create();
    }

    private void commandEdit() throws ContentException, CircularDependencyException{
        EditCellCommand e = new EditCellCommand(this.spreadsheet);
        e.edit(this.tokens.get(2).sequence, this.tokens.get(4).sequence);
    }

    private void commandLoad(){
        LoadSpreadsheetCommand l = new LoadSpreadsheetCommand();
        this.spreadsheet = l.Load(this.tokens.get(2).sequence);
    }

    private void commandSave(){
        SaveSpreadsheetCommand s = new SaveSpreadsheetCommand();
        s.save(this.tokens.get(2).sequence, spreadsheet);
    }
}
