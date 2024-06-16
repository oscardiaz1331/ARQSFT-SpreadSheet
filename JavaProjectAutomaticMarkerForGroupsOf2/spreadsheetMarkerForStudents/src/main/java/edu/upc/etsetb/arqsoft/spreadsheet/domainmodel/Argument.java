/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import java.util.List;

/**
 *
 * @author oscar
 */
public interface Argument{
  
    /**
     *
     * @return
     * @throws edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException
     */
    public List<Double> getValue()throws NoNumberException;
    
}
