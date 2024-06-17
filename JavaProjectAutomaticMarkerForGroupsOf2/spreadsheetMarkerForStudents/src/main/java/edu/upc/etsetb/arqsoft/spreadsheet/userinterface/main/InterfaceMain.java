/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface.main;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command.CommandExecutor;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command.ReadFromFileCommand;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ICreateSpreadSheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IEditCell;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ILoadSpreadSheet;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IReadFromFile;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.ISaveSpreadSheet;
import java.util.Scanner;

/**
 *
 * @author esthe
 */
public class InterfaceMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Spreadsheet spreadsheet = new Spreadsheet();
        CommandExecutor commandExecutor = new CommandExecutor(spreadsheet);

        System.out.println("Welcome to Spreadsheet Application!");

        while (true) {
            showMenu();
            String userInput = scanner.nextLine();

            switch (userInput.toLowerCase()) {
                case "1":
                    commandExecutor.execute(new ReadFromFileCommand());
                    break;
                case "2":
                    commandExecutor.execute(new CreateSpreadsheetCommand());
                    break;
                case "3":
                    commandExecutor.execute(new EditCellCommand(spreadsheet));
                    break;
                case "4":
                    commandExecutor.execute(new LoadSpreadsheetCommand());
                    break;
                case "5":
                    commandExecutor.execute(new SaveSpreadsheetCommand());
                    break;
                case "q":
                    System.out.println("Exiting Spreadsheet Application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Read from file");
        System.out.println("2. Create new spreadsheet");
        System.out.println("3. Edit cell");
        System.out.println("4. Load spreadsheet");
        System.out.println("5. Save spreadsheet");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
}