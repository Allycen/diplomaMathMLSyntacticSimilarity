package entities;

import GUI.ResultFrame;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jeuclid.DOMBuilder;
import net.sourceforge.jeuclid.MathMLParserSupport;
import net.sourceforge.jeuclid.app.mathviewer.Messages;
import net.sourceforge.jeuclid.layout.LayoutableDocument;
import net.sourceforge.jeuclid.layout.LayoutableNode;
import net.sourceforge.jeuclid.swing.JMathComponent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class MathExpression {
 
    
    // логгирование ошибок
    private static final Log LOGGER = LogFactory.getLog(MathExpression.class);
    

    // для отображении выражения в swing
    private final JMathComponent jMathComponent;
    
    
    // структурное дерево
    private final MathMLTree mathMLTree;
    
    
    // название файла / ресурса, откуда получено выражение
    public final String fileName;
    
    
    
    public MathExpression(String path, String fileName) throws SAXException, IOException {
        
        File f = new File(path, fileName);
        Document domDocument = MathMLParserSupport.parseFile(f);
        
        
        this.fileName = f.getName();
        this.jMathComponent = new JMathComponent();
        this.jMathComponent.setDocument(domDocument);
        this.mathMLTree = MathMLTree.fromNode(domDocument.getDocumentElement());
        
        
        this.mathMLTree.normilizeTree();
    }

    public MathExpression(File f) throws SAXException, IOException {
        Document domDocument = MathMLParserSupport.parseFile(f);
        
        this.fileName = f.getName();
        this.jMathComponent = new JMathComponent();
        this.jMathComponent.setDocument(domDocument);
        this.mathMLTree = MathMLTree.fromNode(domDocument.getDocumentElement());
        
        this.mathMLTree.normilizeTree();
    }
    
    public JMathComponent getJMathComponent() {
        return this.jMathComponent;
    }  
    
    
    public void printTreeStructure() {
        mathMLTree.prettyPrint();
    }

    public MathMLTree getMathMLTree() {
        return mathMLTree;
    }
    
    
}
