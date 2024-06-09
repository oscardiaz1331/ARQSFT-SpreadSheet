/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Function;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Range;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.SUM;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.MAX;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.MIN;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.PROMEDIO;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.NonExistentCell;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author oscar
 */
public class Specifier {
    private List<Token> components;
    private List<Cell> cells;
    
    public Specifier(List<Token> components, List<Cell> cells){
        this.components = components;
        this.cells = cells;
    }
    
    public List<FormulaComponent> specifyFormulaComponents() throws TokenWrittenIncorrectlyException, NonExistentCell{
        List<FormulaComponent> formulaComponents = new LinkedList<>();
        List<Integer> startFunctions = new LinkedList<>();
        List<String> nameFunctions = new LinkedList<>();
        for(int i = 1; i < this.components.size(); i++){
            Token currentToken = this.components.get(i);
            breakIf:
            switch(currentToken.token){
                case OPERATOR:
                    formulaComponents.add(new Operator(currentToken.sequence));
                    break;
                case INTEGER:
                case FLOAT_NUM:
                    if(startFunctions.isEmpty()){
                        formulaComponents.add(new Number(Double.parseDouble(currentToken.sequence)));
                    }
                    break;
                case CELL_COORD:
                    if(startFunctions.isEmpty()){
                        Tokenizer tokenizer =  new Tokenizer(Tokenizer.TokenizerType.COORDINATES);
                        List<Token> coordTokens = tokenizer.tokenize(currentToken.sequence);
                        int row = 0;
                        String column = "";
                        for(Token coordToken : coordTokens){
                            //It has passed away the syntax checker function. 
                            //There are not errors
                            if(coordToken.token == Token.TokenType.LETTER){
                                column = coordToken.sequence;
                            }
                            else if(coordToken.token == Token.TokenType.INTEGER){
                                row =  Integer.parseInt(coordToken.sequence);
                            }
                        }
                        Coordinate coord = new Coordinate(row, column);
                        for(Cell cell :this.cells)
                        {
                            if(cell.getCoordinate().equals(coord)){
                                formulaComponents.add(cell);
                                break breakIf;
                            }
                        }
                        throw new NonExistentCell("The referenced cell " + coord.toString() + " does not exist, please create it before using it");
                    }
                    break;
                case FUNCTION_NAME:
                    if(startFunctions.isEmpty()){
                        startFunctions.add(i);
                        nameFunctions.add(currentToken.sequence);
                    }
                    break;
                case CLOSE_PAREN:
                    if(!startFunctions.isEmpty()){
                        String functionString = new String();
                        int lastStartFunction =startFunctions.removeLast();
                        for(int j = lastStartFunction; j < i; j++){
                            functionString += this.components.get(j).sequence;
                        }
                        functionString += currentToken.sequence;
                        //TODO adapt tokenizer to tokenize the function arguments
                        String type = nameFunctions.removeLast();
                        Function function = this.specifyFunction(type, functionString);
                        formulaComponents.add(function); 
                    }
                    break;
            }
        }
        return formulaComponents;
    }
    
        public LinkedList<Argument> specifyFunctionArguments(List<Token> tokens) throws TokenWrittenIncorrectlyException, NonExistentCell{
        LinkedList<Argument> arguments = new LinkedList<>();
        List<Integer> startFunctions = new LinkedList<>();
        List<String> nameFunctions = new LinkedList<>();
        boolean isRange = false;
        for(int i = 1; i < tokens.size(); i++){
            Token currentToken = tokens.get(i);
            breakIf:
            switch(currentToken.token){
                case INTEGER:
                case FLOAT_NUM:
                    if(startFunctions.isEmpty()){
                        arguments.add(new Number(Double.parseDouble(currentToken.sequence)));
                    }
                    break;
                case CELL_COORD:
                    if(startFunctions.isEmpty()){
                        Tokenizer tokenizer =  new Tokenizer(Tokenizer.TokenizerType.COORDINATES);
                        List<Token> coordTokens = tokenizer.tokenize(currentToken.sequence);
                        int row = 0;
                        String column = "";
                        for(Token coordToken : coordTokens){
                            //It has passed away the syntax checker function. 
                            //There are not errors
                            if(coordToken.token == Token.TokenType.LETTER){
                                column = coordToken.sequence;
                            }
                            else if(coordToken.token == Token.TokenType.INTEGER){
                                row =  Integer.parseInt(coordToken.sequence);
                            }
                        }
                        Coordinate coord = new Coordinate(row, column);
                        for(Cell cell :this.cells)
                        {
                            if(cell.getCoordinate().equals(coord)){
                                if(isRange){
                                    //If it is here, the syntax is correct
                                    Cell firstCell = (Cell) arguments.removeLast();
                                    arguments.add(new Range(firstCell, cell));
                                    isRange = false;
                                }else{
                                    arguments.add(cell);
                                }
                                break breakIf;
                            }
                        }
                        throw new NonExistentCell("The referenced cell " + coord.toString() + " does not exist, please create it before using it");
                    }
                    break;
                case COLON:
                    isRange = true;
                    break;
                case FUNCTION_NAME:
                    startFunctions.add(i);
                    nameFunctions.add(currentToken.sequence);
                    break;
                case CLOSE_PAREN:
                    if(!startFunctions.isEmpty()){
                        String functionString = new String();
                        int lastStartFunction =startFunctions.removeLast();
                        for(int j = lastStartFunction; j < i; j++){
                            functionString += this.components.get(j).sequence;
                        }
                        functionString += currentToken.sequence;
                        String type = nameFunctions.removeLast();
                        Function function = this.specifyFunction(type, functionString);
                        arguments.add(function);                      
                    }
                    break;
            }
        }
        return arguments;
    }
    public Function specifyFunction(String type, String arguments) throws TokenWrittenIncorrectlyException, NonExistentCell{
        Tokenizer tokenizer =  new Tokenizer(Tokenizer.TokenizerType.FORMULA);
        List<Token> argumentsFunction = tokenizer.tokenize(arguments);
        Function function = null;
            switch(type){
                case Function.SUM:
                   function =new SUM(this.specifyFunctionArguments(argumentsFunction));
                    break;
                case Function.MAX:
                    function =new MAX(this.specifyFunctionArguments(argumentsFunction));
                    break;
                case Function.MIN:
                    function =new MIN(this.specifyFunctionArguments(argumentsFunction));
                    break;
                case Function.PROMEDIO:
                    function =new PROMEDIO(this.specifyFunctionArguments(argumentsFunction));
                    break;
            }
        return function;
    }
}
