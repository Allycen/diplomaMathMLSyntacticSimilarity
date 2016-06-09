
import entities.MathMLTree;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.tree.TreeNode;
import junit.framework.TestCase;


public class MathMLTreeTest extends TestCase {
    
    
    public MathMLTreeTest(String testName) {
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

    public void testSiblingsNumber1() {
        MathMLTree t = MathMLTree.fromString("{a{b}{c}{d}}");
        MathMLTree node = (MathMLTree)t.getChildAt(0);
        
        assertEquals(1, node.getSiblingsNumber());
    }
     
    public void testSiblingsNumber2() {
        MathMLTree t = MathMLTree.fromString("{a{b}{b}{c}}");
        MathMLTree node = (MathMLTree)t.getChildAt(1);
        
        assertEquals("b", node.getLabel());
        assertEquals(2, node.getSiblingsNumber());
    }
    
    public void testSiblingsNumber3() {
        MathMLTree t = MathMLTree.fromString("{a{d}{c}{c}}");
        MathMLTree n1 = (MathMLTree)t.getChildAt(1);
        MathMLTree n2 = (MathMLTree)t.getChildAt(2);
        
        assertEquals("c", n1.getLabel());
        assertEquals(2, n1.getSiblingsNumber());
        
        assertEquals("c", n2.getLabel());
        assertEquals(3, n2.getSiblingsNumber());
    }
    

    public void testLabelIdxTime1() {
        MathMLTree t = MathMLTree.fromString("{a{d}{c}{c}}");
        MathMLTree node = (MathMLTree)t.getChildAt(2);
        
        assertEquals(2, node.getLabelIdxTime());
    }
    
    public void testLabelIdxTime2() {
        MathMLTree t = MathMLTree.fromString("{a{b{a{c{e}}{c{f}}{d{g}}{c{h}}}}}");
        MathMLTree node = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(3);
        
        assertEquals(3, node.getLabelIdxTime());
    }
    
    
    public void testLabelIdxTime3() {
        MathMLTree t = MathMLTree.fromString("{a{e}{f}{g}{g}}");
        MathMLTree node = (MathMLTree)t.getChildAt(3);
        
        assertEquals(2, node.getLabelIdxTime());
    }
    
    public void testSiblingsNumber4() {
        MathMLTree t = MathMLTree.fromString("{a{c{e}}{c{f}}{d{g}}{c{g}}}");
        
        MathMLTree n1 = (MathMLTree)t.getChildAt(2).getChildAt(0);
        MathMLTree n2 = (MathMLTree)t.getChildAt(3).getChildAt(0);
        
        assertEquals(1, n1.getSiblingsNumber());
        assertEquals(1, n2.getSiblingsNumber());
    }
    
    
    public void testLabelIdxTime4() {
        MathMLTree t = MathMLTree.fromString("{a{c{e}}{c{f}}{d{g}}{c{g}}}");
        MathMLTree n1 = (MathMLTree)t.getChildAt(0);
        MathMLTree n2 = (MathMLTree)t.getChildAt(1);
        MathMLTree n3 = (MathMLTree)t.getChildAt(2);
        MathMLTree n4 = (MathMLTree)t.getChildAt(3);
        
        MathMLTree n5 = (MathMLTree)t.getChildAt(0).getChildAt(0);
        MathMLTree n6 = (MathMLTree)t.getChildAt(1).getChildAt(0);
        MathMLTree n7 = (MathMLTree)t.getChildAt(2).getChildAt(0);
        MathMLTree n8 = (MathMLTree)t.getChildAt(3).getChildAt(0);
        
        assertEquals("c", n1.getLabel());
        assertEquals(1, n1.getLabelIdxTime());
        
        assertEquals("c", n2.getLabel());
        assertEquals(2, n2.getLabelIdxTime());
        
        assertEquals("d", n3.getLabel());
        assertEquals(1, n3.getLabelIdxTime());
        
        assertEquals("c", n4.getLabel());
        assertEquals(3, n4.getLabelIdxTime());
        
        
        assertEquals("e", n5.getLabel());
        assertEquals(1, n5.getLabelIdxTime());
        
        assertEquals("f", n6.getLabel());
        assertEquals(1, n6.getLabelIdxTime());
        
        assertEquals("g", n7.getLabel());
        assertEquals(1, n7.getLabelIdxTime());
        
        assertEquals("g", n8.getLabel());
        assertEquals(2, n8.getLabelIdxTime()); //!!
    }
    
    
    
    public void testNodeBreadthOrder3() {
        MathMLTree t = MathMLTree.fromString("{a{e}{f}{g}{g}}");
        MathMLTree node = (MathMLTree)t.getChildAt(3);
        
        assertEquals(5, node.getNodeBreadthOrder());
    }
    
    
    public void testNodeBreadthOrder1() {
        MathMLTree t = MathMLTree.fromString("1:{a{b{a{c{e}}{c{f}}{d{g}}{c{h}}}}}");
        //MathMLTree n = (fromMathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(2);
        MathMLTree n2 = (MathMLTree)t.getChildAt(0);
        MathMLTree n3 = (MathMLTree)t.getChildAt(0).getChildAt(0);
        MathMLTree n4 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(0);
        MathMLTree n5 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(1);
        MathMLTree n6 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(2);
        MathMLTree n7 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(3);
        
        MathMLTree n8 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(0).getChildAt(0);
        MathMLTree n9 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(1).getChildAt(0);
        MathMLTree n10 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(2).getChildAt(0);
        MathMLTree n11 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(3).getChildAt(0);
        
        
        
        assertEquals("a", t.getLabel());
        assertEquals(1, t.getNodeBreadthOrder());
        
        assertEquals("b", n2.getLabel());
        assertEquals(2, n2.getNodeBreadthOrder());
        
        assertEquals("a", n3.getLabel());
        assertEquals(3, n3.getNodeBreadthOrder());
        
        assertEquals("c", n4.getLabel());
        assertEquals(4, n4.getNodeBreadthOrder());
        
        assertEquals("c", n5.getLabel());
        assertEquals(5, n5.getNodeBreadthOrder());
        
        assertEquals("d", n6.getLabel());
        assertEquals(6, n6.getNodeBreadthOrder());
        
        assertEquals("c", n7.getLabel());
        assertEquals(7, n7.getNodeBreadthOrder());
        
        
        assertEquals("e", n8.getLabel());
        assertEquals(8, n8.getNodeBreadthOrder());
        
        assertEquals("f", n9.getLabel());
        assertEquals(9, n9.getNodeBreadthOrder());
        
        assertEquals("g", n10.getLabel());
        assertEquals(10, n10.getNodeBreadthOrder());
        
        assertEquals("h", n11.getLabel());
        assertEquals(11, n11.getNodeBreadthOrder()); 
    }
    
    
    
        public void testNodeBreadthOrder2() {
        MathMLTree t = MathMLTree.fromString("2:{a{j{a{c{e}}{c{f}}{d{g}}{c{g}}}{k{l}}}}");
        
        MathMLTree n2 = (MathMLTree)t.getChildAt(0);
        
        MathMLTree n3 = (MathMLTree)t.getChildAt(0).getChildAt(0);
        MathMLTree n4 = (MathMLTree)t.getChildAt(0).getChildAt(1);
        
        MathMLTree n5 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(0);
        MathMLTree n6 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(1);
        MathMLTree n7 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(2);
        MathMLTree n8 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(3);
        MathMLTree n9 = (MathMLTree)t.getChildAt(0).getChildAt(1).getChildAt(0);
        
        MathMLTree n10 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(0).getChildAt(0);
        MathMLTree n11 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(1).getChildAt(0);
        MathMLTree n12 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(2).getChildAt(0);
        MathMLTree n13 = (MathMLTree)t.getChildAt(0).getChildAt(0).getChildAt(3).getChildAt(0);
        
        
        
        assertEquals("a", t.getLabel());
        assertEquals(1, t.getNodeBreadthOrder());
        
        assertEquals("j", n2.getLabel());
        assertEquals(2, n2.getNodeBreadthOrder());
        
        assertEquals("a", n3.getLabel());
        assertEquals(3, n3.getNodeBreadthOrder());
        assertEquals("k", n4.getLabel());
        assertEquals(4, n4.getNodeBreadthOrder());
        
        assertEquals("c", n5.getLabel());
        assertEquals(5, n5.getNodeBreadthOrder());
        assertEquals("c", n6.getLabel());
        assertEquals(6, n6.getNodeBreadthOrder());
        assertEquals("d", n7.getLabel());
        assertEquals(7, n7.getNodeBreadthOrder());
        assertEquals("c", n8.getLabel());
        assertEquals(8, n8.getNodeBreadthOrder());
        assertEquals("l", n9.getLabel());
        assertEquals(9, n9.getNodeBreadthOrder());
        
        assertEquals("e", n10.getLabel());
        assertEquals(10, n10.getNodeBreadthOrder());
        assertEquals("f", n11.getLabel());
        assertEquals(11, n11.getNodeBreadthOrder());
        assertEquals("g", n12.getLabel());
        assertEquals(12, n12.getNodeBreadthOrder());
        
        assertEquals("g", n13.getLabel());
        assertEquals("c", ((MathMLTree)n13.getParent()).getLabel());
        assertEquals(2, ((MathMLTree)n13).getLabelIdxTime());
        
        assertEquals(13, n13.getNodeBreadthOrder()); // НАДО ИСПРАВИТЬ!
        
    }
    
        

}
