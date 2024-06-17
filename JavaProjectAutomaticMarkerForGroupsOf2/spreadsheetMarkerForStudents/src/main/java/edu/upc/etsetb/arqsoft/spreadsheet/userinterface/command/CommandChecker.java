/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command;

import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token;
import java.util.List;

/**
 *
 * @author oscar
 */
public class CommandChecker {
    private List<Token> tokens;
    
    public CommandChecker(List<Token> tokens){
        this.tokens = tokens;
    }
    public void checkCommand() throws CommandException{
        Token first = this.tokens.getFirst();
        switch(first.token){
            case COMMAND_READ_COMMAND:
                this.checkReadCommand();
                break;
            case COMMAND_CREATE:
                this.checkCommandCreate();
                break;
            case COMMAND_EDIT_CELL:
                this.checkCommandEdit();
                break;
            case COMMAND_LOAD:
                this.checkCommandLoad();
                break;
            case COMMAND_SAVE:
                this.checkCommandSave();
                break;
            default:
                throw new CommandException("The command must start with RF, C, E, L or S");
                
        }
    }

    private void checkReadCommand() throws CommandException {
        if(this.tokens.size()!=3){
            throw new CommandException("The command has to be RF[SPACE][FILENAME]");
        }
        if( !this.tokens.get(1).token.equals(Token.TokenType.SPACE) || !this.tokens.get(2).token.equals(Token.TokenType.LETTER)){
            throw new CommandException("Something after the RF is wrong");
        }
    }

    private void checkCommandCreate() throws CommandException {
        if(this.tokens.size()!=1){
            throw new CommandException("The command has to be C");
        }
    }

    private void checkCommandEdit() throws CommandException {
        if(this.tokens.size()!=5){
            throw new CommandException("The command has to be E[SPACE][COORDINATE][SPACE][CONTENT]");
        }
        if( !this.tokens.get(1).token.equals(Token.TokenType.SPACE) || !this.tokens.get(2).token.equals(Token.TokenType.CELL_COORD) ||!this.tokens.get(3).token.equals(Token.TokenType.SPACE) || !this.tokens.get(3).token.equals(Token.TokenType.LETTER)){
            throw new CommandException("Something after the E is wrong");
        }
    }

    private void checkCommandLoad() throws CommandException {
        if(this.tokens.size()!=2){
            throw new CommandException("The command has to be L[SPACE][FILENAME]");
        }
        if( !this.tokens.get(1).token.equals(Token.TokenType.SPACE) || !this.tokens.get(2).token.equals(Token.TokenType.LETTER)){
            throw new CommandException("Something after the L is wrong");
        }
    }

    private void checkCommandSave() throws CommandException {
        if(this.tokens.size()!=2){
            throw new CommandException("The command has to be S[SPACE][FILENAME]");
        }
        if( !this.tokens.get(1).token.equals(Token.TokenType.SPACE) || !this.tokens.get(2).token.equals(Token.TokenType.LETTER)){
            throw new CommandException("Something after the S is wrong");
        }
    }
}
