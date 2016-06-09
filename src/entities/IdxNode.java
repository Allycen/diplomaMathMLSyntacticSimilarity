package entities;

import additional.ConsoleStrings;
import java.util.Objects;


public class IdxNode<T> {
    
    private final T label;
    
    // идентификатор дерева, в котором нахожится этот узел
    private final int treeID;
    
    // каким по счету этот узел встретился в дереве
    private final int repeatID;
    
    // сколько листьев у поддерева, начинающегося с этого узла
    private final int leavesCount;
    
    // позиция в дереве с индексом treeID (в ширину)
    private final int breadthPosInTree;
    
    // те элементы, которые будут учитываться при сравнении и выводе на экран
    public static enum Content {
        label,
        label_repeatID,
        label_treeID_repeatID,
        label_treeID_repeatID_leavesCount,
        label_treeID_repeatID_leavesCount_breadthPosInTree,
        label_leavesCount;
    }
    
    public IdxNode(T label, int treeID, int repeatID, int leavesCount, int breadthPosInTree) {
        this.label = label;
        this.treeID = treeID;
        this.repeatID = repeatID;
        this.leavesCount = leavesCount;
        this.breadthPosInTree = breadthPosInTree;
    }
    
    public IdxNode(MathMLTree node) {
        
        this.label = (T) node.getLabel();
        
        if(!node.isRoot())
            this.treeID = ((MathMLTree)node.getRoot()).getTreeID();
        else
            this.treeID = node.getTreeID();
        
        
        if(node.isLeaf())
            this.leavesCount = 0;
        else
            this.leavesCount = node.getLeafCount();
        
        
        this.repeatID = node.getLabelIdxTime();
        this.breadthPosInTree = node.getNodeBreadthOrder();
    } 
    
    public IdxNode(IdxNode idxNode, int offsetBreadthOrder) {
        this.label = (T) idxNode.getLabel();
        this.treeID = idxNode.getTreeID();
        this.leavesCount = idxNode.getLeavesCount();
        this.repeatID = idxNode.repeatID;
        this.breadthPosInTree = idxNode.getBreadthPosInTree() + offsetBreadthOrder;
    }
    
    public T getLabel() {
        return this.label;
    }
            
    public int getTreeID() {
        return this.treeID;
    }
    
    public int getRepeatID() {
        return this.repeatID;
    }
    
    public int getLeavesCount() {
        return this.leavesCount;
    }
    
    public int getBreadthPosInTree() {
        return this.breadthPosInTree;
    }
    
    // узлы равны, если (все совпадает)
    @Override
    public boolean equals(Object obj) {
        
        if(obj == this) 
            return true;
        
        if(obj == null) 
            return false;
        
        if (obj instanceof IdxNode<?>)
            if(((IdxNode<?>)obj).label.equals(this.label) && 
                    ((IdxNode<?>)obj).leavesCount == this.leavesCount &&
                    ((IdxNode<?>)obj).repeatID == this.repeatID &&
                    ((IdxNode<?>)obj).treeID == this.treeID &&
                    ((IdxNode<?>)obj).breadthPosInTree == this.breadthPosInTree)
                return true;
        
        return false;
    }
    
    public boolean equals(Content content, Object obj) {
        if(obj == this) 
            return true;
        
        if(obj == null) 
            return false;
        
        
        if (obj instanceof IdxNode<?>)
            switch(content) {
            case label:
                if(((IdxNode<?>)obj).label.equals(this.label))
                    return true;
                
                
            case label_treeID_repeatID:
                if(((IdxNode<?>)obj).label.equals(this.label) && 
                   ((IdxNode<?>)obj).treeID == this.treeID &&
                   ((IdxNode<?>)obj).repeatID == this.repeatID)
                return true;
                
                
            case label_treeID_repeatID_leavesCount:
                if(((IdxNode<?>)obj).label.equals(this.label) && 
                   ((IdxNode<?>)obj).treeID == this.treeID &&
                   ((IdxNode<?>)obj).repeatID == this.repeatID &&
                   ((IdxNode<?>)obj).leavesCount == this.leavesCount)
                return true;
                
                
            case label_leavesCount:
                if(((IdxNode<?>)obj).label.equals(this.label) && 
                   ((IdxNode<?>)obj).leavesCount == this.leavesCount)
                return true;
        }
        
         
        return false;
    }
    
    // param - inColor -> выводить в цвете или нет
    public String getStringView(Content viewstyle, boolean isInColor) {
        StringBuilder sb = new StringBuilder();
           
        if(isInColor)
            switch(this.treeID % 4) {
                case 1:
                    sb.append(ConsoleStrings.ANSI_RED);
                    break;
                case 2:
                    sb.append(ConsoleStrings.ANSI_BLUE);
                    break;
                case 3: 
                    sb.append(ConsoleStrings.ANSI_PURPLE);
                    break;
                default:
                    sb.append(ConsoleStrings.ANSI_BLACK);
            }
            
        
        sb.append(this.label.toString());
        
        switch(viewstyle) {
            
            case label_repeatID:
                sb.append(ConsoleStrings.LOWIDX).append(this.repeatID);
                break;
            
            case label_treeID_repeatID:
                sb.append(ConsoleStrings.UPPERIDX).append(this.treeID);
                break;
                
                
            case label_treeID_repeatID_leavesCount:
                sb.append(ConsoleStrings.SPACE).
                append("(").append(this.leavesCount).append(")");
                break;
                
                
            case label_treeID_repeatID_leavesCount_breadthPosInTree:
                sb.append(ConsoleStrings.SPACE).
                append("(").append(this.leavesCount).append(")").append(" (pos:").
                        append(this.breadthPosInTree).append(")");
        }
                
        if(isInColor) 
            sb.append(ConsoleStrings.ANSI_RESET); 
        
        return sb.toString();
    }
    
    public void print(Content viewStyle, boolean isInColor) {
        System.out.println(getStringView(viewStyle, isInColor));
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.label);
        return hash;
    }
}
