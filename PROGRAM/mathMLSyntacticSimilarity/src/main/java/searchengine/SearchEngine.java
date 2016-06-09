
package searchengine;

import additional.Pair;
import entities.IdxNode;
import entities.MathMLTree;
import entities.ProductionRule;
import entities.PRwithIdxNodes;
import entities.Sto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class SearchEngine {

    private List<PRwithIdxNodes<String>> corpusIdxTable;
    private List<PRwithIdxNodes<String>> queryIdxTable;
    private List<MathMLTree> corpusTrees;
    private MathMLTree queryTree;
    
    private ArrayList<Sto> StoList;
    
    
    public static enum StoOrder {
        SIMILARITY,
        TREEID;
    }
    
    public ArrayList<Sto> getStoList(StoOrder order) {
        
        switch(order) {
            case SIMILARITY:
                Collections.sort(StoList, new Comparator<Sto>() {
                    @Override
                    public int compare(Sto o1, Sto o2) {
                        if (o1.simValue() < o2.simValue()) return -1;
                        if (o1.simValue() > o2.simValue()) return 1;
                        return 0;
                    }
                });
                break;
            case TREEID:
                Collections.sort(StoList, new Comparator<Sto>() {
                    @Override
                    public int compare(Sto o1, Sto o2) {
                        return o1.tr2.getTreeID() - o2.tr2.getTreeID();
                    }
                });
        }
        Collections.reverse(StoList);
        
        
        return this.StoList;
    }
    
    
    public void printStoList(StoOrder order) {
        if(StoList.isEmpty())
            System.out.println("StoList is empty!");
        
        switch(order) {
            case SIMILARITY:
                Collections.sort(StoList, new Comparator<Sto>() {
                    @Override
                    public int compare(Sto o1, Sto o2) {
                        if (o1.simValue() < o2.simValue()) return -1;
                        if (o1.simValue() > o2.simValue()) return 1;
                        return 0;
                    }
                });
                break;
            case TREEID:
                Collections.sort(StoList, new Comparator<Sto>() {
                    @Override
                    public int compare(Sto o1, Sto o2) {
                        return o1.tr2.getTreeID() - o2.tr2.getTreeID();
                    }
                });
                //do nothing
        }
        
        
        
        
        for(Sto s : StoList) 
            s.print(Sto.ViewStyle.OVERLAYNODES); 
    }
    
    
    
    
    public SearchEngine(MathMLTree quTree) {
        this.corpusIdxTable = new ArrayList<>();
        this.queryIdxTable = new ArrayList<>();
        this.corpusTrees = new ArrayList<>();
        this.queryTree = null;
        this.StoList = new ArrayList<>();
        
        setQueryTree(quTree);
    }
    
    public SearchEngine() {
        this.corpusIdxTable = new ArrayList<>();
        this.queryIdxTable = new ArrayList<>();
        this.corpusTrees = new ArrayList<>();
        this.queryTree = null;
        this.StoList = new ArrayList<>();
    }
    
    
    
    
    public void setQueryTree(MathMLTree quTree) {
        quTree.setTreeIDSameForAllTree(0); // changed!!!
        this.queryTree = quTree;
        addTreeToIndexTable(quTree, queryIdxTable);
    }
    
    // должны добавляться в порядке возврастания индекса дерева!
    public void addCorpusTree(MathMLTree corpTree) {
        
        if(corpTree.getTreeID() != 0)
            corpTree.setTreeIDSameForAllTree(corpusTrees.size() + 1); // changed!!!
        
        corpusTrees.add(corpTree);
        this.addTreeToIndexTable(corpTree, corpusIdxTable);
    }
    
    public MathMLTree getTreeFromCorpusTreesByIndexNode(IdxNode<String> idxNode, List<MathMLTree> cTrs) {
        for(MathMLTree tr : cTrs)
            if(tr.getTreeID() == idxNode.getTreeID())
                return tr;
                 
        return null;
    }
    
    
    private void addTreeToIndexTable(MathMLTree tree, List<PRwithIdxNodes<String>> idxTbl) {
        /*
        Алгоритм:
            1. Проходимся по всем узлам в ширину
            2. Prod Rule текущего узла в таблице:
               если есть  -> 
                    Corp Node текущего узла в списке Corp Node этого PR 
                    если есть  -> не добавляем
                    если нету  -> добавляем
               если нету  -> 
                    а) добавляем PR
                    б) добавляем к PR текущий узел
        */
        
        MathMLTree node;            // текущий узел
        ProductionRule<String> PR;  // текущий PR узла
        IdxNode<String> CN;      // текущий CN узла
        boolean hasPR;              // нашелся ли в таблице индексов PR узла
        
        
        // проходимся по всем элементам дерева в ширину
        for(Enumeration e = tree.breadthFirstEnumeration(); e.hasMoreElements();) {
            
            node = (MathMLTree)e.nextElement();
            PR = new ProductionRule<>(node);
            CN = new IdxNode<>(node);
            hasPR = false;
            
            // проходимся по Index Table
            for (PRwithIdxNodes<String> corpIdx : idxTbl) 
                if(corpIdx.getPR().equals(PR)) {
                    hasPR = true;
                    if(containsIdenticalInCorpusTreeNodes(corpIdx.getIdxNodes(), CN))  
                        break;
                    else {
                        corpIdx.getIdxNodes().add(CN);
                        break;
                    }
                }
            
            // если нету в базе такого PR
            if(!hasPR) {
                PRwithIdxNodes<String> newCorpIdx = new PRwithIdxNodes<>(PR);
                newCorpIdx.add_topCorpusTreeNode(CN);
                idxTbl.add(newCorpIdx);
            }
        } 
        
    }
    
    // получаем списочек Cto
    public Map<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>>
         calculateCtoList(MathMLTree qTr, List<PRwithIdxNodes<String>> qIdxTable, 
                List<MathMLTree> cTrs, List<PRwithIdxNodes<String>> corpIdxTable) {
        
        Map<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>> 
                Cto = new LinkedHashMap<>();
        
        for(PRwithIdxNodes<String> qPRwNodes : qIdxTable) 
            for(PRwithIdxNodes<String> cPRwNodes : corpIdxTable) 
                if(qPRwNodes.getPR().equals(cPRwNodes.getPR())) 
                    for(IdxNode<String> qIdxNode : qPRwNodes.getIdxNodes()) 
                        for(IdxNode<String> cIdxNode : cPRwNodes.getIdxNodes()) 
                            if(qIdxNode.equals(IdxNode.Content.label_leavesCount, cIdxNode)) {

                                MathMLTree cTr = getTreeFromCorpusTreesByIndexNode(cIdxNode, cTrs);
                                Pair topNM = topIndexNode(qIdxNode, qTr, cIdxNode, cTr);

                                
//                                printTopColumn(qPRwNodes.getPR().getStringView(),
//                                        qIdxNode.getStringView(IdxNode.Content.label_repeatID, true),
//                                        cIdxNode.getStringView(IdxNode.Content.label_repeatID, true),
//                                        ((IdxNode)topNM.n).getStringView(IdxNode.Content.label_repeatID, true),
//                                        ((IdxNode)topNM.m).getStringView(IdxNode.Content.label_repeatID, true),
//                                        (qIdxNode.getLeavesCount() == 0));

                                // сколько раз встретился корень дерева
                                int leavesCover = 0; 
                                // сколько раз встретился узел
                                int overlap = 0; 
                                boolean leafCover = (qIdxNode.getLeavesCount() == 0) && 
                                        (((IdxNode)topNM.n).getLeavesCount() != 0);


                                if(Cto.containsKey(topNM)) {
                                    overlap = Cto.get(topNM).n + 1;
                                    if(leafCover)
                                        leavesCover = Cto.get(topNM).m + 1;
                                    else 
                                        leavesCover = Cto.get(topNM).m;
                                } 
                                else {
                                   overlap = 1;
                                   if(leafCover)
                                       leavesCover = 1;
                                   else
                                       leavesCover = 0;
                                }

                                // без проверки того, сколько листьев наложено
                                Cto.put(topNM, new Pair(overlap, leavesCover));  
                            }
        return Cto;
    }
    
         
    // RECURSIVE METHOD!
    public Map<Pair<IdxNode<String>, IdxNode<String>>, Pair<Integer, Integer>> 
        Cto_to_CtoSubtrList(MathMLTree tr0, MathMLTree tr1, 
            IdxNode idxNode1, IdxNode idxNode2,
            Map<Pair<IdxNode<String>, IdxNode<String>>, Pair<Integer, Integer>> CtoSubtrCollector) {
        //System.out.println("RECURSIVE METHOD: Cto_to_CtoSubtrList");
            
        
        // получаем поддеревья, начинающиеся с узлов idxNode1 и idxNode2
        MathMLTree t0 = (MathMLTree) tr0.getNodeByBreadthOrderIndex(idxNode1.getBreadthPosInTree());
        MathMLTree t1 = (MathMLTree) tr1.getNodeByBreadthOrderIndex(idxNode2.getBreadthPosInTree());
        
        // копируем поддеревья
        MathMLTree t0_subtr = MathMLTree.fromMathMLTree(t0);
        MathMLTree t1_subtr = MathMLTree.fromMathMLTree(t1);
        
        // изменяем корни поддеревьев, чтобы Cto в них еще раз не было
        t0_subtr.setLabel("XYZ");
        t1_subtr.setLabel("RTY");
        
        // делаем таблицу индексов для поддеревьев
        List<PRwithIdxNodes<String>> t0SubtrIdxTable = new ArrayList<>();
        List<PRwithIdxNodes<String>> t1SubtrIdxTable = new ArrayList<>();
        addTreeToIndexTable(t0_subtr, t0SubtrIdxTable);
        addTreeToIndexTable(t1_subtr, t1SubtrIdxTable);
        
        // .. для метода
        List<MathMLTree> t1_subtr_list = new ArrayList<>();
        t1_subtr_list.add(t1_subtr);

        
        // получаем список новых Cto
        Map<Pair<IdxNode<String>, IdxNode<String>>, Pair<Integer, Integer>> 
                    CtoList = calculateCtoList(t0_subtr, t0SubtrIdxTable, t1_subtr_list, t1SubtrIdxTable);

        
        for(Iterator<Map.Entry<Pair<IdxNode<String>, IdxNode<String>>, Pair<Integer, Integer>>> 
                it = CtoList.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Pair<IdxNode<String>, IdxNode<String>>, Pair<Integer, Integer>> 
                    Cto = it.next();
            
            if(Cto.getValue().m != Cto.getKey().n.getLeavesCount()) {
                Cto_to_CtoSubtrList(t0_subtr, t1_subtr, Cto.getKey().n, Cto.getKey().m, CtoSubtrCollector);
                it.remove();
            } else {
                CtoSubtrCollector.put(Cto.getKey(), Cto.getValue());
            }            
        }
        
        
        return CtoList;
    }
         
    
        // схожесть подвыражений
    public void calculateStructuralSimilarity() {
        

        Map<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>> CtoList;        
        // Cto (с теми узлами, которые подходят по покрытию листьев)
        Map<Pair<IdxNode<String>,IdxNode<String>>, Integer> Cto_WithooutLeaveInfo = new HashMap<>();

        // key      = pair (number of 1-st tree, 
        //                  number of 2-st tree)
        // value    = pair (overlap, 
        //                  pair (idxNode of 1-st tree,
        //                        idxNode of 2-nd tree)
        Map<Pair<Integer, Integer> ,
            Pair <Integer, Pair<IdxNode<String>,IdxNode<String>>>> StoRoot = new HashMap<>();

         
        // Cto - классическое Cto по алгоритму из статьи
        // -------------------------------------------------------------------------
        CtoList = calculateCtoList(queryTree, queryIdxTable, corpusTrees, corpusIdxTable);
        //System.out.println("------ ------ ----- BEFORE ------ ------ -----");
        //printCtoList(CtoList);
        

        
        // Cto_WithooutLeaveInfo <- Сto
        // -------------------------------------------------------------------------
        for(Map.Entry<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>> entry  
                : CtoList.entrySet())
                Cto_WithooutLeaveInfo.put(entry.getKey(), entry.getValue().n);   
        
        
        
        
        
        // Sto_Subtr - наибольшее по Cto_Subtr
        // -------------------------------------------------------------------------
        for(Map.Entry<Pair<IdxNode<String>,IdxNode<String>>,Integer> entry  
                : Cto_WithooutLeaveInfo.entrySet()) {
            
            int NtrID = entry.getKey().n.getTreeID();
            int MtrID = entry.getKey().m.getTreeID();
            Pair pairTrID = new Pair(NtrID, MtrID);
            
            if(entry.getKey().m.getLabel().equals("math"))    
                StoRoot.put(pairTrID, new Pair(entry.getValue(), entry.getKey()));
        }
        
        
        
        
        // запихиваем результаты в отдельный объект для этого
        // -------------------------------------------------------------------------
        for(Map.Entry<Pair<Integer, Integer>, Pair<Integer, Pair<IdxNode<String>, IdxNode<String>>>> entry  
                : StoRoot.entrySet()) {
            //System.out.println("overlap:::: " + entry.getValue().n);

            Sto sim = new Sto(
                    entities.Sto.Type.STRUCTURAL,
                    queryTree,
                    entry.getValue().m.n,
                    getTreeFromCorpusTreesByIndexNode(entry.getValue().m.m, corpusTrees),
                    entry.getValue().m.m, 
                    entry.getValue().n);
            
            this.StoList.add(sim);
        }
        
        
        addALLMissedStoToStoList(Sto.Type.STRUCTURAL);
        

        
    }
        
    
    
    private void addALLMissedStoToStoList(Sto.Type type) {
        
        
        for(MathMLTree tr : corpusTrees) {
            boolean hasSto = false;
            for(Sto Sto : StoList) 
                if(Sto.tr2.getTreeID() == tr.getTreeID())
                   hasSto = true; 

            if(!hasSto) 
                StoList.add(new Sto(type, queryTree, tr));
        }
    }
    
        
    
    // схожесть подвыражений
    public void calculateSubExprSimilarity() {
        

        Map<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>> CtoList;        
        // Cto (с теми узлами, которые подходят по покрытию листьев)
        Map<Pair<IdxNode<String>,IdxNode<String>>, Integer> Cto_Subtr = new HashMap<>();

        // key      = pair (number of 1-st tree, 
        //                  number of 2-st tree)
        // value    = pair (overlap, 
        //                  pair (idxNode of 1-st tree,
        //                        idxNode of 2-nd tree)
        Map<Pair<Integer, Integer> ,
            Pair <Integer, Pair<IdxNode<String>,IdxNode<String>>>> Sto_Subtr = new HashMap<>();
        
        Map<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>> Cto_subtr_collector = new LinkedHashMap<>();
        
        
        // Cto - классическое Cto по алгоритму из статьи
        // -------------------------------------------------------------------------
        CtoList = calculateCtoList(queryTree, queryIdxTable, corpusTrees, corpusIdxTable);
//        System.out.println("------ ------ ----- BEFORE ------ ------ -----");
//        printCtoList(CtoList);
        
        
        
        //  РЕКУРСИВНО заменяем Cto, которые не покрывают поддерево
        // -------------------------------------------------------------------------
        for(Map.Entry<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>> Cto  
                : CtoList.entrySet()) {
            
            if(Cto.getValue().m != Cto.getKey().n.getLeavesCount()) {
                MathMLTree qTr = queryTree;
                MathMLTree cTr = getTreeFromCorpusTreesByIndexNode(Cto.getKey().m, corpusTrees);
                Cto_to_CtoSubtrList(queryTree, cTr, Cto.getKey().n, Cto.getKey().m, Cto_subtr_collector);
            } else {
                Cto_subtr_collector.put(Cto.getKey(), Cto.getValue());
            }
        }
        
        
        //System.out.println("------ ------ ----- AFTER ------ ------ -----");
        //printCtoList(Cto_subtr_collector);
        
        
        
        
        
        // Cto_Subtr - Сto, которые подходят по покрытию (тут должны подходить все) 
        // -------------------------------------------------------------------------
        for(Map.Entry<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>> entry  
                : Cto_subtr_collector.entrySet())
            if(entry.getValue().m == entry.getKey().n.getLeavesCount())
                Cto_Subtr.put(entry.getKey(), entry.getValue().n);   
            
        //printCto_SubtrList(Cto_Subtr);
        
        
        
        
        // Sto_Subtr - наибольшее по Cto_Subtr
        // -------------------------------------------------------------------------
        for(Map.Entry<Pair<IdxNode<String>,IdxNode<String>>,Integer> entry  
                : Cto_Subtr.entrySet()) {
            
            int NtrID = entry.getKey().n.getTreeID();
            int MtrID = entry.getKey().m.getTreeID();
            Pair pairTrID = new Pair(NtrID, MtrID);
            int overlap = entry.getValue();
            
            
            if(Sto_Subtr.containsKey(pairTrID)) {
                if(Sto_Subtr.get(pairTrID).n < overlap)
                    Sto_Subtr.put(pairTrID, new Pair(overlap, entry.getKey()));
            } else {
                Sto_Subtr.put(pairTrID, new Pair(overlap, entry.getKey()));
            }  
        }
        
        
        // запихиваем результаты в отдельный объект для этого
        // -------------------------------------------------------------------------
        for(Map.Entry<Pair<Integer, Integer>, Pair<Integer, Pair<IdxNode<String>, IdxNode<String>>>> entry  
                : Sto_Subtr.entrySet()) {
            //System.out.println("overlap:::: " + entry.getValue().n);

            Sto sim = new Sto(
                    entities.Sto.Type.SUBEXPRESSION,
                    queryTree,
                    entry.getValue().m.n,
                    getTreeFromCorpusTreesByIndexNode(entry.getValue().m.m, corpusTrees),
                    entry.getValue().m.m, 
                    entry.getValue().n);
            
            this.StoList.add(sim);
        }
    }
    
    
    
    
    
    private void printTopColumn(String PR, String n, String m, String topN, String topM, boolean getLC) {
        
        System.out.print(PR);
        System.out.print("  : (");
        System.out.print(n);
        System.out.print(",");
        System.out.print(m);
        System.out.print(")");

        System.out.print("  top(");
        System.out.print(topN);
        System.out.print(",");
        System.out.print(topM);
        System.out.print(")");
        System.out.println(" (" + getLC + ")");
    }
    
    
        
    
    private void printCto_SubtrList(Map<Pair<IdxNode<String>,IdxNode<String>>, Integer> Cto) {
        
        System.out.println("CtoList_Subtr: ");
        for(Map.Entry<Pair<IdxNode<String>,IdxNode<String>>, Integer> entry  : Cto.entrySet()) {
            System.out.print("(");
            System.out.print(entry.getKey().n.getStringView(IdxNode.Content.label_treeID_repeatID_leavesCount_breadthPosInTree, true));
            System.out.print(",");
            System.out.print(entry.getKey().m.getStringView(IdxNode.Content.label_treeID_repeatID_leavesCount_breadthPosInTree, true));
            System.out.print(") ");
            System.out.println("overlap: " + entry.getValue());
        }
    }
    
    
    private void printCtoList(Map<Pair<IdxNode<String>,IdxNode<String>>, 
            Pair<Integer, Integer>> preCto) {
        
            System.out.println("CtoList: ");
        
            for(Map.Entry<Pair<IdxNode<String>,IdxNode<String>>, Pair<Integer, Integer>> entry  
                    : preCto.entrySet()) {
            System.out.print("(");
            System.out.print(entry.getKey().n.getStringView(IdxNode.Content.label_treeID_repeatID_leavesCount_breadthPosInTree, true));
            System.out.print(",");
            System.out.print(entry.getKey().m.getStringView(IdxNode.Content.label_treeID_repeatID_leavesCount_breadthPosInTree, true));
            System.out.print(") ");
            System.out.print("overlap: " + entry.getValue().n);
            System.out.print(", leavesCover: " + entry.getValue().m);
            System.out.println(" / " + entry.getKey().n.getLeavesCount());
        }
    }
    
    
    
    // n - узел дерева tr_n
    // m - узел дерева tr_m
    public Pair<IdxNode<String>,IdxNode<String>> 
         topIndexNode(IdxNode<String> n, MathMLTree tr_n, 
             IdxNode<String> m, MathMLTree tr_m) {
        
        if(!n.equals(IdxNode.Content.label, m)) {
            System.err.println("Имена начальных узлов не совпадают!");
            return null;
        }
            
        MathMLTree N = (MathMLTree) tr_n.getNodeByBreadthOrderIndex(n.getBreadthPosInTree());
        MathMLTree M = (MathMLTree) tr_m.getNodeByBreadthOrderIndex(m.getBreadthPosInTree());
        
        
        if(!N.getLabel().equals(n.getLabel())) 
            System.err.println("Имена index node и узла в дереве на совпадают!");
        if(!M.getLabel().equals(m.getLabel()))
            System.err.println("Имена index node и узла в дереве на совпадают!");
        
        
        if(N.isRoot() || M.isRoot())
            return new Pair(n,m);
            
        
        Pair<MathMLTree, MathMLTree> NM     = new Pair<>(N, M);
        Pair<MathMLTree, MathMLTree> newNM  = top(N, M);
                

        
        while(!NM.equals(newNM)) {
            NM = newNM;
            newNM = top(NM.n, NM.m);
        }
        
        
        Pair p = new Pair<>(new IdxNode(NM.n),
                            new IdxNode(NM.m));
        
        return p;
    }
    
         
    private Pair<MathMLTree, MathMLTree> top(MathMLTree t1, MathMLTree t2) {
        
        // если названия t1 и t2 не совпадают изначально
        if(!t1.getLabel().equals(t2.getLabel())) {
            System.err.println("Имена не совпали!");
            return null;
        }
        
        // если они уже руты
        if(t1.isRoot() || t2.isRoot()) {
            return new Pair(t1, t2);
        }
            
        String pT1 = ((MathMLTree)t1.getParent()).getLabel();
        String pT2 = ((MathMLTree)t2.getParent()).getLabel(); 
        
        // если совпадают имена предыдущего узла и порядок среди братьев и систер
        if(pT1.equals(pT2))
            if(t1.getSiblingsNumber() == t2.getSiblingsNumber())
                return new Pair((MathMLTree)t1.getParent(), 
                                (MathMLTree)t2.getParent());
        
        return new Pair(t1, t2);
    }
     
    
    private boolean containsIdenticalInCorpusTreeNodes(List<IdxNode<String>> idxNodelist,
            IdxNode<String> CN) {
        
        for(IdxNode<String> idxNode : idxNodelist)
            if(idxNode.equals(CN))
                return true;
        
        return false;
    }
    
    
    private String getIndexTableStringView(List<PRwithIdxNodes<String>> idxTbl) {
        
        StringBuilder sb = new StringBuilder();
        
        for(PRwithIdxNodes<String> cIdx : idxTbl)
            sb.append(cIdx.getStringView()).append("\n");
        
        return sb.toString();
    }
    
    private String getCorpusIndexTableStringView() {
        return getIndexTableStringView(this.corpusIdxTable);
    }
    
    private String getQueryIndexTableStringView() {
        return getIndexTableStringView(this.queryIdxTable);
    }
    
    
    public void printCorpusIndexTable() {
        
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCorpusIndexTableStringView()).
                append("PR count: ").append(corpusIdxTable.size());
        
        System.out.println(sb.toString());
    }
    
    public void printQueryIndexTable() {        
        StringBuilder sb = new StringBuilder();
        sb.append(this.getQueryIndexTableStringView()).
                append("PR count: ").append(queryIdxTable.size());
        
        System.out.println(sb.toString());
    }
    
    
    
    public int getNumberOfCommonNodesByNumCorpTree(int num) {
        
        for(Sto sto: StoList)
            if(sto.tr2.getTreeID() == num) 
                return sto.numberCommonNodes();

                
        System.out.println("Схожести с деревом Т" + num + " не встретилось");
        return 0;
    }

    
}
