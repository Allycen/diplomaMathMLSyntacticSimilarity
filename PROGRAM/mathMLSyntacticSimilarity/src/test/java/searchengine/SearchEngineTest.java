package searchengine;

import static GUI.consoleEntry.PATH_TO_MATHML_DATABASE;
import additional.Pair;
import entities.IdxNode;
import entities.MathExpression;
import entities.MathMLTree;
import entities.Sto;
import java.io.IOException;
import junit.framework.TestCase;
import org.xml.sax.SAXException;


public class SearchEngineTest extends TestCase {
    
    public SearchEngineTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLeafRecursiveCollection01() throws SAXException, IOException {
        //System.out.println("testLeafRecursiveCollection:");
        
        MathMLTree t0 = MathMLTree.fromString("0:{a{b{c{e}{f}}{d}}}");
        MathMLTree t1 = MathMLTree.fromString("1:{a{b{c{e}{g}}{d}}}");
        //MathMLTree t0 = MathMLTree.fromString("0:{a{m{n{c}{d}}{k}}}");
        //MathMLTree t1 = MathMLTree.fromString("1:{a{m{n{c}{e}}{k}}}");

        
        //t0.print(true);
        //t1.print(true);
        
        
        //SearchEngine se = new SearchEngine(t0);
        //se.addCorpusTree(t1);
        //se.calculateSubExprSimilarity();
        
//        assertEquals(6, se.getNumberOfCommonNodesByNumCorpTree(1));
    }
    
    
    
    public void testLeafRecursiveCollection0() throws SAXException, IOException {
        //System.out.println("testLeafRecursiveCollection:");
        
        MathMLTree t0 = MathMLTree.fromString("0:{a{b{b{d{f}}{e}}{c}{b{d}{b}}}}");
        MathMLTree t1 = MathMLTree.fromString("1:{a{b{b{d{h}}{e}}{c}{b{d}{b}}}}");
        
        //SearchEngine se = new SearchEngine(t0);
        //se.addCorpusTree(t1);
        //se.calculateSubExprSimilarity();
        
       // assertEquals(6, se.getNumberOfCommonNodesByNumCorpTree(1));
    }
    
    // !!!
    public void testLeafRecursiveCollection() throws SAXException, IOException {
        //System.out.println("testLeafRecursiveCollection:");
        
        MathMLTree t0 = MathMLTree.fromString("0:{a{b{d{g}}{e}{f{h}{j}}}{c}}");
        MathMLTree t1 = MathMLTree.fromString("1:{a{b{d}{e}{f{h}{j}}}{c}}");
        
//        SearchEngine se = new SearchEngine(t0);
//        se.addCorpusTree(t1);
//        se.calculateSubExprSimilarity();
//        
//        assertEquals(6, se.getNumberOfCommonNodesByNumCorpTree(1));
    }
    

     public void testLeafRecursiveCollection2() throws SAXException, IOException {
        //System.out.println("testLeafRecursiveCollection2:");
        
        MathMLTree t0 = MathMLTree.fromString("0:{a{b{d}}{c{e}}}");
        MathMLTree t1 = MathMLTree.fromString("1:{a{b}{c}}");
        
        
       // SearchEngine se = new SearchEngine(t0);
       // se.addCorpusTree(t1);
        //se.calculateSubExprSimilarity();
        
       // assertEquals(0, se.getNumberOfCommonNodesByNumCorpTree(1));
    }
     
    public void testLeafRecursiveCollection3() throws SAXException, IOException {
        //System.out.println("testLeafRecursiveCollection3:");
        
        MathMLTree t0 = MathMLTree.fromString("0:{a{b{e}}{c}{d{f}{g}}}");
        MathMLTree t1 = MathMLTree.fromString("1:{a{b}{c}{d{f}{g}}}");
        
        //SearchEngine se = new SearchEngine(t0);
        //se.addCorpusTree(t1);
        //se.calculateSubExprSimilarity();
        
        //assertEquals(6, se.getNumberOfCommonNodesByNumCorpTree(1));
    }  
    
