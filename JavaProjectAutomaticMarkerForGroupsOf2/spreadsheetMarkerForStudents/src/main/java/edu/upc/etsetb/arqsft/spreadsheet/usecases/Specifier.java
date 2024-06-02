/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsft.spreadsheet.usecases;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operator;
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
        for(int i = 1; i < this.components.size(); i++){
            Token currentToken = this.components.get(i);
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
                                break;
                            }
                            throw new NonExistentCell("The referenced cell does not exist, please create it before using it");
                        }
                    }
                    break;
                case FUNCTION_NAME:
                    startFunctions.add(i);
                    break;
                case CLOSE_PAREN:
                    if(!startFunctions.isEmpty()){
                        String function = new String();
                        for(int j = startFunctions.getLast(); j < i; j++){
                            function += this.components.get(j).sequence;
                        }
                        function += currentToken.sequence;
                        //TODO adapt tokenizer to tokenize the function arguments
                        //formulaComponents.add(new Formula(currentToken.sequence));
                        
                        //CAN be used the current tokenizer to tokenize a formula?? 
                        //Maybe yes, but a specifier is needed
                        //The syntax checker is not needed if it is done in a formula, 
                        //but in a function can be needed o maybe the same syntax checker can validate it? 
                        //the function will not begin with a =, it will begin with the function name
                        //Also, we can adjust the syntaxchecker and the specifier to work in both cases with the same code
                        //but using a new bool to determine if it is a function or it is a formula
                        //this can be done viewing the first token, if is a  = it is a formula
                        //if is a function name it is a function
                        //if it is neither both, throw exception (?)
                        //Maybe this one is the solution
                        startFunctions.removeLast();
                    }
                    break;
            
            }
        }
        return formulaComponents;
    }
    
    
}
