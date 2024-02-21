/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.run;

import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.EqsLinearSystem;
import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.FullMatrix;
import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.MapMatrix;
import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.Vector;
import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.solvers.GaussEqsLinearSystemSolver;
import edu.upc.etsetb.arqsoft.lab.polymorphism.linearsystem.solvers.GaussJordanEqsLinearSystemSolver;

/**
 *
 * @author JuanCarlos
 */
public class Main {
    
    public static void main(String[] args){
        /*
        1. Create the following matrix supported by a FullMatrix object
        */
        int dim=7;
        FullMatrix matrix = new FullMatrix(dim);
        matrix.setVal(0, 0, 4);
        matrix.setVal(0, 1, -2);
        matrix.setVal(0, 3, -2);
        matrix.setVal(1, 0, -1);
        matrix.setVal(1, 1, 4);
        matrix.setVal(1, 2, -1);
        matrix.setVal(1, 4, -2);
        matrix.setVal(2, 1, -1);
        matrix.setVal(2, 2, 4);
        matrix.setVal(2, 5, -2);
        matrix.setVal(3, 0, -1);
        matrix.setVal(3, 3, 4);
        matrix.setVal(3, 4, -2);
        matrix.setVal(4, 1, -1);
        matrix.setVal(4, 3, -1);
        matrix.setVal(4, 4, 4);
        matrix.setVal(4, 5, -1);
        matrix.setVal(5, 2, -1);
        matrix.setVal(5, 4, -1);
        matrix.setVal(5, 5, 4);
        matrix.setVal(0, 6, 2);
        matrix.setVal(1, 6, 2);
        matrix.setVal(2, 6, 2);
        matrix.setVal(3, 6, 2);
        matrix.setVal(4, 6, 2);
        matrix.setVal(5, 6, 2);
        FullMatrix matrix2 = new FullMatrix(dim);
        matrix2.setVal(0, 0, 4);
        matrix2.setVal(0, 1, -2);
        matrix2.setVal(0, 3, -2);
        matrix2.setVal(1, 0, -1);
        matrix2.setVal(1, 1, 4);
        matrix2.setVal(1, 2, -1);
        matrix2.setVal(1, 4, -2);
        matrix2.setVal(2, 1, -1);
        matrix2.setVal(2, 2, 4);
        matrix2.setVal(2, 5, -2);
        matrix2.setVal(3, 0, -1);
        matrix2.setVal(3, 3, 4);
        matrix2.setVal(3, 4, -2);
        matrix2.setVal(4, 1, -1);
        matrix2.setVal(4, 3, -1);
        matrix2.setVal(4, 4, 4);
        matrix2.setVal(4, 5, -1);
        matrix2.setVal(5, 2, -1);
        matrix2.setVal(5, 4, -1);
        matrix2.setVal(5, 5, 4);
        matrix2.setVal(0, 6, 2);
        matrix2.setVal(1, 6, 2);
        matrix2.setVal(2, 6, 2);
        matrix2.setVal(3, 6, 2);
        matrix2.setVal(4, 6, 2);
        matrix2.setVal(5, 6, 2);
        /*
        2. Create the following vector for the system of linear equations
        */
        int dim2=6;
        Vector vector = new Vector(dim2);
        for(int i = 0; i < dim2; i++){
            vector.setVal(i, 2);
        }
        /*
        3. Create a linear system supported by EqsLinearSystem whose matrix and 
        vector are the matrix and vector previously created
        */
        EqsLinearSystem eqsLinearSystem = new EqsLinearSystem(matrix, vector);
        EqsLinearSystem eqsLinearSystem2 = new EqsLinearSystem(matrix2, vector);
        /*
        4. Create a solver for solving the previous system of linear equations 
        using gauss elimination
        */
        GaussEqsLinearSystemSolver gaussEqsLinearSystemSolver = new GaussEqsLinearSystemSolver();
        /*
        5. Solve the system and print the solution
        */
        gaussEqsLinearSystemSolver.solve(eqsLinearSystem);
        Vector sol= ((Vector)gaussEqsLinearSystemSolver.getSolution());
        System.out.println("Gausss for FullMatrix");
        for(int i=0;i<dim2;i++){
            System.out.println(sol.getVal(i));
        }
        /*
        6. Now create a solver for solving the previous system of linear equations 
        using gauss-jordan elimination
        */
        GaussJordanEqsLinearSystemSolver gaussJordanEqsLinearSystemSolver = new GaussJordanEqsLinearSystemSolver();
        /*
        7. Solve the system and print the solution
        */
        gaussJordanEqsLinearSystemSolver.solve(eqsLinearSystem2);
        Vector sol2= ((Vector)gaussJordanEqsLinearSystemSolver.getSolution());
        System.out.println("Gauss Jordan for FullMatrix");
        for(int i=0;i<dim2;i++){
            System.out.println(sol2.getVal(i));
        }
        /*
        8 to 14 Repeat steps 1 to 7 but supporting the matrix of the system with
        an object MapMatrix
        */
         /*
        1. Create the following matrix supported by a FullMatrix object
        */
        MapMatrix mapMatrix = new MapMatrix();
        mapMatrix.setVal(0, 0, 4);
        mapMatrix.setVal(0, 1, -2);
        mapMatrix.setVal(0, 3, -2);
        mapMatrix.setVal(1, 0, -1);
        mapMatrix.setVal(1, 1, 4);
        mapMatrix.setVal(1, 2, -1);
        mapMatrix.setVal(1, 4, -2);
        mapMatrix.setVal(2, 1, -1);
        mapMatrix.setVal(2, 2, 4);
        mapMatrix.setVal(2, 5, -2);
        mapMatrix.setVal(3, 0, -1);
        mapMatrix.setVal(3, 3, 4);
        mapMatrix.setVal(3, 4, -2);
        mapMatrix.setVal(4, 1, -1);
        mapMatrix.setVal(4, 3, -1);
        mapMatrix.setVal(4, 4, 4);
        mapMatrix.setVal(4, 5, -1);
        mapMatrix.setVal(5, 2, -1);
        mapMatrix.setVal(5, 4, -1);
        mapMatrix.setVal(5, 5, 4);
        mapMatrix.setVal(0, 6, 2);
        mapMatrix.setVal(1, 6, 2);
        mapMatrix.setVal(2, 6, 2);
        mapMatrix.setVal(3, 6, 2);
        mapMatrix.setVal(4, 6, 2);
        mapMatrix.setVal(5, 6, 2);
        MapMatrix mapMatrix2 = new MapMatrix();
        mapMatrix2.setVal(0, 0, 4);
        mapMatrix2.setVal(0, 1, -2);
        mapMatrix2.setVal(0, 3, -2);
        mapMatrix2.setVal(1, 0, -1);
        mapMatrix2.setVal(1, 1, 4);
        mapMatrix2.setVal(1, 2, -1);
        mapMatrix2.setVal(1, 4, -2);
        mapMatrix2.setVal(2, 1, -1);
        mapMatrix2.setVal(2, 2, 4);
        mapMatrix2.setVal(2, 5, -2);
        mapMatrix2.setVal(3, 0, -1);
        mapMatrix2.setVal(3, 3, 4);
        mapMatrix2.setVal(3, 4, -2);
        mapMatrix2.setVal(4, 1, -1);
        mapMatrix2.setVal(4, 3, -1);
        mapMatrix2.setVal(4, 4, 4);
        mapMatrix2.setVal(4, 5, -1);
        mapMatrix2.setVal(5, 2, -1);
        mapMatrix2.setVal(5, 4, -1);
        mapMatrix2.setVal(5, 5, 4);
        mapMatrix2.setVal(0, 6, 2);
        mapMatrix2.setVal(1, 6, 2);
        mapMatrix2.setVal(2, 6, 2);
        mapMatrix2.setVal(3, 6, 2);
        mapMatrix2.setVal(4, 6, 2);
        mapMatrix2.setVal(5, 6, 2);
        /*
        2. Create the following vector for the system of linear equations
        */

        Vector vector2 = new Vector(dim2);
        for(int i = 0; i < dim2; i++){
            vector2.setVal(i, 2);
        }
        /*
        3. Create a linear system supported by EqsLinearSystem whose matrix and 
        vector are the matrix and vector previously created
        */
        EqsLinearSystem eqsLinearSystem3 = new EqsLinearSystem(mapMatrix, vector2);
        EqsLinearSystem eqsLinearSystem4 = new EqsLinearSystem(mapMatrix2, vector2);
        /*
        4. Create a solver for solving the previous system of linear equations 
        using gauss elimination
        */
        GaussEqsLinearSystemSolver gaussEqsLinearSystemSolver2 = new GaussEqsLinearSystemSolver();
        /*
        5. Solve the system and print the solution
        */
        gaussEqsLinearSystemSolver2.solve(eqsLinearSystem3);
        Vector sol3 = ((Vector)gaussEqsLinearSystemSolver2.getSolution());
        System.out.println("Gauss for MapMatrix");
        for(int i=0;i<dim2;i++){
            System.out.println(sol3.getVal(i));
        }
        /*
        6. Now create a solver for solving the previous system of linear equations 
        using gauss-jordan elimination
        */
        GaussJordanEqsLinearSystemSolver gaussJordanEqsLinearSystemSolver2 = new GaussJordanEqsLinearSystemSolver();
        /*
        7. Solve the system and print the solution
        */
        gaussJordanEqsLinearSystemSolver2.solve(eqsLinearSystem4);
        Vector sol4= ((Vector)gaussJordanEqsLinearSystemSolver2.getSolution());
        System.out.println("Gausss Jordan for MapMatrix");
        for(int i=0;i<dim2;i++){
            System.out.println(sol4.getVal(i));
        }
    }
}
