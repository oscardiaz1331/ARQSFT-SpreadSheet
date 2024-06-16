/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.userinterface;

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
import java.util.Scanner;

/**
 *
 * @author esthe
 */
public class EditCell extends Command{
    private Coordinate cellCoordinate;
    private Content newContent;

    public EditCell(Coordinate cellCoordinate, Content newContent) {
        this.cellCoordinate = cellCoordinate;
        this.newContent = newContent;
    }

    @Override
    public void execute(Spreadsheet spreadsheet, S2VLoader loader, S2VStore store) throws NoNumberException, ContentException, CircularDependencyException, WrongSyntaxException, TokenWrittenIncorrectlyException {
        Scanner scanner = new Scanner(System.in);
        
        // Solicitar coordenadas de la celda al usuario
        System.out.print("Ingrese la coordenada de la celda (ej. A1): ");
        String coordInput = scanner.nextLine();
        try {
            cellCoordinate = Coordinate.fromString(coordInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Coordenada inválida. Por favor, intente de nuevo.");
            return;
        }

        // Solicitar nuevo contenido para la celda al usuario
        System.out.print("Ingrese el nuevo contenido para la celda: ");
        String contentInput = scanner.nextLine();
        try {
            newContent = Content.fromString(contentInput);
        } catch (WrongSyntaxException e) {
            System.out.println("Contenido inválido. Por favor, intente de nuevo.");
            return;
        }

        // Intentar editar la celda en la hoja de cálculo
        try {
            spreadsheet.editCell(cellCoordinate, newContent);
            System.out.println("La celda ha sido actualizada exitosamente.");
        } catch (CircularDependencyException e) {
            System.out.println("Error: Dependencia circular detectada. La operación no puede completarse.");
        } catch (ContentException e) {
            System.out.println("Error: Contenido inválido. " + e.getMessage());
        }

        // Mostrar la hoja de cálculo actualizada
        spreadsheet.display();
    }
}
