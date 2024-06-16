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

    public void run() throws SavingSpreadSheetException, ReadingSpreadSheetException{
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
            } catch (CircularDependencyException | WrongSyntaxException | TokenWrittenIncorrectlyException  e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Read from file (RF)");
        System.out.println("2. Create new spreadsheet (C)");
        System.out.println("3. Edit cell (E)");
        System.out.println("4. Load spreadsheet (L)");
        System.out.println("5. Save spreadsheet (S)");
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
        String[] parts = arguments.split(" ", 3); // Usar 3 para incluir todo el contenido de la celda
        if (parts.length >= 3) {
            String cellCoordinateStr = parts[0];
            String newContentStr = parts[1] + " " + parts[2]; // Unir el contenido de la celda si se dividió

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

    private Coordinate parseCoordinate(String coordinateStr) {
        String[] parts = coordinateStr.split(",");
        if (parts.length == 2) {
            try {
                int row = Integer.parseInt(parts[0].trim());
                String col = parts[1].trim(); // Leer la columna como String
                return new Coordinate(row, col); // Crear Coordinate con int y String
            } catch (NumberFormatException e) {
                // Handle parsing error if necessary
                System.out.println("Error parsing cell coordinate: " + coordinateStr);
            }
        }
        return null;
    }


    private void executeCommand(Command command) throws CircularDependencyException, WrongSyntaxException, WrongSyntaxException, TokenWrittenIncorrectlyException, SavingSpreadSheetException, ReadingSpreadSheetException{
        try {
            command.execute(spreadsheet, loader, store);
        } catch (ContentException ex) {
            System.out.println("There is an error in the content:\n"+ex.getMessage());
        }
        spreadsheet.display();
    }

    private void processCommand(String commandLine) throws CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException, SavingSpreadSheetException, ReadingSpreadSheetException{
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

    //TODO
    //Thisn fucntion must not throw anything, neither arrive to this function, they must be caught before
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java TextUserInterface <filename>");
            return;
        }

        String filename = args[0];

        try {
            Spreadsheet spreadsheet = new Spreadsheet();
            TextUserInterface ui = new TextUserInterface(filename, spreadsheet);
            ui.run();
        } catch (ReadingSpreadSheetException | SavingSpreadSheetException e) {
            System.out.println("Error initializing TextUserInterface: " + e.getMessage());
        }
    }
}
