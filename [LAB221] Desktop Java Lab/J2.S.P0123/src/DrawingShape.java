import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class DrawingShape extends javax.swing.JFrame {
    Graphics g=null;
    Point p1, p2=null;
    String shape="";
    /**
     * Creates new form DrawingShape
     */
    public DrawingShape() {
        initComponents();
        this.setSize(700, 550);
        g=pDrawArea.getGraphics();
        g.setColor(Color.red);
        pDrawArea.setSize(700, 550);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        pDrawArea = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnAdd = new javax.swing.JMenu();
        mnRectangle = new javax.swing.JMenuItem();
        mnOval = new javax.swing.JMenuItem();
        mnRoundRect = new javax.swing.JMenuItem();
        mnColor = new javax.swing.JMenu();
        mnRed = new javax.swing.JRadioButtonMenuItem();
        mnGreen = new javax.swing.JRadioButtonMenuItem();
        mnBlue = new javax.swing.JRadioButtonMenuItem();
        mnCyan = new javax.swing.JRadioButtonMenuItem();
        mnMagenta = new javax.swing.JRadioButtonMenuItem();
        mnYellow = new javax.swing.JRadioButtonMenuItem();
        mnBlack = new javax.swing.JRadioButtonMenuItem();
        mnWhite = new javax.swing.JRadioButtonMenuItem();
        mnGray = new javax.swing.JRadioButtonMenuItem();
        mnOptions = new javax.swing.JMenu();
        mnClear = new javax.swing.JMenuItem();
        cbBorder = new javax.swing.JCheckBoxMenuItem();
        mnBackgroundColor = new javax.swing.JMenu();
        cbWhite_BG = new javax.swing.JCheckBoxMenuItem();
        cbGreen_BG = new javax.swing.JCheckBoxMenuItem();
        cbCyan_BG = new javax.swing.JCheckBoxMenuItem();
        cbBlue_BG = new javax.swing.JCheckBoxMenuItem();
        cbGray_BG = new javax.swing.JCheckBoxMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        pDrawArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pDrawAreaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pDrawAreaMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout pDrawAreaLayout = new javax.swing.GroupLayout(pDrawArea);
        pDrawArea.setLayout(pDrawAreaLayout);
        pDrawAreaLayout.setHorizontalGroup(
            pDrawAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 788, Short.MAX_VALUE)
        );
        pDrawAreaLayout.setVerticalGroup(
            pDrawAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 546, Short.MAX_VALUE)
        );

        getContentPane().add(pDrawArea, java.awt.BorderLayout.CENTER);

        jMenuBar1.setName(""); // NOI18N
        jMenuBar1.setPreferredSize(new java.awt.Dimension(143, 40));

        mnAdd.setText("Add");

        mnRectangle.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mnRectangle.setText("Rectangle");
        mnRectangle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRectangleActionPerformed(evt);
            }
        });
        mnAdd.add(mnRectangle);

        mnOval.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnOval.setText("Oval");
        mnOval.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnOvalActionPerformed(evt);
            }
        });
        mnAdd.add(mnOval);

        mnRoundRect.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        mnRoundRect.setText("Round Rect");
        mnRoundRect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRoundRectActionPerformed(evt);
            }
        });
        mnAdd.add(mnRoundRect);

        jMenuBar1.add(mnAdd);

        mnColor.setText("Color");

        buttonGroup1.add(mnRed);
        mnRed.setSelected(true);
        mnRed.setText("Red");
        mnRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRedActionPerformed(evt);
            }
        });
        mnColor.add(mnRed);

        buttonGroup1.add(mnGreen);
        mnGreen.setText("Green");
        mnGreen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGreenActionPerformed(evt);
            }
        });
        mnColor.add(mnGreen);

        buttonGroup1.add(mnBlue);
        mnBlue.setText("Blue");
        mnBlue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBlueActionPerformed(evt);
            }
        });
        mnColor.add(mnBlue);

        buttonGroup1.add(mnCyan);
        mnCyan.setText("Cyan");
        mnCyan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCyanActionPerformed(evt);
            }
        });
        mnColor.add(mnCyan);

        buttonGroup1.add(mnMagenta);
        mnMagenta.setText("Magenta");
        mnMagenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnMagentaActionPerformed(evt);
            }
        });
        mnColor.add(mnMagenta);

        buttonGroup1.add(mnYellow);
        mnYellow.setText("Yellow");
        mnYellow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnYellowActionPerformed(evt);
            }
        });
        mnColor.add(mnYellow);

        buttonGroup1.add(mnBlack);
        mnBlack.setText("Black");
        mnBlack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnBlackActionPerformed(evt);
            }
        });
        mnColor.add(mnBlack);

        buttonGroup1.add(mnWhite);
        mnWhite.setText("White");
        mnWhite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnWhiteActionPerformed(evt);
            }
        });
        mnColor.add(mnWhite);

        buttonGroup1.add(mnGray);
        mnGray.setText("Gray");
        mnGray.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnGrayActionPerformed(evt);
            }
        });
        mnColor.add(mnGray);

        jMenuBar1.add(mnColor);

        mnOptions.setText("Options");

        mnClear.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mnClear.setText("Clear");
        mnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnClearActionPerformed(evt);
            }
        });
        mnOptions.add(mnClear);

        cbBorder.setText("Add Shapes with Border");
        cbBorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBorderActionPerformed(evt);
            }
        });
        mnOptions.add(cbBorder);

        mnBackgroundColor.setText("Background Color");

        buttonGroup2.add(cbWhite_BG);
        cbWhite_BG.setText("White");
        cbWhite_BG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbWhite_BGActionPerformed(evt);
            }
        });
        mnBackgroundColor.add(cbWhite_BG);

        buttonGroup2.add(cbGreen_BG);
        cbGreen_BG.setText("Green");
        cbGreen_BG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGreen_BGActionPerformed(evt);
            }
        });
        mnBackgroundColor.add(cbGreen_BG);

        buttonGroup2.add(cbCyan_BG);
        cbCyan_BG.setText("Cyan");
        cbCyan_BG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCyan_BGActionPerformed(evt);
            }
        });
        mnBackgroundColor.add(cbCyan_BG);

        buttonGroup2.add(cbBlue_BG);
        cbBlue_BG.setText("Blue");
        cbBlue_BG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbBlue_BGActionPerformed(evt);
            }
        });
        mnBackgroundColor.add(cbBlue_BG);

        buttonGroup2.add(cbGray_BG);
        cbGray_BG.setText("Gray");
        cbGray_BG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbGray_BGActionPerformed(evt);
            }
        });
        mnBackgroundColor.add(cbGray_BG);

        mnOptions.add(mnBackgroundColor);

        jMenuBar1.add(mnOptions);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void drawRect(){
        int left=p1.x<p2.x? p1.x: p2.x;
        int top=p1.y<p2.y? p1.y: p2.y;
        int height=Math.abs(p1.y-p2.y);
        int width=Math.abs(p1.x-p2.x);
        
        Color c=g.getColor();
        g.fillRect(left, top, width, height);
        if (cbBorder.isSelected()){
            g.setColor(Color.black);
            g.drawRect(left, top, width, height);
        }
        g.setColor(c);
        //p1=p2=null;
    }
    
    private void drawOval() {
        int left=p1.x<p2.x? p1.x: p2.x;
        int top=p1.y<p2.y? p1.y: p2.y;
        int height=Math.abs(p1.y-p2.y);
        int width=Math.abs(p1.x-p2.x);
        
         Color c=g.getColor();
        g.fillOval(left, top, width, height);
        if (cbBorder.isSelected()){
            g.setColor(Color.black);
            g.drawOval(left, top, width, height);
        }
          g.setColor(c);
        //p1=p2=null;
    }
    
    private void drawRoundRect() {
         int left=p1.x<p2.x? p1.x: p2.x;
        int top=p1.y<p2.y? p1.y: p2.y;
        int height=Math.abs(p1.y-p2.y);
        int width=Math.abs(p1.x-p2.x);
        
         Color c=g.getColor();
        g.fillRoundRect(left, top, width, height, 20, 20);
        
        if (cbBorder.isSelected()) {
            g.setColor(Color.black);
            g.drawRoundRect(left, top, width, height, 20, 20);
        }
          g.setColor(c);
        //p1=p2=null;
    }
    
    private void mnRoundRectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRoundRectActionPerformed
        // TODO add your handling code here:
        shape="roundRect";
    }//GEN-LAST:event_mnRoundRectActionPerformed

    private void mnRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRedActionPerformed
        // TODO add your handling code here:
        Color c=Color.red;
        g.setColor(c);
    }//GEN-LAST:event_mnRedActionPerformed

    private void mnGreenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGreenActionPerformed
        // TODO add your handling code here:
        g.setColor(Color.green);
    }//GEN-LAST:event_mnGreenActionPerformed

    private void pDrawAreaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pDrawAreaMousePressed
        // TODO add your handling code here:
        p1=evt.getPoint();
        System.out.println(p1);
    }//GEN-LAST:event_pDrawAreaMousePressed

    private void pDrawAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pDrawAreaMouseReleased
        // TODO add your handling code here:
        p2=evt.getPoint();
        System.out.println(p2);
        switch(shape){
            case "rect": drawRect();
                         break;
            case "oval": drawOval();
                         break;
            case "roundRect": drawRoundRect();
                         break;
        }
    }//GEN-LAST:event_pDrawAreaMouseReleased

    private void mnBlueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBlueActionPerformed
        // TODO add your handling code here:
        g.setColor(Color.blue);
    }//GEN-LAST:event_mnBlueActionPerformed

    private void mnCyanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCyanActionPerformed
        // TODO add your handling code here:
        g.setColor(Color.cyan);
    }//GEN-LAST:event_mnCyanActionPerformed

    private void mnMagentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnMagentaActionPerformed
        // TODO add your handling code here:
        g.setColor(Color.magenta);
    }//GEN-LAST:event_mnMagentaActionPerformed

    private void mnYellowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnYellowActionPerformed
        // TODO add your handling code here:
        g.setColor(Color.yellow);
    }//GEN-LAST:event_mnYellowActionPerformed

    private void mnBlackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnBlackActionPerformed
        // TODO add your handling code here:
        g.setColor(Color.black);
    }//GEN-LAST:event_mnBlackActionPerformed

    private void mnGrayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnGrayActionPerformed
        // TODO add your handling code here:
        g.setColor(Color.gray);
    }//GEN-LAST:event_mnGrayActionPerformed

    private void mnWhiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnWhiteActionPerformed
        // TODO add your handling code here:
        g.setColor(Color.white);
    }//GEN-LAST:event_mnWhiteActionPerformed

    private void mnOvalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnOvalActionPerformed
        // TODO add your handling code here:
        shape="oval";
    }//GEN-LAST:event_mnOvalActionPerformed

    private void mnRectangleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRectangleActionPerformed
        // TODO add your handling code here:
        shape="rect";
        //isRect=true;
    }//GEN-LAST:event_mnRectangleActionPerformed

    private void mnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnClearActionPerformed
        // TODO add your handling code here:
        g.clearRect(0, 0, 700, 550);
       
    }//GEN-LAST:event_mnClearActionPerformed

    private void cbCyan_BGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCyan_BGActionPerformed
        // TODO add your handling code here:
        pDrawArea.setBackground(Color.cyan);
    }//GEN-LAST:event_cbCyan_BGActionPerformed

    private void cbWhite_BGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbWhite_BGActionPerformed
        // TODO add your handling code here:
        pDrawArea.setBackground(Color.white);
    }//GEN-LAST:event_cbWhite_BGActionPerformed

    private void cbGreen_BGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGreen_BGActionPerformed
        // TODO add your handling code here:
        pDrawArea.setBackground(Color.green);
    }//GEN-LAST:event_cbGreen_BGActionPerformed

    private void cbBlue_BGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBlue_BGActionPerformed
        // TODO add your handling code here:
        pDrawArea.setBackground(Color.blue);
    }//GEN-LAST:event_cbBlue_BGActionPerformed

    private void cbGray_BGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbGray_BGActionPerformed
        // TODO add your handling code here:
        pDrawArea.setBackground(Color.gray);
    }//GEN-LAST:event_cbGray_BGActionPerformed

    private void cbBorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbBorderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbBorderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DrawingShape.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DrawingShape.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DrawingShape.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DrawingShape.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DrawingShape().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JCheckBoxMenuItem cbBlue_BG;
    private javax.swing.JCheckBoxMenuItem cbBorder;
    private javax.swing.JCheckBoxMenuItem cbCyan_BG;
    private javax.swing.JCheckBoxMenuItem cbGray_BG;
    private javax.swing.JCheckBoxMenuItem cbGreen_BG;
    private javax.swing.JCheckBoxMenuItem cbWhite_BG;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu mnAdd;
    private javax.swing.JMenu mnBackgroundColor;
    private javax.swing.JRadioButtonMenuItem mnBlack;
    private javax.swing.JRadioButtonMenuItem mnBlue;
    private javax.swing.JMenuItem mnClear;
    private javax.swing.JMenu mnColor;
    private javax.swing.JRadioButtonMenuItem mnCyan;
    private javax.swing.JRadioButtonMenuItem mnGray;
    private javax.swing.JRadioButtonMenuItem mnGreen;
    private javax.swing.JRadioButtonMenuItem mnMagenta;
    private javax.swing.JMenu mnOptions;
    private javax.swing.JMenuItem mnOval;
    private javax.swing.JMenuItem mnRectangle;
    private javax.swing.JRadioButtonMenuItem mnRed;
    private javax.swing.JMenuItem mnRoundRect;
    private javax.swing.JRadioButtonMenuItem mnWhite;
    private javax.swing.JRadioButtonMenuItem mnYellow;
    private javax.swing.JPanel pDrawArea;
    // End of variables declaration//GEN-END:variables
}
