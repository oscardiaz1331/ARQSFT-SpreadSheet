/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.upc.etsetb.arqsoft.spreadsheet.auxiliar;

import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Cell;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.FormulaComponent;
import edu.upc.etsetb.arqsoft.spreadsheet.domainmodel.Value;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.CircularDependencyException;
import edu.upc.etsetb.arqsoft.spreadsheet.entities.NoNumberException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.TokenWrittenIncorrectlyException;
import edu.upc.etsetb.arqsoft.spreadsheet.exceptions.WrongSyntaxException;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.CircularDependencyChecker;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.PostfixEvaluator;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.PostfixGenerator;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Specifier;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.SyntaxChecker;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Token;
import edu.upc.etsetb.arqsoft.spreadsheet.usecases.project.Tokenizer;
import java.util.List;

/**
 *
 * @author oscar
 */
public class FormulaComputator {

    private List<Cell> cells;

    public FormulaComputator(List<Cell> cells) {
        this.cells = cells;
    }

    public Value compute(String argument) throws TokenWrittenIncorrectlyException, WrongSyntaxException, CircularDependencyException, NoNumberException {
        Tokenizer tokenizer = new Tokenizer(Tokenizer.TokenizerType.FORMULA);
        List<Token> tokens = tokenizer.tokenize(argument);
        SyntaxChecker syntaxChecker = new SyntaxChecker(tokens);
        syntaxChecker.check();
        Specifier specifier = new Specifier(tokens, this.cells);
        List<FormulaComponent> components = specifier.specifyFormulaComponents();
        CircularDependencyChecker circularDependencyChecker = new CircularDependencyChecker(components);
        circularDependencyChecker.check();
        PostfixGenerator generator = new PostfixGenerator(components);
        components = generator.result();
        PostfixEvaluator evaluator = new PostfixEvaluator(components);
        return evaluator.result();
    }
}
