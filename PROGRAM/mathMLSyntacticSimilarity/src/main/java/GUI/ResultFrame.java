/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import entities.MathExpression;
import static GUI.consoleEntry.PATH_TO_MATHML_DATABASE;
import entities.Sto;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.jeuclid.MathMLParserSupport;
import net.sourceforge.jeuclid.app.mathviewer.FileIO;
import net.sourceforge.jeuclid.app.mathviewer.Messages;
import net.sourceforge.jeuclid.swing.JMathComponent;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import searchengine.SearchEngine;



public class ResultFrame extends javax.swing.JFrame { 
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelNoShow;
    private javax.swing.JLabel jLblNotShownExpr;
    private javax.swing.JLabel jLblQuery_fileName;
    private javax.swing.JLabel jLblResult1_fileName;
    private javax.swing.JLabel jLblResult1_nodes;
    private javax.swing.JLabel jLblResult1_rank;
    private javax.swing.JLabel jLblResult1_sim;
    private javax.swing.JLabel jLblResult2_fileName;
    private javax.swing.JLabel jLblResult2_nodes;
    private javax.swing.JLabel jLblResult2_rank;
    private javax.swing.JLabel jLblResult2_sim;
    private javax.swing.JLabel jLblResult3_fileName;
    private javax.swing.JLabel jLblResult3_nodes;
    private javax.swing.JLabel jLblResult3_rank;
    private javax.swing.JLabel jLblResult3_sim;
    private javax.swing.JLabel jLblResult4_fileName;
    private javax.swing.JLabel jLblResult4_nodes;
    private javax.swing.JLabel jLblResult4_rank;
    private javax.swing.JLabel jLblResult4_sim;
    private javax.swing.JLabel jLblResult5_fileName;
    private javax.swing.JLabel jLblResult5_nodes;
    private javax.swing.JLabel jLblResult5_rank;
    private javax.swing.JLabel jLblResult5_sim;
    private javax.swing.JLabel jLblSimType;
    private javax.swing.JScrollPane jPanelMathQuery;
    private javax.swing.JScrollPane jPanelMathResult1;
    private javax.swing.JScrollPane jPanelMathResult2;
    private javax.swing.JScrollPane jPanelMathResult3;
    private javax.swing.JScrollPane jPanelMathResult4;
    private javax.swing.JScrollPane jPanelMathResult5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables

    



