/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.domainmodel;

/**
 *
 * @author oscar
 */
public class Number implements Argument, Value {
    private float num;
    
    public Number(float num){
        this.num = num;
    }
    
    public float getNum(){
        return this.num;
    }
}
