
package entities;



public enum MathMLPresentationMarkupElement implements MathMLElement {
    
    MATH        ("math",        ElementType.ENTRY, true),
    
    MI          ("mi",          ElementType.TOKEN, true),
    MN          ("mn",          ElementType.TOKEN, true),
    MO          ("mo",          ElementType.TOKEN, true),
    MTEXT       ("mtext",       ElementType.TOKEN, true),
    MSPACE      ("mspace",      ElementType.TOKEN, true),
    MS          ("ms",          ElementType.TOKEN, true),
    MGLYPH      ("mglyph",      ElementType.TOKEN, true),
    
    
    MROW        ("mrow",        ElementType.GENERAL, true),
    MFRAC       ("mfrac",       ElementType.GENERAL, true),
    MSQRT       ("msqrt",       ElementType.GENERAL, true),
    MROOT       ("mroot",       ElementType.GENERAL, true),
    MSTYLE      ("mstyle",      ElementType.GENERAL, false),
    MERROR      ("merror",      ElementType.GENERAL, false),
    MPADDED     ("mpadded",     ElementType.GENERAL, false),
    MPHANTOM    ("mphantom",    ElementType.GENERAL, false),
    MFENCED     ("mfenced",     ElementType.GENERAL, true),
    MENCLOSE    ("menclose",    ElementType.GENERAL, false),
    
    
    MSUB        ("msub",        ElementType.SCRIPT, true),
    MSUP        ("msup",        ElementType.SCRIPT, true),
    MSUBSUP     ("msubsup",     ElementType.SCRIPT, true),
    MUNDER      ("munder",      ElementType.SCRIPT, true),
    MOVER       ("mover",       ElementType.SCRIPT, true),
    MUNDEROVER  ("munderover",  ElementType.SCRIPT, true),
    MMULTISCRIPTS   
              ("mmultiscripts", ElementType.SCRIPT, true),
    
    
    MTABLE      ("mtable",      ElementType.TABLE, true),
    MLABELEDTR  ("mlabeledtr",  ElementType.TABLE, true),
    MTR         ("mtr",         ElementType.TABLE, true),
    MTD         ("mtd",         ElementType.TABLE, true),
    MALIGNGROUP ("maligngroup", ElementType.TABLE, true),
    MALIGNMARK  ("malignmark",  ElementType.TABLE, true),
    
    MACTION     ("maction",     ElementType.ACTION, false);
    
    
    
    
    
    public static enum ElementType {
        ENTRY, TOKEN, GENERAL, SCRIPT, TABLE, ACTION;
    }
    
    private final String XMLTag;
    private final ElementType type;
    private final boolean isSyntacticInfluential;
    
    
    MathMLPresentationMarkupElement(String XMLTag, ElementType type, 
        boolean isSyntacticInfluential) {
        this.XMLTag = XMLTag;
        this.type = type;
        this.isSyntacticInfluential = isSyntacticInfluential;
    }
    
    
    
    public String XMLTag() {
       return this.XMLTag;
    }
    
    public ElementType type() {
        return this.type;
    }
    
    public boolean isSyntacticInfluential() {
        return this.isSyntacticInfluential;
    }
    
    
    public static boolean contains(String str) {

        for (MathMLPresentationMarkupElement e : 
                MathMLPresentationMarkupElement.values()) 
            if (e.XMLTag.equals(str)) 
                return true;

        return false;
    }
    
    
    public static boolean isToken(String str) {

        for (MathMLPresentationMarkupElement e : 
                MathMLPresentationMarkupElement.values()) 
            if (e.XMLTag.equals(str) && e.type.equals(ElementType.TOKEN)) 
                return true;

        return false;
    }
    
    
    
}
