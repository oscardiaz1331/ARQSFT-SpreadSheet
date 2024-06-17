/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface.command;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.CoordinateCreator;
import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.EditCellContent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
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
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.SavingSpreadSheetException;
import edu.upc.etsetb.arqsoft.spreadsheet.userinterface.edu.upc.etsetb.arqsoft.spreadsheet.userinterface.entities.IEditCell;
import java.util.Scanner;

/**
 *
 * @author esthe
 */
public class EditCellCommand extends Command implements IEditCell{
    private Spreadsheet spreadsheet;

    public EditCellCommand(Spreadsheet spreadsheet) {
        this.spreadsheet = spreadsheet;
    }
        //TODO eliminate after copy
//    @Override
//    public void execute(Spreadsheet spreadsheet, S2VLoader loader, S2VStore store) throws NoNumberException, ContentException, CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException {
//        Scanner scanner = new Scanner(System.in);
//        
//        // Solicitar coordenadas de la celda al usuario
//        System.out.print("Ingrese la coordenada de la celda (ej. A1): ");
//        String coordInput = scanner.nextLine();
//        try {
//            cellCoordinate = Coordinate.fromString(coordInput);
//        } catch (IllegalArgumentException e) {
//            System.out.println("Coordenada inválida. Por favor, intente de nuevo.");
//            return;
//        }
//
//        // Solicitar nuevo contenido para la celda al usuario
//        System.out.print("Ingrese el nuevo contenido para la celda: ");
//        String contentInput = scanner.nextLine();
//        try {
//            newContent = Content.fromString(contentInput);
//        } catch (WrongSyntaxException e) {
//            System.out.println("Contenido inválido. Por favor, intente de nuevo.");
//            return;
//        }
//
//        // Intentar editar la celda en la hoja de cálculo
//        try {
//            spreadsheet.editCell(cellCoordinate, newContent);
//            System.out.println("La celda ha sido actualizada exitosamente.");
//        } catch (CircularDependencyException e) {
//            System.out.println("Error: Dependencia circular detectada. La operación no puede completarse.");
//        } catch (ContentException e) {
//            System.out.println("Error: Contenido inválido. " + e.getMessage());
//        }
//
//        // Mostrar la hoja de cálculo actualizada
//        spreadsheet.display();
//    }

    @Override
    public void edit(String cellCoordinate, String newContent) throws ContentException, CircularDependencyException{
        
        //TODO 
        //pedir pedir por consola cell coordi y new content, en un whiile hasta que este bien y tratar las excepcion de content y circular
        //Cuando todo ha ido bien (coord y content correct pues ya la linea de abajo)
        //Hay una excepcion de mala coordenada, deberias lanzarla aqui...
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                // Solicitar coordenadas de la celda al usuario
                System.out.print("Enter the cell coordinate (e.g., A1): ");
                cellCoordinate = scanner.nextLine();

                // Solicitar nuevo contenido para la celda al usuario
                System.out.print("Enter the new content for the cell: ");
                newContent = scanner.nextLine();

                // Intentar editar la celda en la hoja de cálculo
                EditCellContent.edit(cellCoordinate, newContent, this.spreadsheet.getCells());
                System.out.println("The cell has been successfully updated.");
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid coordinate. Please try again.");
            } catch (ContentException e) {
                System.out.println("Error: Invalid content. " + e.getMessage());
                throw e;
            } catch (CircularDependencyException e) {
                System.out.println("Error: Circular dependency detected. The operation cannot be completed.");
                throw e;
            }
        }
    }
}
