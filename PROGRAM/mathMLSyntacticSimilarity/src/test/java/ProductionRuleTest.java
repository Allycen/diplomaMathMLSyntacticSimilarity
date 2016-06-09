
import entities.MathMLElement;
import entities.MathMLPresentationMarkupElement;
import entities.ProductionRule;
import java.util.ArrayList;
import java.util.Arrays;
import junit.framework.TestCase;


public class ProductionRuleTest extends TestCase {
    
    public ProductionRuleTest(String testName) {
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
        ProductionRule<String> PR1 = new ProductionRule<>("a", new ArrayList<String>());
        ProductionRule<String> PR2 = new ProductionRule<>("a", new ArrayList<String>());
        
        assertEquals(true, PR1.equals(PR2));
    }
    
    public void testEqStr2() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", null);
        ProductionRule<String> PR2 = new ProductionRule<>("a", null);
        
        assertEquals(true, PR1.equals(PR2));
    }
    
    public void testEqStr3() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("b"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("b"));
        
        assertEquals(true, PR1.equals(PR2));
    }
    
    public void testEqStr4() {
        ProductionRule<String> PR1 = new ProductionRule<>("math", Arrays.asList("mo", "mn"));
        ProductionRule<String> PR2 = new ProductionRule<>("math", Arrays.asList("mo", "mn"));
        
        assertEquals(true, PR1.equals(PR2));
    }
    
    public void testEqStr5() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("d", "c", "e", "f", "h"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("d", "c", "e", "f", "h"));
        
        assertEquals(true, PR1.equals(PR2));
    }
 
    public void testEqStr6() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", new ArrayList<String>());
        ProductionRule<String> PR2 = new ProductionRule<>("a", null);
        
        assertEquals(true, PR1.equals(PR2));
    }
    
    public void testEqStr7() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", null);
        ProductionRule<String> PR2 = new ProductionRule<>("a", new ArrayList<String>());
        
        assertEquals(true, PR1.equals(PR2));
    }
    
    
    public void testEqMathML4() {
        
        MathMLElement e1 = MathMLPresentationMarkupElement.MO;
        MathMLElement e2 = MathMLPresentationMarkupElement.MN;
        MathMLElement e3 = MathMLPresentationMarkupElement.MROW;        
           
        ProductionRule<MathMLElement> PR1 = new ProductionRule<>(e1, Arrays.asList(e2, e3));
        ProductionRule<MathMLElement> PR2 = new ProductionRule<>(e1, Arrays.asList(e2, e3));
        
        assertEquals(true, PR1.equals(PR2));
    }
    
    
    
    public void testNEqStr1() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", new ArrayList<String>());
        ProductionRule<String> PR2 = new ProductionRule<>("b", new ArrayList<String>());
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    public void testNEqStr2() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", null);
        ProductionRule<String> PR2 = new ProductionRule<>("b", null);
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    public void testNEqStr3() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("c"));
        ProductionRule<String> PR2 = new ProductionRule<>("b", Arrays.asList("c"));
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    public void testNEqStr4() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("b"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("c"));
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    public void testNEqStr5() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("b", "c"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("b", "e"));
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    public void testNEqStr6() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("b", "c"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("c", "b"));
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    
    public void testNEqStr7() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("b", "c"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("b", "c", "d"));
        
        assertEquals(false, PR1.equals(PR2));
    }
      
    public void testNEqStr8() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("b", "c", "d"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("b", "c"));
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    public void testNEqStr9() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("b", "c", "d"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("b", "c"));
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    public void testNEqStr10() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", null);
        ProductionRule<String> PR2 = new ProductionRule<>("a", Arrays.asList("b", "c"));
        
        assertEquals(false, PR1.equals(PR2));
    }
    
    public void testNEqStr11() {
        ProductionRule<String> PR1 = new ProductionRule<>("a", Arrays.asList("b", "c", "d"));
        ProductionRule<String> PR2 = new ProductionRule<>("a", null);
        
        assertEquals(false, PR1.equals(PR2));
    }
      
    
}
