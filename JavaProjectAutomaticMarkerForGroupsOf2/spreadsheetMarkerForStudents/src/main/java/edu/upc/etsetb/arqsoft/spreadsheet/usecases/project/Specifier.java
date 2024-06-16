/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.usecases.project;

import edu.upc.etsetb.arqsoft.spreadsheet.auxiliar.CoordinateCreator;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Coordinate;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Formula;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Function;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Argument;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Content;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Number;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Range;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Operator;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.SUM;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.MAX;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.MIN;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.NumericalContent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.PROMEDIO;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.TextContent;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.ContentException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.marker.ReadingSpreadSheetException;
import static edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token.TokenType.TEXT_CONTENT;
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
    
    public List<FormulaComponent> specifyFormulaComponents() throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException{
        List<FormulaComponent> formulaComponents = new LinkedList<>();
        Integer startFunction = 0;
        Integer functionsInside = 0;
        String nameFunction = "";
        for(int i = 1; i < this.components.size(); i++){
            Token currentToken = this.components.get(i);
            breakIf:
            switch(currentToken.token){
                case OPERATOR:
                    formulaComponents.add(new Operator(currentToken.sequence));
                    break;
                case INTEGER:
                case FLOAT_NUM:
                    if(startFunction==0){
                        formulaComponents.add(new Number(Double.parseDouble(currentToken.sequence)));
                    }
                    break;
                case CELL_COORD:
                    if(startFunction==0){
                        Coordinate coord = CoordinateCreator.create(currentToken.sequence);
                        for(Cell cell :this.cells)
                        {
                            if(cell.getCoordinate().equals(coord)){
                                formulaComponents.add(cell);
                                break breakIf;
                            }
                        }
                        Cell cell =new Cell(coord,new TextContent(""),this.cells);
                        this.cells.add(cell);
                        formulaComponents.add(cell);
                    }
                    break;
                case FUNCTION_NAME:
                    if(startFunction==0){
                        startFunction=i;
                        nameFunction = currentToken.sequence;
                    }
                    else{
                    functionsInside++; 
                    }
                    break;
                case CLOSE_PAREN:
                    if(startFunction !=0){
                        if(functionsInside == 0){
                            String functionString = new String();
                            int lastStartFunction =startFunction;
                            for(int j = lastStartFunction; j < i; j++){
                                functionString += this.components.get(j).sequence;
                            }
                            functionString += currentToken.sequence;
                            Function function = this.specifyFunction(nameFunction, functionString);
                            nameFunction = "";
                            formulaComponents.add(function); 
                        }
                        else{
                            functionsInside--;
                        }
                    }else{
                        formulaComponents.add(new Operator(currentToken.sequence));
                    }
                    break;
                case OPEN_PAREN:
                    if(startFunction == 0){
                        formulaComponents.add(new Operator(currentToken.sequence));
                    }
                    break;
            }
        }
        return formulaComponents;
    }
    
    public LinkedList<Argument> specifyFunctionArguments(List<Token> tokens) throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException{
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
                        Coordinate coord = CoordinateCreator.create(currentToken.sequence);
                        for(Cell cell :this.cells)
                        {
                            if(cell.getCoordinate().equals(coord)){
                                if(isRange){
                                    //If it is here, the syntax is correct
                                    Cell firstCell = (Cell) arguments.removeLast();
                                    arguments.add(new Range(firstCell, cell,this.cells));
                                    isRange = false;
                                }else{
                                    arguments.add(cell);
                                }
                                break breakIf;
                            }
                        }
                        Cell cell = new Cell(coord, new TextContent(""),this.cells);
                        arguments.add(cell);
                        this.cells.add(cell);
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
                            functionString += tokens.get(j).sequence;
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
    
    
    public Function specifyFunction(String type, String arguments) throws NoNumberException, TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, ContentException{
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
    
    public static Content specifyContent(Token token, Coordinate coord, List<Cell> cells) throws ContentException, CircularDependencyException{
        Content content;
        Tokenizer tokenizerFormula = new Tokenizer(Tokenizer.TokenizerType.FORMULA);
        switch(token.token){
            case FORMULA:
                try {
                    String formulaContent = token.sequence.replaceAll(",", ";");
                    List<Token> tokens = tokenizerFormula.tokenize(formulaContent);
                    new SyntaxChecker(tokens).check();
                    Specifier specifier = new Specifier(tokens, cells);
                    List<FormulaComponent> formulaComponents = specifier.specifyFormulaComponents();
                    content = new Formula(formulaContent, formulaComponents, cells);
                } 
                catch (TokenWrittenIncorrectlyException | WrongSyntaxException ex) {
                    throw new ContentException(ex.getMessage());
                }
                break;
            case TEXT_CONTENT:
                content = new TextContent(token.sequence);
                break;
            case INTEGER:
            case FLOAT_NUM:
                double number = Double.parseDouble(token.sequence);
                content = new NumericalContent(new Number(number));
                break;
            default:
                throw new ContentException("The content is not text, numerical or formula");
        }
         return content;
    }
}
