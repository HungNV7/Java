package sample.UIs;

import sample.utils.KeyWord;
import sample.events.ButtonEvent;
import sample.clients.ClientManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class GameUI extends javax.swing.JFrame {
    private ClientManager mng;
    private int[][] arr;
    private JLabel[][] listLb;
    private int roomID;
    private int size;
    private int positionX;
    private int positionY;
    private String tmp="";
        
    
    /**
     * Creates new form GameUI
     * @param mng
     * @param roomID
     * @param size
     */
    public GameUI(ClientManager mng, int roomID, int size) {
        initComponents();   //loi o day
        this.mng=mng;
        this.size=size;
        drawGame(); //ham draw co van de roi       
        this.roomID=roomID;
        
        arr=new int[size][size];
        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                arr[i][j]=-1;
            }
        }
    }
    
    public void drawGame(){
        listLb=new JLabel[size][size];
        pGameArea.removeAll();
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                listLb[i][j]=new JLabel(" ");
                pGameArea.add(listLb[i][j]);
                listLb[i][j].setFont(new Font("Tahoma", Font.PLAIN, 24));
                listLb[i][j].setHorizontalAlignment((int) CENTER_ALIGNMENT);
                listLb[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
                listLb[i][j].addMouseListener(new ButtonEvent(mng, this));
            }        
        }
        pGameArea.setLayout(new GridLayout(size, size, 3, 3));
        pGameArea.setPreferredSize(new Dimension(690, 590));
    }
    
    public void updateArr(){
        int[][] tmp=new int[size][size];
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                tmp[i][j]=-1;
            }      
        }
        
        for (int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(!listLb[i][j].getText().isEmpty()){
                    if(listLb[i][j].getText().equals("X")){
                        tmp[i][j]=0;
                    }else if(listLb[i][j].getText().equals("O")){
                        tmp[i][j]=1;
                    }
                }
            }
        }
        
        for (int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                if(arr[i][j]!=tmp[i][j]){
                    String msg=KeyWord.YOUR_TURN+roomID+"-"+i+"-"+j; //"your-turn:roomID-{positionY}-{positionX}"
                    mng.sendRequestUpdateUI(msg);
                    positionX=j;
                    positionY=i;
                }
            }
        }
        arr=tmp;
    }
    
    public boolean checkWin(){
        String conditionWin="";
        String notWin="";
        
        notWin=mng.getListYourText().get(roomID).equals("X")?"O":"X";
        for (int i=0; i<5; i++){
            conditionWin+=mng.getListYourText().get(roomID);
            notWin+=mng.getListYourText().get(roomID);
        }
        notWin+=mng.getListYourText().get(roomID).equals("X")?"O":"X";
        if(checkRow(conditionWin, notWin)){
            return true;
        }
        if(checkColumn(conditionWin, notWin)){
            return true;
        }
        if(checkLeftBottomRightTop(conditionWin, notWin)){
            return true;
        }
        if(checkTopLeftToBottomRight(conditionWin, notWin)){
            return true;
        }
        return false;
    }
    
    public boolean checkRow(String conditionWin, String notWin){
        int startPoint=(positionX-5<0)?0:positionX-5;
        int endPoint=(positionX+5>=size)?size-1:positionX+5;
        for (int i=startPoint; i<=endPoint; i++){
            tmp+=listLb[positionY][i].getText();
        }
        if(tmp.contains(conditionWin)){
            if (!tmp.contains(notWin)){
                return true;
            }
        }
        System.out.println(tmp);
        return false;
    }
    
    public boolean checkColumn(String conditionWin, String notWin){
        tmp="";
        int startPoint=(positionY-5<0)?0:positionY-5;
        int endPoint=(positionY+5>=size)?size-1:positionY+5;
        for (int i=startPoint; i<=endPoint; i++){
            tmp+=listLb[i][positionX].getText();
        }
        if(tmp.contains(conditionWin)){
            if (!tmp.contains(notWin)){
                return true;
            }
        }
        return false;
    }
    
    public boolean checkTopLeftToBottomRight(String conditionWin, String notWin){
        tmp="";
        int startPointX=(positionX-5<0)?0:positionX-5;
        int startPointY=(positionY-5<0)?0:positionY-5;
        
        int countLeft=5;
        if(startPointX==0 && startPointY==0){
            if(positionY>positionX){
                countLeft=positionX;
            }else{
               countLeft=positionY; 
            }
        }else if(startPointY==0){
            countLeft=positionY;
        }else if(startPointX==0){
            countLeft=positionX;
        }  
        
        int endPointX=(positionX+5>=size)?size-1:positionX+5;
        int endPointY=(positionY+5>=size)?size-1:positionY+5;
        
        int countRight=5;
        if(endPointX==size-1 && endPointY==size-1){
            if(positionY>positionX){
                countRight=size-1-positionY;
            }else{
               countRight=size-1-positionX; 
            }
        }else if(endPointY==size-1){
            countRight=size-1-positionY;
        }else if(endPointX==size-1){
            countRight=size-1-positionX;
        }
        while(countLeft!=0){
            tmp+=listLb[positionY-countLeft][positionX-countLeft].getText();
            countLeft--;
        }
        tmp+=listLb[positionY][positionX].getText();
        int count=0;
        while(count!=countRight){
            count++;
            tmp+=listLb[positionY+count][positionX+count].getText();          
        }
        if(tmp.contains(conditionWin)){
            if (!tmp.contains(notWin)){
                return true;
            }
        }
        return false;
    }
    
    public boolean checkLeftBottomRightTop(String conditionWin, String notWin){
        tmp="";
        int startPointX=(positionX-5<0)?0:positionX-5;
        int startPointY=(positionY+5>=size)?size-1:positionY+5;
        
        int countLeft=5;
        if (startPointX==0 && startPointY==size-1){
            if(positionX<size-1-positionY){
                countLeft=positionX;
            }else{
                countLeft=size-1-positionY;
            }
        }else if(startPointY==size-1){
            countLeft=size-1-positionY;
        }else if(startPointX==0){
            countLeft=positionX;
        } 
        int endPointX=(positionX+5>=size)?size-1:positionX+5;
        int endPointY=(positionY-5<0)?0:positionY-5;
        
        int countRight=5;
        if (endPointX==size-1 && endPointY==0){
            if(positionY<size-1-positionX){
                countRight=positionY;
            }else{
                countRight=size-1-positionX;
            }
        }else if(endPointY==0){
            countRight=positionY;
        }else if(endPointX==size-1){
            countRight=size-1-positionX;
        }
        while(countLeft!=0){
            tmp+=listLb[positionY+countLeft][positionX-countLeft].getText();
            countLeft--;
        }
        tmp+=listLb[positionY][positionX].getText();
        int count=0;
        while(count!=countRight){
            count++;
            tmp+=listLb[positionY-count][positionX+count].getText();
        }
        if(tmp.contains(conditionWin)){
            if (!tmp.contains(notWin)){
                return true;
            }
        }
        System.out.println(tmp);
        return false;
    
}
    
    public boolean isFull(){
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                if (arr[i][j]==-1){
                return false;
                }
            }
            
        }
        return true;
    }
    
    public void drawNewGame(){
        for(int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                arr[i][j]=-1;
            }
        }
        drawGame();
    }
            

    public JLabel getLbName() {
        return lbName;
    }

    public void setLbName(String name) {
        this.lbName.setText(name);
    }

    public JLabel getLbTurn() {
        return lbTurn;
    }

    public void setLbTurn(String turn) {
        this.lbTurn.setText("Turn: "+turn);
    }

    public void setLbRoomID(String roomID) {
        this.lbRoomID.setText("Room ID: "+roomID);
    }

    public int[][] getArr() {
        return arr;
    }

    public JLabel[][] getListLb() {
        return listLb;
    }

    public int getRoomID() {
        return roomID;
    }
    


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbName = new javax.swing.JLabel();
        lbTurn = new javax.swing.JLabel();
        pGameArea = new javax.swing.JPanel();
        lbRoomID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Care Game");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("jLabel1");

        lbTurn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbTurn.setText("jLabel2");

        javax.swing.GroupLayout pGameAreaLayout = new javax.swing.GroupLayout(pGameArea);
        pGameArea.setLayout(pGameAreaLayout);
        pGameAreaLayout.setHorizontalGroup(
            pGameAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 693, Short.MAX_VALUE)
        );
        pGameAreaLayout.setVerticalGroup(
            pGameAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 693, Short.MAX_VALUE)
        );

        lbRoomID.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbRoomID.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pGameArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lbTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTurn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pGameArea, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosing

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        this.dispose();
        mng.sendQuitGameInFo(this.roomID);
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new GameUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbRoomID;
    private javax.swing.JLabel lbTurn;
    private javax.swing.JPanel pGameArea;
    // End of variables declaration//GEN-END:variables
}