    public void testShowTree() throws SAXException, IOException {
        MathExpression expr1 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr1.xml");
        MathExpression expr2 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr2.xml");
        MathExpression expr5 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr5.xml");
        MathExpression expr6 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr6.xml");
        MathExpression expr7 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr7.xml");
        MathExpression expr9 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr9.xml");
        MathExpression expr21 = new MathExpression(PATH_TO_MATHML_DATABASE, "expr21.xml");
        
        
        SearchEngine se = new SearchEngine(expr1.getMathMLTree());
        se.addCorpusTree(expr2.getMathMLTree());
        se.addCorpusTree(expr5.getMathMLTree());
        se.addCorpusTree(expr6.getMathMLTree());
        se.addCorpusTree(expr7.getMathMLTree());
        se.addCorpusTree(expr9.getMathMLTree());
        se.addCorpusTree(expr21.getMathMLTree());
        
        
        se.calculateSubExprSimilarity();
        se.printStoList(SearchEngine.StoOrder.TREEID);
    }
//
//    public void testStructSim() throws SAXException, IOException {
//        MathExpression trigExpr1 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr1.xml");
//        MathExpression trigExpr2 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr2.xml");
//        MathExpression trigExpr3 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr3.xml");
//        MathExpression trigExpr4 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr4.xml");
//        MathExpression trigExpr5 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr5.xml");
//        MathExpression trigExpr6 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr6.xml");
//        MathExpression trigExpr7 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr7.xml");
//        MathExpression trigExpr8 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr8.xml");
//        MathExpression trigExpr9 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr9.xml");
//        MathExpression trigExpr10 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr10.xml");
//        
//        SearchEngine se1 = new SearchEngine(trigExpr1.getMathMLTree());
//        se1.addCorpusTree(trigExpr1.getMathMLTree());
//        se1.addCorpusTree(trigExpr2.getMathMLTree());
//        se1.addCorpusTree(trigExpr3.getMathMLTree());
//        se1.addCorpusTree(trigExpr4.getMathMLTree());
//        se1.addCorpusTree(trigExpr5.getMathMLTree());
//        se1.addCorpusTree(trigExpr6.getMathMLTree());
//        se1.addCorpusTree(trigExpr7.getMathMLTree());
//        se1.addCorpusTree(trigExpr8.getMathMLTree());
//        se1.addCorpusTree(trigExpr9.getMathMLTree());
//        se1.addCorpusTree(trigExpr10.getMathMLTree());
//        
//        se1.calculateStructuralSimilarity();
//        //se1.printStoList(SearchEngine.StoOrder.TREEID);
//        
//        assertEquals(84, se1.getNumberOfCommonNodesByNumCorpTree(0));
//        assertEquals(62, se1.getNumberOfCommonNodesByNumCorpTree(2));
//        assertEquals(0, se1.getNumberOfCommonNodesByNumCorpTree(3));
//        assertEquals(0, se1.getNumberOfCommonNodesByNumCorpTree(4));
//        assertEquals(42, se1.getNumberOfCommonNodesByNumCorpTree(5));
//        assertEquals(0, se1.getNumberOfCommonNodesByNumCorpTree(6));
//        assertEquals(8, se1.getNumberOfCommonNodesByNumCorpTree(7));
//        assertEquals(0, se1.getNumberOfCommonNodesByNumCorpTree(8));
//        assertEquals(0, se1.getNumberOfCommonNodesByNumCorpTree(9));
//        assertEquals(20, se1.getNumberOfCommonNodesByNumCorpTree(10));
//        
//        
//        SearchEngine se2 = new SearchEngine(trigExpr10.getMathMLTree());
//        se2.addCorpusTree(trigExpr10.getMathMLTree());
//        
//        se2.calculateStructuralSimilarity();
//        //se2.printStoList(SearchEngine.StoOrder.TREEID);
//  
//    }
//    
//    
//    /**
//     * Test of calculateSubExprSimilarity method, of class SearchEngine.
//     */
//    public void testSubExprSim() throws SAXException, IOException {
//        MathExpression trigExpr1 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr1.xml");
//        MathExpression trigExpr2 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr2.xml");
//        MathExpression trigExpr3 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr3.xml");
//        MathExpression trigExpr4 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr4.xml");
//        MathExpression trigExpr5 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr5.xml");
//        MathExpression trigExpr6 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr6.xml");
//        MathExpression trigExpr7 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr7.xml");
//        MathExpression trigExpr8 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr8.xml");
//        MathExpression trigExpr9 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr9.xml");
//        MathExpression trigExpr10 = new MathExpression(PATH_TO_MATHML_DATABASE, "trigonometryExpr10.xml");
//        
//        
//        // проверяем 1ое выражение с каждым
//        SearchEngine se1 = new SearchEngine(trigExpr1.getMathMLTree());
//        se1.addCorpusTree(trigExpr1.getMathMLTree());
//        se1.addCorpusTree(trigExpr2.getMathMLTree());
//        se1.addCorpusTree(trigExpr3.getMathMLTree());
//        se1.addCorpusTree(trigExpr4.getMathMLTree());
//        se1.addCorpusTree(trigExpr5.getMathMLTree());
//        se1.addCorpusTree(trigExpr6.getMathMLTree());
//        se1.addCorpusTree(trigExpr7.getMathMLTree());
//        se1.addCorpusTree(trigExpr8.getMathMLTree());
//        se1.addCorpusTree(trigExpr9.getMathMLTree());
//        se1.addCorpusTree(trigExpr10.getMathMLTree());
//        se1.calculateSubExprSimilarity();
//        //se1.printStoList(SearchEngine.StoOrder.TREEID);
//        
//        assertEquals(84, se1.getNumberOfCommonNodesByNumCorpTree(0));
//        assertEquals(30, se1.getNumberOfCommonNodesByNumCorpTree(2));
//        assertEquals(24, se1.getNumberOfCommonNodesByNumCorpTree(3));
//        assertEquals(24, se1.getNumberOfCommonNodesByNumCorpTree(4));
//        assertEquals(26, se1.getNumberOfCommonNodesByNumCorpTree(5));
//        assertEquals(26, se1.getNumberOfCommonNodesByNumCorpTree(6));
//        assertEquals(20, se1.getNumberOfCommonNodesByNumCorpTree(7));
//        assertEquals(14, se1.getNumberOfCommonNodesByNumCorpTree(8));
//        assertEquals(32, se1.getNumberOfCommonNodesByNumCorpTree(9));
//        assertEquals(14, se1.getNumberOfCommonNodesByNumCorpTree(10));
//        
//        // проверяем 2ое выражение с каждым
//        SearchEngine se2 = new SearchEngine(trigExpr2.getMathMLTree());
//        se2.addCorpusTree(trigExpr2.getMathMLTree());
//        se2.addCorpusTree(trigExpr3.getMathMLTree());
//        se2.addCorpusTree(trigExpr4.getMathMLTree());
//        se2.addCorpusTree(trigExpr5.getMathMLTree());
//        se2.addCorpusTree(trigExpr6.getMathMLTree());
//        se2.addCorpusTree(trigExpr7.getMathMLTree());
//        se2.addCorpusTree(trigExpr8.getMathMLTree());
//        se2.addCorpusTree(trigExpr9.getMathMLTree());
//        se2.addCorpusTree(trigExpr10.getMathMLTree());
//        se2.calculateSubExprSimilarity();
//        //se2.printStoList(SearchEngine.StoOrder.TREEID);
//        
//        assertEquals(88, se2.getNumberOfCommonNodesByNumCorpTree(0));
//        assertEquals(24, se2.getNumberOfCommonNodesByNumCorpTree(2));
//        assertEquals(24, se2.getNumberOfCommonNodesByNumCorpTree(3));
//        assertEquals(4, se2.getNumberOfCommonNodesByNumCorpTree(4));
//        assertEquals(24, se2.getNumberOfCommonNodesByNumCorpTree(5));
//        assertEquals(24, se2.getNumberOfCommonNodesByNumCorpTree(6));
//        assertEquals(4, se2.getNumberOfCommonNodesByNumCorpTree(7));
//        assertEquals(4, se2.getNumberOfCommonNodesByNumCorpTree(8));
//        assertEquals(4, se2.getNumberOfCommonNodesByNumCorpTree(9));
//        
//        
//        // проверяем 3-ее выражение с каждым
//        SearchEngine se3 = new SearchEngine(trigExpr3.getMathMLTree());
//        se3.addCorpusTree(trigExpr3.getMathMLTree());
//        se3.addCorpusTree(trigExpr4.getMathMLTree());
//        se3.addCorpusTree(trigExpr5.getMathMLTree());
//        se3.addCorpusTree(trigExpr6.getMathMLTree());
//        se3.addCorpusTree(trigExpr7.getMathMLTree());
//        se3.addCorpusTree(trigExpr8.getMathMLTree());
//        se3.addCorpusTree(trigExpr9.getMathMLTree());
//        se3.addCorpusTree(trigExpr10.getMathMLTree());
//        se3.calculateSubExprSimilarity();
//        //se3.printStoList(SearchEngine.StoOrder.TREEID);
//        
//        assertEquals(72, se3.getNumberOfCommonNodesByNumCorpTree(0));
//        assertEquals(24, se3.getNumberOfCommonNodesByNumCorpTree(2));
//        assertEquals(4, se3.getNumberOfCommonNodesByNumCorpTree(3));
//        assertEquals(18, se3.getNumberOfCommonNodesByNumCorpTree(4));
//        assertEquals(18, se3.getNumberOfCommonNodesByNumCorpTree(5));
//        assertEquals(4, se3.getNumberOfCommonNodesByNumCorpTree(6));
//        assertEquals(4, se3.getNumberOfCommonNodesByNumCorpTree(7));
//        assertEquals(4, se3.getNumberOfCommonNodesByNumCorpTree(8));
//        
////        // проверяем 4-ое выражение с каждым
////        SearchEngine se4 = new SearchEngine(trigExpr4.getMathMLTree());
////        se4.addCorpusTree(trigExpr4.getMathMLTree());
////        se4.addCorpusTree(trigExpr5.getMathMLTree());
////        se4.addCorpusTree(trigExpr6.getMathMLTree());
////        se4.addCorpusTree(trigExpr7.getMathMLTree());
////        se4.addCorpusTree(trigExpr8.getMathMLTree());
////        se4.addCorpusTree(trigExpr9.getMathMLTree());
////        se4.addCorpusTree(trigExpr10.getMathMLTree());
////        se4.calculateSubExprSimilarity();
////        //se4.printStoList(SearchEngine.StoOrder.TREEID);
////        
////        
////        // проверяем 5-ое выражение с каждым
////        SearchEngine se5 = new SearchEngine(trigExpr5.getMathMLTree());
////        se5.addCorpusTree(trigExpr5.getMathMLTree());
////        se5.addCorpusTree(trigExpr6.getMathMLTree());
////        se5.addCorpusTree(trigExpr7.getMathMLTree());
////        se5.addCorpusTree(trigExpr8.getMathMLTree());
////        se5.addCorpusTree(trigExpr9.getMathMLTree());
////        se5.addCorpusTree(trigExpr10.getMathMLTree());
////        se5.calculateSubExprSimilarity();
////        //se5.printStoList(SearchEngine.StoOrder.TREEID);
////        
////        SearchEngine se6 = new SearchEngine(trigExpr6.getMathMLTree());
////        se6.addCorpusTree(trigExpr6.getMathMLTree());
////        se6.addCorpusTree(trigExpr7.getMathMLTree());
////        se6.addCorpusTree(trigExpr8.getMathMLTree());
////        se6.addCorpusTree(trigExpr9.getMathMLTree());
////        se6.addCorpusTree(trigExpr10.getMathMLTree());
////        se6.calculateSubExprSimilarity();
////        //se6.printStoList(SearchEngine.StoOrder.TREEID);
////        
////        SearchEngine se7 = new SearchEngine(trigExpr7.getMathMLTree());
////        se7.addCorpusTree(trigExpr7.getMathMLTree());
////        se7.addCorpusTree(trigExpr8.getMathMLTree());
////        se7.addCorpusTree(trigExpr9.getMathMLTree());
////        se7.addCorpusTree(trigExpr10.getMathMLTree());
////        se7.calculateSubExprSimilarity();
////        //se7.printStoList(SearchEngine.StoOrder.TREEID);
////        
////        SearchEngine se8 = new SearchEngine(trigExpr8.getMathMLTree());
////        se8.addCorpusTree(trigExpr8.getMathMLTree());
////        se8.addCorpusTree(trigExpr9.getMathMLTree());
////        se8.addCorpusTree(trigExpr10.getMathMLTree());
////        se8.calculateSubExprSimilarity();
////        //se8.printStoList(SearchEngine.StoOrder.TREEID);
////        
////        SearchEngine se9 = new SearchEngine(trigExpr9.getMathMLTree());
////        se9.addCorpusTree(trigExpr9.getMathMLTree());
////        se9.addCorpusTree(trigExpr10.getMathMLTree());
////        se9.calculateSubExprSimilarity();
////        //se9.printStoList(SearchEngine.StoOrder.TREEID);
////        
////        SearchEngine se10 = new SearchEngine(trigExpr10.getMathMLTree());
////        se10.addCorpusTree(trigExpr10.getMathMLTree());
////        se10.calculateSubExprSimilarity();
////        //se10.printStoList(SearchEngine.StoOrder.TREEID);
////        
//    }
//    
//    
// 
//
//    /**
//     * Test of topIndexNode method, of class SearchEngine.
//     */
//    public void testTopIndexNode2 () {
//        //System.out.println("topIndexNod2");
//        
//        MathMLTree t0 = MathMLTree.fromString("0:{a{b}{c}{d{b}{c}}{d}}");
//        MathMLTree t1 = MathMLTree.fromString("1:{a{b{c}}{c}{d{b}{c}}{d}}");
//        
//        
//        IdxNode<String> t0n0 = new IdxNode<>("a",0,1,5,1);
//        IdxNode<String> t1n0 = new IdxNode<>("a",1,1,5,1);
//        
//        IdxNode<String> t0n1 = new IdxNode<>("c",0,1,0,3);
//        IdxNode<String> t1n1 = new IdxNode<>("c",1,1,0,3);
//        
//        
//        SearchEngine se = new SearchEngine(t0);
//        
//        assertEquals(new Pair<>(t0n0,t1n0), se.topIndexNode(t0n1, t0, t1n1, t1));
//    }
//    
//    
//    public void testTopIndexNode() {
//        //System.out.println("topIndexNode");
//        
//        MathMLTree t0 = MathMLTree.fromString("0:{a{m{a{c{e}}{c{f}}{d{g}}{c{h}}}{k{l}}}}");
//        MathMLTree t1 = MathMLTree.fromString("1:{a{b{a{c{e}}{c{f}}{d{g}}{c{h}}}}}");
//        
//        IdxNode<String> t0n1 = new IdxNode<>("a",0,1,5,1);
//        IdxNode<String> t1n1 = new IdxNode<>("a",1,1,4,1);
//        
//        
//        IdxNode<String> t0n2 = new IdxNode<>("a",0,2,4,3);
//        IdxNode<String> t1n2 = new IdxNode<>("a",1,2,4,3);
//        
//        IdxNode<String> t0n3 = new IdxNode<>("c",0,1,1,5);
//        IdxNode<String> t1n3 = new IdxNode<>("c",1,1,1,4);
//        
//        IdxNode<String> t0n4 = new IdxNode<>("c",0,2,1,6);
//        IdxNode<String> t1n4 = new IdxNode<>("c",1,2,1,5);
//        
//        IdxNode<String> t0n5 = new IdxNode<>("c",0,3,1,8);
//        IdxNode<String> t1n5 = new IdxNode<>("c",1,3,1,7);
//        
//        IdxNode<String> t0n6 = new IdxNode<>("d",0,1,1,7);
//        IdxNode<String> t1n6 = new IdxNode<>("d",1,1,1,6);
//        
//        IdxNode<String> t0n7 = new IdxNode<>("e",0,1,0,10);
//        IdxNode<String> t1n7 = new IdxNode<>("e",1,1,0,8);
//        
//        IdxNode<String> t0n8 = new IdxNode<>("f",0,1,0,11);
//        IdxNode<String> t1n8 = new IdxNode<>("f",1,1,0,9);
//        
//        IdxNode<String> t0n9 = new IdxNode<>("g",0,1,0,12);
//        IdxNode<String> t1n9 = new IdxNode<>("g",1,1,0,10);
//        
//        IdxNode<String> t0n10 = new IdxNode<>("h",0,1,0,13);
//        IdxNode<String> t1n10 = new IdxNode<>("h",1,1,0,11);
//        
//        
//        SearchEngine instance = new SearchEngine(t0);
//        
//        assertEquals(new Pair<>(t0n1, t1n1), instance.topIndexNode(t0n1, t0, t1n1, t1));
//        assertEquals(new Pair<>(t0n2, t1n2), instance.topIndexNode(t0n2, t0, t1n2, t1));
//        
//        assertEquals(new Pair<>(t0n2,t1n2), instance.topIndexNode(t0n3, t0, t1n3, t1)); 
//        assertEquals(new Pair<>(t0n2,t1n2), instance.topIndexNode(t0n4, t0, t1n4, t1));
//        assertEquals(new Pair<>(t0n2,t1n2), instance.topIndexNode(t0n5, t0, t1n5, t1));
//        assertEquals(new Pair<>(t0n2,t1n2), instance.topIndexNode(t0n6, t0, t1n6, t1));
//        assertEquals(new Pair<>(t0n2,t1n2), instance.topIndexNode(t0n7, t0, t1n7, t1));
//        assertEquals(new Pair<>(t0n2,t1n2), instance.topIndexNode(t0n8, t0, t1n8, t1));
//        assertEquals(new Pair<>(t0n2,t1n2), instance.topIndexNode(t0n9, t0, t1n9, t1));
//        assertEquals(new Pair<>(t0n2,t1n2), instance.topIndexNode(t0n10, t0, t1n10, t1));
//        
//        assertEquals(new Pair<>(t0n3,t1n5), instance.topIndexNode(t0n3, t0, t1n5, t1));
//        assertEquals(new Pair<>(t0n1,t1n2), instance.topIndexNode(t0n1, t0, t1n2, t1));
//    }
//
//

    
}
