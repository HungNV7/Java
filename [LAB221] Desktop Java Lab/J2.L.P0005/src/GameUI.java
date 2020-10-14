
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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

    public final int HIGHT = 650;
    public final int WIDTH = 550;
    public final int GAP = 150;
    JLabel lb;
    boolean flag = false;
    boolean isLose = false;
    int point = 0;

    List<JButton> listBtn;
//    JButton btn1=new JButton("1");
//    JButton btn2=new JButton("2");
    private boolean start = false;

    /**
     * Creates new form GameUI
     */
    public GameUI() {
        initComponents();
        pGame.setPreferredSize(new Dimension(WIDTH, HIGHT));
        listBtn = new ArrayList<>();
        drawNewGame();
    }

    public void drawNewGame() {
        drawFrog();
        addPipe(WIDTH + 80);
    }

    public void drawFrog() {
        lb = new JLabel();
        lb.setSize(new Dimension(50, 50));
        pGame.add(lb);
        lb.setLocation(220, 250);

        String path = "D:\\FPT\\CN4\\LAB221\\J2.L.P0005\\frog.png";
        ImageIcon icon = new ImageIcon(path);
        Image img = icon.getImage();
        img = img.getScaledInstance(lb.getWidth(), lb.getHeight(), Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        lb.setIcon(icon);

    }

    public void jump() {
        if (!isLose) {
            int x = lb.getX();
            int y = lb.getY();
            lb.setLocation(x, y - 60);
        }
    }

    public void frogMove() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isLose) {
                        int x = lb.getX();
                        int y = lb.getY() + 10;
                        lb.setLocation(x, y);
                        Thread.sleep(50);
                    }

                } catch (Exception ex) {
                }
            }

        };
        thread.start();
    }

    public void pipeMove() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isLose) {
                        for (JButton btn : listBtn) {
                            int x = btn.getX() - 10;
                            int y = btn.getY();

                            btn.setLocation(x, y);

                            if (x == 140) {
                                flag = true;
                            }
                        }
                        if (flag) {
                            addPipe(WIDTH);
                            flag = false;
                        }
                        Thread.sleep(50);
                    }

                } catch (Exception ex) {
                }
            }

        };
        thread.start();
    }

    public void addPipe(int position) {
        Random rd = new Random();
        int height = rd.nextInt(30) * 10 + 100; // 100-400
        JButton btn1 = new JButton();
        btn1.setEnabled(false);
        btn1.setBorder(BorderFactory.createLineBorder(Color.red));

        JButton btn2 = new JButton();
        btn2.setBorder(BorderFactory.createLineBorder(Color.red));
        btn2.setEnabled(false);
        listBtn.add(btn1);
        listBtn.add(btn2);

        pGame.add(btn2);
        pGame.add(btn1);
        btn1.setSize(new Dimension(80, height));
        btn2.setSize(new Dimension(80, HIGHT - height - GAP));
        btn1.setLocation(position, 0);
        btn2.setLocation(position, height + GAP);
    }

    public void checkLose() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                while (!isLose) {
                    try {
                        boolean end = false;
                        int positionX = lb.getX();
                        int positionY = lb.getY();
                        //System.out.println(positionY);
                        for (int i = 0; i < listBtn.size(); i++) {
                            JButton btn = listBtn.get(i);
                            if (btn.getX() == positionX + 50) {
                                if (i % 2 == 0 && positionY >= 0 && positionY <= btn.getHeight()) {
                                    end = true;
                                }
                                if (i % 2 != 0 && positionY >= btn.getY() - 50) {
                                    end = true;
                                }
                            }

                            if (positionX + 50 >= btn.getX() && positionX <= btn.getX() + 80) {
                                if (positionY == btn.getHeight() && i % 2 == 0) {
                                    end = true;

                                }

                                if (positionY == btn.getY() - 50 && i % 2 != 0) {
                                    end = true;
                                }
                            }
                            if (positionY >= 600) {
                                end = true;
                            }
                            if (end) {
                                showResult();
                                end = false;
                            }

                        }

                        Thread.sleep(10);
                    } catch (Exception ex) {
                    }
                }
            }

        };
        thread.start();
    }

    public void countPoint() {
        Thread thread = new Thread() {

            @Override
            public void run() {
                try {
                    while (!isLose) {
                        int positionX = lb.getX();
                        int positionY = lb.getY();
                        //System.out.println(positionY);
                        for (int i = 0; i < listBtn.size(); i++) {
                            JButton btn = listBtn.get(i);
                            if (i % 2 == 0 && btn.getX() + 80 == positionX) {
                                point++;
                            }
                        }
                        lbPoint.setText("Point: " + point);
                        Thread.sleep(50);
                    }
                } catch (Exception e) {
                }

            }

        };
        thread.start();
    }

    public void showResult() {
        isLose = true;
        String reward = "";
        if (point < 10) {
            reward = "No medal";
        } else if (point < 20) {
            reward = "Bronze medal";
        } else if (point < 30) {
            reward = "Sliver medal";
        } else if (point < 40) {
            reward = "Gold metal";
        } else {
            reward = "Platium medal";
        }
        int choice = JOptionPane.showConfirmDialog(this, "Your point: " + point + "\nYour reward: " + reward + "\nDo you want to play again?", "Result", JOptionPane.YES_NO_OPTION);
        if (choice == 0) {
            pGame.removeAll();
            pGame.invalidate();
            pGame.repaint();
            listBtn.removeAll(listBtn);
            drawNewGame();
            point = 0;
            lbPoint.setText("Point: " + point);
            isLose = false;
            start = false;
        } else {
            System.exit(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pGame = new javax.swing.JPanel();
        lbPoint = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        pGame.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pGame.setPreferredSize(new java.awt.Dimension(556, 650));
        pGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pGameMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pGameMouseReleased(evt);
            }
        });
        pGame.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pGameKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pGameLayout = new javax.swing.GroupLayout(pGame);
        pGame.setLayout(pGameLayout);
        pGameLayout.setHorizontalGroup(
            pGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 554, Short.MAX_VALUE)
        );
        pGameLayout.setVerticalGroup(
            pGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 648, Short.MAX_VALUE)
        );

        lbPoint.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbPoint.setText("Point: 0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPoint)
                    .addComponent(pGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPoint)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pGameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pGameMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_pGameMousePressed

    private void pGameMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pGameMouseReleased
        // TODO add your handling code here:
        jump();
        if (!start) {
            countPoint();
            pipeMove();
            frogMove();
            checkLose();

        }
        start = true;

    }//GEN-LAST:event_pGameMouseReleased

    private void pGameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pGameKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_pGameKeyReleased

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyChar() == ' ') {
            pGameMouseReleased(null);
        }
    }//GEN-LAST:event_formKeyReleased

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
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbPoint;
    private javax.swing.JPanel pGame;
    // End of variables declaration//GEN-END:variables
}
