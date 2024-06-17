/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface.main;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command.CommandExecutor;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command.ReadFromFileCommand;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ICreateSpreadSheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IEditCell;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ILoadSpreadSheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IReadFromFile;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ISaveSpreadSheet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author esthe
 */
public class InterfaceMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Spreadsheet spreadsheet = new Spreadsheet();
        System.out.println("Welcome to Spreadsheet Application!");
        String userInput = "";
        CommandExecutor commandExecutor = new CommandExecutor(spreadsheet);
        while(true){
            showMenu();
            userInput = scanner.nextLine();
            if(userInput.contains("6")){
                break;
            }
            showOptions();
            String command = scanner.nextLine();
            
            try {
                commandExecutor.execute(command);
            } catch (TokenWrittenIncorrectlyException |ContentException | CircularDependencyException ex) {
                System.out.println("There is an error:\n");
                System.err.println(ex.getMessage());
                System.out.println("Try again");
            }
        }
        System.out.println("Closing program");
    }

    private static void showMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Read from file");
        System.out.println("2. Create new spreadsheet");
        System.out.println("3. Edit cell");
        System.out.println("4. Load spreadsheet");
        System.out.println("5. Save spreadsheet");
        System.out.println("6. Exit");
    }
    private static void showOptions(){
        System.out.println("\nOptions:\nRF <filename>");
        System.out.println("C");
        System.out.println("E <cell coordinate(LetterNumber)> <content>");
        System.out.println("L <filename>");
        System.out.println("S <filename>");
    }
}