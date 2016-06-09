
package entities;

import additional.ConsoleStrings;
import static entities.MathMLTree.fromNode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import javax.swing.tree.TreeNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public final class ProductionRule<T> {
    
    private final T topNode;
    private final List<T> children;
    
    
    public ProductionRule(T topNode, List<T> children) {
        this.topNode = topNode;
        
        if(children == null)
            this.children = new ArrayList<>();
        else
            this.children = children;
    }
    
    public ProductionRule(TreeNode node) {
        // T -> String
        
        this.topNode = (T) ((MathMLTree)node).getLabel();
        this.children = new ArrayList<>();

        for(Enumeration e = node.children(); e.hasMoreElements();)
            this.children.add((T) ((MathMLTree)e.nextElement()).getLabel());
    }
    
    
    public List<T> getChildren() {
        return Collections.unmodifiableList(this.children);
    }
    
    public T getTopNode() {
        return this.topNode;
    }
    
    public String getStringView() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(ConsoleStrings.LEFTQUOTES).
                append(topNode.toString()).
                append(ConsoleStrings.RIGHTQUOTES);
        
        if(!children.isEmpty()) {
            
            sb.append(ConsoleStrings.PR);
            for (T child : children) 
                sb.append(ConsoleStrings.SPACE).
                        append(ConsoleStrings.LEFTQUOTES).
                        append(child.toString()).
                        append(ConsoleStrings.RIGHTQUOTES);
        }
        
        return sb.toString();
    }
    
    public void print() {
        System.out.println(getStringView());
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if(obj == this) 
            return true;
        
        if(obj == null) 
            return false;
        
        if (obj instanceof ProductionRule<?>)
            if(((ProductionRule<?>)obj).topNode.equals(this.topNode) &&
                    ((ProductionRule<?>)obj).children.equals(this.children))
                return true;
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.topNode);
        hash = 31 * hash + Objects.hashCode(this.children);
        return hash;
    }
    
}
