/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JuanCarlos
 */
public class MapMatrix implements Matrix{
    
    /**
     * An implementation of Matrix using a map where the keys are strings  
     * indicating a position in the matrix, and the values are the values stored
     * in these positions
     */
    
    /**
     * The map for storing the matrix: the key shall be a string formed by 
     * the concatenation <row>,<col>; example, for row=2 and col=15, the key shall 
     * be the string "2,15"
     */
    private Map<String,Double> map;
    
    /**
     * The maximum index of row where a non-zero value has been set
     */
    private int maxRow;

    /**
     * The maximum index of column where a non-zero value has been set
     */
    private int maxCol;

    /**
     * Constructor: creates an empty HashMap
     */
    public MapMatrix(){
        map = new HashMap<String,Double>();
    }
    
    /**
     * Get the value of maxCol
     *
     * @return the value of maxCol
     */
    public int getMaxCol() {
        return this.maxCol;
    }

    /**
     * Get the value of maxRow
     *
     * @return the value of maxRow
     */
    public int getMaxRow() {
        return this.maxRow;
    }

    /**
     * Sets the value val in the position identified by row and col; if row is 
     * greater than maxRow then sets maxRow to row; if col is greater than maxCol 
     * then sets maxCol to col
     * @param row the row of the position 
     * @param col the column of the position
     * @param val the value to be set in the position identified by row and col
     */
    @Override
    public void setVal(int row, int col, double val) {
        if(row>this.maxRow){
            this.maxRow = row;
        }
        if(col>this.maxCol){
            this.maxCol = col;
        }
        String key = String.valueOf(row).concat(",").concat(String.valueOf(col));
        if(this.map.containsKey(key)){
            map.replace(key,val);
        }
        else{
            map.put(key, val);
        }
    }
    
    /**
     * Returns the value in the position identified by row and col; if the map 
     * does not contain any pair with the key built with row and col as indicated 
     * above, then the method returns 0.0
     * @param row the row of the position 
     * @param col the column of the position
     * @return the value in the position identified by row and col or 0.0 if
     * map does not contain the key built with row and col
     */
    @Override
    public double getVal(int row, int col) {
        String key = String.valueOf(row).concat(",").concat(String.valueOf(col));
        if(this.map.containsKey(key)){
            return map.get(key);
        }
        else{
            return 0;
        }
    }
    
}
