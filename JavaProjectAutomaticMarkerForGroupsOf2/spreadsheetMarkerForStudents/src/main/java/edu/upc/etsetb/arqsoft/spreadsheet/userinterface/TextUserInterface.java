/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Spreadsheet;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.TextContent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VLoader;
import edu.upc.etsetb.arqsoft.spreadsheet.storage.S2VStore;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.SavingSpreadSheetException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author esthe
 */

//TODo: split this class in different classes to mantain the SRP
public class TextUserInterface {
    private final Scanner scanner;
    private final Spreadsheet spreadsheet;
    private final S2VLoader loader;
    private final S2VStore store;

    public TextUserInterface(String filename, Spreadsheet spreadsheet) throws ReadingSpreadSheetException, SavingSpreadSheetException{
        this.scanner = new Scanner(System.in);
        this.spreadsheet = spreadsheet;
        this.loader = new S2VLoader(filename);
        this.store = new S2VStore(filename,spreadsheet);
    }

    public void start() throws ReadingSpreadSheetException {
        System.out.println("Welcome to Text-based Spreadsheet Application!");

        while (true) {
            showMenu();
            System.out.print("> ");
            String line = scanner.nextLine().trim();

            try {
                switch (line) {
                    case "1":
                        System.out.print("Enter file path: ");
                        String filePath = scanner.nextLine().trim();
                        executeReadFromFile(filePath);
                        break;
                    case "2":
                        executeCommand(new CreateSpreadsheet());
                        break;
                    case "3":
                        System.out.print("Enter cell coordinate and new content (e.g., 1,A newContent): ");
                        String arguments = scanner.nextLine().trim();
                        executeEditCell(arguments);
                        break;
                    case "4":
                        executeCommand(new LoadSpreadsheet());
                        break;
                    case "5":
                        executeCommand(new SaveSpreadsheet());
                        break;
                    case "6":
                        System.out.println("Exiting Text-based Spreadsheet Application.");
                        return;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (CircularDependencyException | WrongSyntaxException | TokenWrittenIncorrectlyException | SavingSpreadSheetException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Read from file");
        System.out.println("2. Create new spreadsheet");
        System.out.println("3. Edit cell");
        System.out.println("4. Load spreadsheet");
        System.out.println("5. Save spreadsheet");
        System.out.println("6. Exit");
    }

    private void executeReadFromFile(String filePath) throws CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException, SavingSpreadSheetException, ReadingSpreadSheetException {
        try (Scanner fileScanner = new Scanner(new FileInputStream(filePath))) {
            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine().trim();
                if (!fileLine.isEmpty()) {
                    processCommand(fileLine);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading commands from file: " + e.getMessage());
        }
    }

    private void executeEditCell(String arguments) throws CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException, SavingSpreadSheetException, ReadingSpreadSheetException {
        String[] parts = arguments.split(" ", 3);
        if (parts.length >= 3) {
            String cellCoordinateStr = parts[0];
            String newContentStr = parts[1] + " " + parts[2];

            Coordinate cellCoordinate = parseCoordinate(cellCoordinateStr);
            Content newContent = new TextContent(newContentStr);

            if (cellCoordinate != null) {
                executeCommand(new EditCell(cellCoordinate, newContent));
            } else {
                System.out.println("Invalid cell coordinate format: " + cellCoordinateStr);
            }
        } else {
            System.out.println("Invalid edit command. Format: E <cell coordinate> <new cell content>");
        }
    }
    //TODO: change this method and all their usages by the function CoordinateCreator: 
    //if u want more information look exxamples in the code
    private Coordinate parseCoordinate(String coordinateStr) {
        String[] parts = coordinateStr.split(",");
        if (parts.length == 2) {
            try {
                int row = Integer.parseInt(parts[0].trim());
                String col = parts[1].trim();
                return new Coordinate(row, col);
            } catch (NumberFormatException e) {
                System.out.println("Error parsing cell coordinate: " + coordinateStr);
            }
        }
        return null;
    }

    private void executeCommand(Command command) throws CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException, SavingSpreadSheetException, ReadingSpreadSheetException {
        try {
            command.execute(spreadsheet, loader, store);
        } catch (ContentException ex) {
            System.out.println("There is an error in the content:\n" + ex.getMessage());
        }
        spreadsheet.display();
    }

    private void processCommand(String commandLine) throws CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException, SavingSpreadSheetException, ReadingSpreadSheetException {
        String[] parts = commandLine.split(" ", 2);
        if (parts.length >= 1) {
            String command = parts[0];
            String arguments = parts.length > 1 ? parts[1] : "";
            switch (command) {
                case "RF":
                    executeReadFromFile(arguments);
                    break;
                case "C":
                    executeCommand(new CreateSpreadsheet());
                    break;
                case "E":
                    executeEditCell(arguments);
                    break;
                case "L":
                    executeCommand(new LoadSpreadsheet());
                    break;
                case "S":
                    executeCommand(new SaveSpreadsheet());
                    break;
                default:
                    System.out.println("Invalid command: " + command);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Spreadsheet spreadsheet = new Spreadsheet();
            TextUserInterface ui;
            // Verifica si se proporcionó el nombre del archivo como argumento
            if (args.length < 1) {
                System.out.println("Usage: java TextUserInterface <filename>");
                return;
            }

            String filename = args[0];
            ui = new TextUserInterface(filename, spreadsheet);
            ui.start();
        } catch (ReadingSpreadSheetException | SavingSpreadSheetException e) {
            System.out.println("Error initializing TextUserInterface: " + e.getMessage());
        }
    }
}