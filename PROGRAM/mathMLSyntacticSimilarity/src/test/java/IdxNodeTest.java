
import entities.IdxNode;
import entities.MathMLTree;

import static junit.framework.Assert.assertEquals;
import junit.framework.TestCase;

public class IdxNodeTest extends TestCase {
    
    public IdxNodeTest(String testName) {
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
   
    
    public void testEqStr1() {
        IdxNode<String> node1 = new IdxNode<>("mo", 1, 3, 3, 5);
        IdxNode<String> node2 = new IdxNode<>("mo", 1, 3, 3, 5);
        
        assertEquals(true, node1.equals(node2));
    }
    
    public void testEqStr2() {
        IdxNode<String> node1 = new IdxNode<>("a", 1, 2, 3, 9);
        IdxNode<String> node2 = new IdxNode<>("a", 7, 15, 11, 4);
        
        assertEquals(false, node1.equals(node2));
    }
    
    
    
    public void testNEqStr1() {
        // узлы НЕ должны совпадать кргда разные названия
        IdxNode<String> node1 = new IdxNode<>("a", 1, 2, 3, 4);
        IdxNode<String> node2 = new IdxNode<>("b", 1, 2, 3, 4);
        
        assertEquals(false, node1.equals(node2));
    }
    

    
    public void testEqWLeavesCountStr1() {
        // должны совпадать если названия и кол-во листьев одинаково
        IdxNode<String> node1 = new IdxNode<>("a", 1, 2, 3, 4);
        IdxNode<String> node2 = new IdxNode<>("a", 1, 2, 3, 4);
        
        assertEquals(true, node1.equals(IdxNode.Content.label_leavesCount, node2));
    }
    
    public void testEqWLeavesCountStr2() {
        IdxNode<String> node1 = new IdxNode<>("b", 4, 2, 3, 5);
        IdxNode<String> node2 = new IdxNode<>("b", 1, 3, 3, 8);
        
        assertEquals(true, node1.equals(IdxNode.Content.label_leavesCount, node2));
    }

    
    
    public void testNEqWLeavesCountStr1() {
        // должны НЕ совпадать если разные названия или кол-во листьев
        IdxNode<String> node1 = new IdxNode<>("a", 1, 2, 4, 5);
        IdxNode<String> node2 = new IdxNode<>("b", 1, 2, 3, 5);
        
        assertEquals(false, node1.equals(IdxNode.Content.label_leavesCount, node2));
    }
    
    public void testNEqWLeavesCountStr2() {
        IdxNode<String> node1 = new IdxNode<>("a", 1, 2, 3, 6);
        IdxNode<String> node2 = new IdxNode<>("a", 1, 2, 4, 6);
        
        assertEquals(false, node1.equals(IdxNode.Content.label_leavesCount, node2));
    }
    
    public void testNEqWLeavesCountStr3() {
        IdxNode<String> node1 = new IdxNode<>("a", 1, 2, 4, 1);
        IdxNode<String> node2 = new IdxNode<>("b", 1, 2, 4, 1);
        
        assertEquals(false, node1.equals(IdxNode.Content.label_leavesCount, node2));
    }
    
    
    
    public void testConstructorByNode1() {
        MathMLTree t1 = MathMLTree.fromString("1:{a{b{a{c{e}}{c{f}}{d{g}}{c{h}}}}}");
        MathMLTree n1 = (MathMLTree)t1.getChildAt(0).getChildAt(0).getChildAt(3).getChildAt(0);
        MathMLTree n2 = (MathMLTree)t1.getChildAt(0).getChildAt(0);
        MathMLTree n3 = (MathMLTree)t1.getChildAt(0).getChildAt(0).getChildAt(2);

        
        IdxNode<String> cn0 = new IdxNode<>(t1);
        IdxNode<String> cn1 = new IdxNode<>(n1);
        IdxNode<String> cn2 = new IdxNode<>(n2);
        IdxNode<String> cn3 = new IdxNode<>(n3);
        
        assertEquals("a", cn0.getLabel());
        assertEquals(1, cn0.getTreeID());
        assertEquals(4, cn0.getLeavesCount());
        assertEquals(1, cn0.getRepeatID());
        assertEquals(1, cn0.getBreadthPosInTree());
        
        assertEquals("h", cn1.getLabel());
        assertEquals(1, cn1.getTreeID());
        assertEquals(0, cn1.getLeavesCount());
        assertEquals(1, cn1.getRepeatID());
        assertEquals(11, cn1.getBreadthPosInTree());
        
        assertEquals("a", cn2.getLabel());
        assertEquals(1, cn2.getTreeID());
        assertEquals(4, cn2.getLeavesCount());
        assertEquals(2, cn2.getRepeatID());
        assertEquals(3, cn2.getBreadthPosInTree());
        
        assertEquals("d", cn3.getLabel());
        assertEquals(1, cn3.getTreeID());
        assertEquals(1, cn3.getLeavesCount());
        assertEquals(1, cn3.getRepeatID());  
        assertEquals(6, cn3.getBreadthPosInTree());
    }
    
    
    
    public void testConstructorByNode2() {
        MathMLTree t2 = MathMLTree.fromString("2:{a{j{a{c{e}}{c{f}}{d{g}}{c{g}}}{k{l}}}}");
        
        
        MathMLTree n1 = (MathMLTree)t2.getChildAt(0).getChildAt(1).getChildAt(0);
        MathMLTree n2 = (MathMLTree)t2.getChildAt(0).getChildAt(1);
        MathMLTree n3 = (MathMLTree)t2.getChildAt(0).getChildAt(0).getChildAt(3).getChildAt(0);


        IdxNode<String> cn0 = new IdxNode<>(t2);
        IdxNode<String> cn1 = new IdxNode<>(n1);
        IdxNode<String> cn2 = new IdxNode<>(n2);
        IdxNode<String> cn3 = new IdxNode<>(n3);

        assertEquals("a", cn0.getLabel());
        assertEquals(2, cn0.getTreeID());
        assertEquals(5, cn0.getLeavesCount());
        assertEquals(1, cn0.getRepeatID());
        assertEquals(1, cn0.getBreadthPosInTree());

        assertEquals("l", cn1.getLabel());
        assertEquals(2, cn1.getTreeID());
        assertEquals(0, cn1.getLeavesCount());
        assertEquals(1, cn1.getRepeatID());
        assertEquals(9, cn1.getBreadthPosInTree());
        
        assertEquals("k", cn2.getLabel());
        assertEquals(2, cn2.getTreeID());
        assertEquals(1, cn2.getLeavesCount());
        assertEquals(1, cn2.getRepeatID());
        assertEquals(4, cn2.getBreadthPosInTree());
        
        assertEquals("g", cn3.getLabel());
        assertEquals(2, cn3.getTreeID());
        assertEquals(0, cn3.getLeavesCount());
        assertEquals(2, cn3.getRepeatID());
        assertEquals(13, cn3.getBreadthPosInTree());

    
    }
    
}
