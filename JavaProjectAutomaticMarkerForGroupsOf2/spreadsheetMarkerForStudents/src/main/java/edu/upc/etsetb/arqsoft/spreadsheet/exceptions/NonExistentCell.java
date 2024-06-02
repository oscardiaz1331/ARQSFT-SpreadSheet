/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.exceptions;

/**
 *
 * @author oscar
 */
public class NonExistentCell extends Exception {
    public NonExistentCell(String msg) {
        super(msg);
    }
}
