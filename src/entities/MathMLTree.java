package entities;

import additional.FormatUtilities;
import additional.ConsoleStrings;
import additional.Pair;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class MathMLTree extends DefaultMutableTreeNode {
    
    private String label;
    private int treeID;
    

    public static MathMLTree fromMathMLTree(MathMLTree trueTree) {
        
        MathMLTree clonedTree = new MathMLTree(trueTree.getLabel(), trueTree.getTreeID());
        
        for(Enumeration e = trueTree.children(); e.hasMoreElements();) {
            MathMLTree sibling = (MathMLTree)e.nextElement();
            clonedTree.add(fromMathMLTree(sibling));
        }
                        
        return clonedTree;
    }
    
    public MathMLTree(String label, int treeID) {
        this.label = null;
        this.treeID = -1;
        this.label = label;
        this.treeID = treeID;
    }
    
    public static MathMLTree fromNode(Node node) {
        
        int treeID = -1;
        
        if(MathMLPresentationMarkupElement.contains(node.getNodeName())) {
            MathMLTree tree = new MathMLTree(node.getNodeName(), treeID);
            if(MathMLPresentationMarkupElement.isToken(node.getNodeName())) 
                tree.add(new MathMLTree(node.getTextContent(), treeID));
            
            NodeList childs = node.getChildNodes();
            for (int i = 0; i < childs.getLength(); i++) 
                if(childs.item(i).getNodeType() == Node.ELEMENT_NODE)
                    tree.add(fromNode(childs.item(i))); 
            
          return tree;  
        }
        
        
        return null;
    }
    
    public void setTreeID(int treeID) {
        this.treeID = treeID;
    }
    
    public int getTreeID() {
        return treeID;
    }
    
    public void normilizeTree() {
        for(TreeNode node : getLeaves()) {
            MathMLTree prnt = (MathMLTree) node.getParent();
            
            if(prnt.getLabel().equals("mi")) {
                String name = ((MathMLTree)node).getLabel();
                switch(name) {
                    case "sin":
                        ((MathMLTree)node).setLabel("TRIGF");
                        break;
                    case "cos":
                        ((MathMLTree)node).setLabel("TRIGF");
                        break;
                    case "tan":
                        ((MathMLTree)node).setLabel("TRIGF");
                        break;
                    case "cot":
                        ((MathMLTree)node).setLabel("TRIGF");
                        break;
                    default:
                        ((MathMLTree)node).setLabel("VARNAME");
                }
            }

            if(prnt.getLabel().equals("mo")) {
                String name = ((MathMLTree)node).getLabel();
                switch(name) {
                    case "+":
                        ((MathMLTree)node).setLabel("PLUSMINUS");
                        break;
                    case "-":
                        ((MathMLTree)node).setLabel("PLUSMINUS");
                        break;
                }
            }
            
                   
            if(prnt.getLabel().equals("mn"))
                ((MathMLTree)node).setLabel("NUMVAL");
        }     
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    // какой по счету среди своих родственных узлов считая слево направо
    // счет начинается с единицы
    public int getSiblingsNumber() {
        
        int k = 1;
        DefaultMutableTreeNode prevNode;
        
        if(getPreviousSibling() != null) {
            prevNode = this.getPreviousSibling();
            k++;
            
            
            while(prevNode.getPreviousSibling() != null) {
                prevNode = prevNode.getPreviousSibling();
                k++;
            }
        }
             
        this.getSiblingCount();
        return k;
    }
    
    public List<Pair<String, Integer>> getPostOrderLabeledPathToRoot() {
        
        List<Pair<String, Integer>> path = new ArrayList<>();
        
        MathMLTree tr = this;
        
        path.add(new Pair(tr.label, tr.getSiblingsNumber()));
        
        
        while(!tr.isRoot()) {
            tr = (MathMLTree)tr.getParent();
            
            path.add(new Pair(tr.label, tr.getSiblingsNumber()));
        }
            
        return path;
    }
    
    
    
    // какой это узел по счету таким названием при проходе в ширину от корня дерева
    /*
    Алгоритм нахожения номера узла и повторялся ли он:
        1. Сохраняем путь до корня
        2. От корня дерева начинаем искать (в ширину) элемент с тем же 
            названием.
        3. Когда находим - сверяем пути до корня
            если совпали -> сохраняем номер узла, repeat = 1
            если не совпали -> repeat++ , ищем дальше 
    */
    public int getLabelIdxTime() {
        int repeat = 0;
        List path = this.getPostOrderLabeledPathToRoot();
        MathMLTree root = (MathMLTree)this.getRoot();
        
        
        
        for (Enumeration e = root.breadthFirstEnumeration(); e.hasMoreElements();) {
            MathMLTree node = (MathMLTree)e.nextElement();
            if(node.getLabel().equals(this.label))
                if(path.equals(node.getPostOrderLabeledPathToRoot())) 
                    
                    break;
                else 
                    repeat++;
        }
        
        return ++repeat;
    }
    
    // какой элемент по счету при проходе в ширину
    // связан по логике с getLabelIdxTime()
    public int getNodeBreadthOrder() {
        
        int order = 0;
        List path = this.getPostOrderLabeledPathToRoot();
        MathMLTree root = (MathMLTree)this.getRoot();
        
        for (Enumeration e = root.breadthFirstEnumeration(); e.hasMoreElements();) {
            MathMLTree node = (MathMLTree)e.nextElement();
            order++;
            if(node.getLabel().equals(this.label))
                if(path.equals(node.getPostOrderLabeledPathToRoot()))
                    break;           
        }
        
        return order;
    }
    
    public void setTreeIDSameForAllTree(int trID) {
        
        MathMLTree tr = this;
        
        while(!tr.isRoot()) 
            tr = (MathMLTree)tr.getParent();
        
        for (Enumeration e = breadthFirstEnumeration(); e.hasMoreElements();) {
            MathMLTree t = (MathMLTree) e.nextElement(); 
            t.setTreeID(trID);
        }
        
    }
    
    // дочерние узлы также наследуют treeID
    public static MathMLTree fromString(String s) {
        
        int treeID = FormatUtilities.getTreeID(s);
        
        s = s.substring(s.indexOf("{"), s.lastIndexOf("}") + 1);
        MathMLTree node = new MathMLTree(FormatUtilities.getRoot(s), treeID);
            
        Vector c = FormatUtilities.getChildren(s);
        for(int i = 0; i < c.size(); i++)
            node.add(fromString((String)c.elementAt(i)));
        
        return node;
    }
       
    public int getNodeCount(){
        int sum = 1;
        for(Enumeration e = children(); e.hasMoreElements();)
            sum += ((MathMLTree)e.nextElement()).getNodeCount();

        return sum;
    }    
    

    public String getStringView(boolean showTreeID) {
        
        StringBuilder sb = new StringBuilder();
        
        for(int i = 0; i < getLevel(); i++)
            sb.append(ConsoleStrings.TAB);
        
        
        if(showTreeID)
            sb.append("trID: ").append(this.treeID).append("\n");
        
        
        if(!isRoot())
            sb.append(ConsoleStrings.BRANCH);
        else 
            sb.append(ConsoleStrings.ROOT);
        
        
        sb.append(ConsoleStrings.SPACE).
            append(ConsoleStrings.LEFTQUOTES).
            append(this.label).
            append(ConsoleStrings.RIGHTQUOTES).append("\n");
        
        for(Enumeration e = children(); e.hasMoreElements();)
                sb.append(((MathMLTree)e.nextElement()).getStringView(false));
        
        return sb.toString();
    }
    
    
    public void print(boolean printTreeID) {
        System.out.println(getStringView(printTreeID));
    }    
    
    public void prettyPrint() {
        for(int i = 0; i < getLevel(); i++)
            System.out.print(ConsoleStrings.TAB);

        if(!isRoot())
            System.out.print(ConsoleStrings.BRANCH);
        else {
            System.out.println("trID: " + this.treeID);
            System.out.print(ConsoleStrings.ROOT);
        }
        
        System.out.print((new StringBuilder(" '")).append(label).append("' ").toString());
        System.out.println();
        
        
        for(Enumeration e = children(); e.hasMoreElements(); 
                ((MathMLTree)e.nextElement()).prettyPrint());
    }
    
    public String getLabel() {
        return this.label;
    }

    public Vector<TreeNode> getLeaves() {
            
        Vector<TreeNode> leaves = new Vector<>();
        
        
        TreeNode node;
        Enumeration enum_ = breadthFirstEnumeration(); // order matters not

        while (enum_.hasMoreElements()) {
            node = (TreeNode)enum_.nextElement();
            if (node.isLeaf()) 
                leaves.add(node);
        }

        return leaves;
    }

    public TreeNode getNodeByBreadthOrderIndex(int n) {
        
        int order = 0;
        for (Enumeration e = breadthFirstEnumeration(); e.hasMoreElements();) {
            MathMLTree t = (MathMLTree) e.nextElement(); 
            if (++order == n) 
                return t;
        }
    
        return null;
    }


}