    public ResultFrame(List<MathExpression> mathExprList, SearchEngine searchEngine) {
        initComponents();
        this.setLocationRelativeTo(null);
        
        
        List<Sto> stoList = searchEngine.getStoList(SearchEngine.StoOrder.SIMILARITY);
        
        
        for(MathExpression expr : mathExprList) {
            if(expr.getMathMLTree().getTreeID() == 0) {
                this.jPanelMathQuery.setViewportView(expr.getJMathComponent());
                jLblQuery_fileName.setText(expr.fileName);
            }
                   
            if(expr.getMathMLTree().getTreeID() == stoList.get(0).tr2.getTreeID()) {
                this.jPanelMathResult1.setViewportView(expr.getJMathComponent());
                jLblResult1_fileName.setText(expr.fileName);
            }
            
            if(expr.getMathMLTree().getTreeID() == stoList.get(1).tr2.getTreeID()) {
                this.jPanelMathResult2.setViewportView(expr.getJMathComponent());   
                jLblResult2_fileName.setText(expr.fileName);
            }
                            
            if(expr.getMathMLTree().getTreeID() == stoList.get(2).tr2.getTreeID()) {
                this.jPanelMathResult3.setViewportView(expr.getJMathComponent());
                jLblResult3_fileName.setText(expr.fileName);
            }
            
            if(expr.getMathMLTree().getTreeID() == stoList.get(3).tr2.getTreeID()) {
                this.jPanelMathResult4.setViewportView(expr.getJMathComponent());
                jLblResult4_fileName.setText(expr.fileName);
            }   
            
            if(expr.getMathMLTree().getTreeID() == stoList.get(4).tr2.getTreeID()) {
                this.jPanelMathResult5.setViewportView(expr.getJMathComponent());
                jLblResult5_fileName.setText(expr.fileName);
            }
        }
        
        switch(stoList.get(0).type) {
            case STRUCTURAL:
                jLblSimType.setText(SettingsFrame.STRUCTSIM);
                break;
            case SUBEXPRESSION:
                jLblSimType.setText(SettingsFrame.SUBEXPRSIM);
                break;
        }
        
        jLblResult1_nodes.setText(stoList.get(0).simNodesString());
        jLblResult2_nodes.setText(stoList.get(1).simNodesString());
        jLblResult3_nodes.setText(stoList.get(2).simNodesString());
        jLblResult4_nodes.setText(stoList.get(3).simNodesString());
        jLblResult5_nodes.setText(stoList.get(4).simNodesString());
        
        jLblResult1_sim.setText(stoList.get(0).simValueString());
        jLblResult2_sim.setText(stoList.get(1).simValueString());
        jLblResult3_sim.setText(stoList.get(2).simValueString());
        jLblResult4_sim.setText(stoList.get(3).simValueString());
        jLblResult5_sim.setText(stoList.get(4).simValueString());
        
      
        
        int haveSimilarity = 0;
        for(Sto sto : stoList) 
            if(sto.simValue() > 0) 
                haveSimilarity ++;
        
        
        switch(haveSimilarity) {
            case 1:
                setVisible2row(false);
            case 2:
                setVisible3row(false);
            case 3:
                setVisible4row(false);
            case 4:
                setVisible5row(false);
            case 5:
                jLblNotShownExpr.setVisible(false);
                jLabelNoShow.setVisible(false);
        }
        
        
        jLblNotShownExpr.setText(String.valueOf(haveSimilarity - 5));
        
    }

    
    private void setVisible2row(boolean isVisible) {
        jLblResult2_nodes.setVisible(isVisible);
        jLblResult2_sim.setVisible(isVisible);
        jLblResult2_fileName.setVisible(isVisible);
        jPanelMathResult2.setVisible(isVisible);
        jLblResult2_rank.setVisible(isVisible);
    }
    
    private void setVisible3row(boolean isVisible) {
        jLblResult3_nodes.setVisible(isVisible);
        jLblResult3_sim.setVisible(isVisible);
        jLblResult3_fileName.setVisible(isVisible);
        jPanelMathResult3.setVisible(isVisible);
        jLblResult3_rank.setVisible(isVisible);
    }
    
    private void setVisible4row(boolean isVisible) {
        jLblResult4_nodes.setVisible(isVisible);
        jLblResult4_sim.setVisible(isVisible);
        jLblResult4_fileName.setVisible(isVisible);
        jPanelMathResult4.setVisible(isVisible);
        jLblResult4_rank.setVisible(isVisible);
    }
    
