package GUI;




import entities.IdxNode;
import entities.MathExpression;
import entities.MathMLTree;
import entities.ProductionRule;
import entities.PRwithIdxNodes;
import java.io.IOException;


import org.xml.sax.SAXException;
import searchengine.SearchEngine;


public class consoleEntry {
    
    public final static String PATH_TO_MATHML_DATABASE = "mathDataFiles/database";
    
    public static void main(String args[]) throws SAXException, IOException {
        
        // ------- DATA -------------
        //MathMLTree t0 = MathMLTree.fromString("0:{a{m{a{c{e}}{c{f}}{d{g}}{c{h}}}{k{l}}}}");
        //MathMLTree t1 = MathMLTree.fromString("1:{a{b{a{c{e}}{c{f}}{d{g}}{c{h}}}}}");
        //MathMLTree t2 = MathMLTree.fromString("2:{a{j{a{c{e}}{c{f}}{d{g}}{c{g}}}{k{l}}}}");
        
        
        
        //MathExpression e1 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr1.xml");
        //MathExpression e2 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr2.xml");
        //MathExpression e3 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr3.xml");
        
        
        //ProductionRule<String> PR = new ProductionRule<>("a", Arrays.asList("c","c","d","с"));
        //IdxNode<String> n1 = new IdxNode<>("e", 1, 1, 0, 2);
        //IdxNode<String> n2 = new IdxNode<>("e", 0, 1, 0, 8);
        
        
        
        //SearchEngine se = new SearchEngine(trigExpr1.getMathMLTree());
        
        //se.addCorpusTree(e2.getMathMLTree());
        //se.addCorpusTree(e1.getMathMLTree());
        //se.calculateSubExprSimilarity();
        //se.printStoList();
        
        //engine.addCorpusTree(t1);
        //engine.addCorpusTree(t2);        
        //engine.addCorpusTree(t3);
        
        //trigExpr1.printTreeStructure();
        
        // ------- TESTS -------------
              
        
        
        //n1.print(IdxNode.Content.label_treeID_repeatID, true);
        //engine.top(n1, t0, n1, t0);
        
        
        //engine.printQueryIndexTable();
        //engine.printCorpusIndexTable();
        //System.out.println("Subexpr similarity:");
        //engine.calculateSubExprSimilarity();
        
    }
    
    
    
}
