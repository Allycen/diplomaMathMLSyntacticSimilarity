package entities;

import java.text.DecimalFormat;


public class Sto {

    public static enum Type {
        SUBEXPRESSION, 
        STRUCTURAL, 
        IRREGULAR;
    }
    
    public final Type type;
    public final MathMLTree tr1;
    public final MathMLTree tr2;
    public final MathMLTree commonPart;

    // additional information
    private final IdxNode<String> overlayIdxNode1;
    private final IdxNode<String> overlayIdxNode2;
    private final double numberCommonNodes;
    private final double numberAllNodes;
    
    
    
    public Sto(Type type, 
        MathMLTree tr1, IdxNode<String> overlayIdxNode1,
        MathMLTree tr2, IdxNode<String> overlayIdxNode2,
        int overlapCount) {
        
        
        this.type = type;
        this.tr1 = tr1;
        this.tr2 = tr2;
        this.commonPart = null;
        
        // additional
        
        this.overlayIdxNode1 = overlayIdxNode1;
        this.overlayIdxNode2 = overlayIdxNode2;
        switch(type) {
            case SUBEXPRESSION:
                this.numberCommonNodes = overlapCount * 2;
                break;
            default:
                this.numberCommonNodes = overlapCount * 2;
        }
        
        this.numberAllNodes = tr1.getNodeCount() + tr2.getNodeCount();   
    }
    
    public Sto(Type type, MathMLTree tr1, MathMLTree tr2) {
        
        
        this.type = type;
        this.tr1 = tr1;
        this.tr2 = tr2;
        this.commonPart = null;
        
        // additional
        this.overlayIdxNode1 = null;
        this.overlayIdxNode2 = null;
        this.numberCommonNodes = 0;
        this.numberAllNodes = tr1.getNodeCount() + tr2.getNodeCount();   
    }
    
    
    public String simValueString() {
        DecimalFormat formatter = new DecimalFormat("#0.000");
        
        return formatter.format(this.simValue());
    }
    
    public String simNodesString() {
        
        return ((int)numberCommonNodes + " / " + (int)numberAllNodes);
    }
    
    public double simValue() {  
        double Sim = numberCommonNodes / numberAllNodes;
        return Sim;
    }
    
    public int numberCommonNodes() {
        return (int) this.numberCommonNodes;
    }
    
    
    public static enum ViewStyle {
        OVERLAYNODES,
        COMMONTREEPART;
    }
    
    public String getStringView(ViewStyle style) {
        
        StringBuilder sb = new StringBuilder();
        
        switch(style) {
            case OVERLAYNODES:
                sb.append("Sto(T").
                   append(tr1.getTreeID()).append(",T").
                   append(tr2.getTreeID()).append(") = ").
                   append(simNodesString());
                
                if (overlayIdxNode1 != null && overlayIdxNode2 != null && type == Type.SUBEXPRESSION) 
                   sb.append(" (").append(overlayIdxNode1.getStringView(IdxNode.Content.label, true)).append(",").
                   append(overlayIdxNode2.getStringView(IdxNode.Content.label, true)).append(")");
                
                break;
        }
        
        return sb.toString();
    }
    
    
    public void print(ViewStyle style) {
        System.out.println(getStringView(style));
    }
}