    private void setVisible5row(boolean isVisible) {
        jLblResult5_nodes.setVisible(isVisible);
        jLblResult5_sim.setVisible(isVisible);
        jLblResult5_fileName.setVisible(isVisible);
        jPanelMathResult5.setVisible(isVisible);
        jLblResult5_rank.setVisible(isVisible);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLblSimType = new javax.swing.JLabel();
        jPanelMathQuery = new javax.swing.JScrollPane();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanelMathResult1 = new javax.swing.JScrollPane();
        jPanelMathResult3 = new javax.swing.JScrollPane();
        jPanelMathResult2 = new javax.swing.JScrollPane();
        jPanelMathResult5 = new javax.swing.JScrollPane();
        jPanelMathResult4 = new javax.swing.JScrollPane();
        jLblResult5_sim = new javax.swing.JLabel();
        jLblResult4_rank = new javax.swing.JLabel();
        jLblResult3_rank = new javax.swing.JLabel();
        jLblResult2_nodes = new javax.swing.JLabel();
        jLblResult1_rank = new javax.swing.JLabel();
        jLblResult2_rank = new javax.swing.JLabel();
        jLblResult3_nodes = new javax.swing.JLabel();
        jLblResult4_nodes = new javax.swing.JLabel();
        jLblResult5_rank = new javax.swing.JLabel();
        jLblResult1_nodes = new javax.swing.JLabel();
        jLblResult2_sim = new javax.swing.JLabel();
        jLblResult3_sim = new javax.swing.JLabel();
        jLblResult4_sim = new javax.swing.JLabel();
        jLblResult5_nodes = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLblResult1_sim = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabelNoShow = new javax.swing.JLabel();
        jBtnBack = new javax.swing.JButton();
        jLblResult1_fileName = new javax.swing.JLabel();
        jLblResult2_fileName = new javax.swing.JLabel();
        jLblResult3_fileName = new javax.swing.JLabel();
        jLblResult4_fileName = new javax.swing.JLabel();
        jLblResult5_fileName = new javax.swing.JLabel();
        jLblNotShownExpr = new javax.swing.JLabel();
        jLblQuery_fileName = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel12.setText("n-Similarity type:");

        jLabel14.setText("Query:");

        jLblSimType.setText("subexpression");

        jLabel1.setText("rank");

        jLabel2.setText("result");

        jLabel3.setText("common nodes/all nodes ");

        jLblResult5_sim.setText("0.00");

        jLblResult4_rank.setText("4");

        jLblResult3_rank.setText("3");

        jLblResult2_nodes.setText("0 / 0");

        jLblResult1_rank.setText("1");

        jLblResult2_rank.setText("2");

        jLblResult3_nodes.setText("0 / 0");

        jLblResult4_nodes.setText("0 / 0");

        jLblResult5_rank.setText("5");

        jLblResult1_nodes.setText("0 / 0");

        jLblResult2_sim.setText("0.00");

        jLblResult3_sim.setText("0.00");

        jLblResult4_sim.setText("0.00");

        jLblResult5_nodes.setText("0 / 0");

        jLabel11.setText("similarity");

        jLblResult1_sim.setText("0.00");

        jLabelNoShow.setText("Not shown but suitable expressions:");

        jBtnBack.setText("back");
        jBtnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnBackActionPerformed(evt);
            }
        });

        jLblResult1_fileName.setText("name 1");

        jLblResult2_fileName.setText("name 2");

        jLblResult3_fileName.setText("name 3");

        jLblResult4_fileName.setText("name 4");

        jLblResult5_fileName.setText("name 5");

        jLblNotShownExpr.setText("0");

        jLblQuery_fileName.setText("name query");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jLabel14)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLblQuery_fileName)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelMathQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jLabel12))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(108, 108, 108)
                                .addComponent(jLblSimType)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLblResult3_rank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblResult1_rank, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLblResult2_rank, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLblResult3_fileName)
                                            .addComponent(jPanelMathResult3, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanelMathResult2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLblResult2_fileName)
                                            .addComponent(jPanelMathResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLblResult1_fileName))
                                        .addGap(65, 65, 65)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLblResult3_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLblResult3_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLblResult2_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLblResult1_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jLblResult1_sim, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLblResult2_sim, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(118, 118, 118)
                                        .addComponent(jLabel2)
                                        .addGap(108, 108, 108)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                                        .addComponent(jLabel11))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLblResult5_rank, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLblResult4_rank, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLblResult4_fileName)
                                        .addGap(4, 4, 4))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLblResult5_fileName)
                                            .addComponent(jPanelMathResult5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jPanelMathResult4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(65, 65, 65)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLblResult4_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLblResult4_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLblResult5_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLblResult5_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelNoShow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLblNotShownExpr, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jBtnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                            .addComponent(jSeparator2))))
                .addGap(0, 58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLblQuery_fileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelMathQuery, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLblSimType)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(33, 33, 33)
                .addComponent(jLblResult1_fileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMathResult1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLblResult1_rank, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblResult1_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLblResult1_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLblResult2_fileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMathResult2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblResult2_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLblResult2_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLblResult2_rank, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLblResult3_fileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMathResult3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLblResult3_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLblResult3_sim))
                    .addComponent(jLblResult3_rank, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLblResult4_fileName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLblResult4_rank, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblResult4_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblResult4_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLblResult5_nodes, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLblResult5_sim, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanelMathResult4, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLblResult5_fileName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLblResult5_rank, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelMathResult5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(91, 91, 91)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNoShow)
                    .addComponent(jBtnBack)
                    .addComponent(jLblNotShownExpr))
                .addGap(13, 13, 13))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnBackActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        try {
            new SettingsFrame().setVisible(true);
        } catch (SAXException ex) {
            Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ResultFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnBackActionPerformed



    
        

    
    






}
