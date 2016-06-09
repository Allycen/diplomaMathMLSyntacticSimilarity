package entities;

import additional.ConsoleStrings;
import java.util.ArrayList;
import java.util.List;


public class PRwithIdxNodes<T> {
    
    private final ProductionRule<T> PR;
    private List<IdxNode<T>> idxNodes;
    
    
    public PRwithIdxNodes(ProductionRule<T> PR) {
        this.PR = PR;
        this.idxNodes = new ArrayList<>();
    }
    
    public void add_topCorpusTreeNode(IdxNode<T> node) {
        this.idxNodes.add(node);
    }
    
    public String getStringView() {
        StringBuilder sb = new StringBuilder();
              
        sb.append(PR.getStringView());
        
        if(!idxNodes.isEmpty()) {
            sb.append(ConsoleStrings.DSPACE).
                append(ConsoleStrings.PR_TOPNODES_DELIMITER).append(ConsoleStrings.DSPACE);
            for(IdxNode<T> node : idxNodes)
                sb.append(node.getStringView(IdxNode.Content.label_treeID_repeatID_leavesCount_breadthPosInTree,true)).
                        append(ConsoleStrings.DSPACE);
        }
     
        return sb.toString();
    }
    
    public void print() {
        System.out.println(getStringView());
    }

    public ProductionRule<T> getPR() {
        return this.PR;
    }
    
    public List<IdxNode<T>> getIdxNodes() {
        return this.idxNodes;
    }ы
}
