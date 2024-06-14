/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

/**
 *
 * @author oscar
 */
public class ColumnManager {
    public static String newColumn(String column){
        char[] chars = column.toCharArray();        
        for(int i = chars.length -1; i>=0;i--){
            if(chars[i] == 'Z'){
                chars[i] = 'A';
            }
            else{
                chars[i]++;
                return new String(chars);
            }
        }
        return 'A' + new String(chars);
    }
}
