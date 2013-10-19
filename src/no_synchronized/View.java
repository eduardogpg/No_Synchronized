
package no_synchronized;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eduardo
 */
public class View extends javax.swing.JFrame implements ActionListener{

    JTable table;
    ArrayList list ;
    private boolean flag= false;
    private int numberThreadtoBegin = 10; 
    Vector vector2; 
    private int timeUpdate =2;
    
    DefaultTableModel modelo;
    Timer time;
    Clock myclock;
    
    public View() {
        initComponents();
        
        this.myclock = new Clock();
        this.myclock.start();
        
        list = new ArrayList();
        Maketable();
        time = new Timer(4,this); 
        time.start();
        setFocusable(true);   
    }
    private void Maketable(){
         Vector vector = new Vector();
         vector.addElement("Name Thread");
         vector.addElement("sleep");
         vector.addElement("Critical Zone");
         vector.addElement("Stop");
        
      modelo = new DefaultTableModel(vector,0);
      this.jTable1.setModel(modelo);
     
    }
    public  void Checkbehavior(){
   
            for(int y=0;y<this.list.size();y++){
                Main_Thread thread =(Main_Thread)list.get(y);
                if(thread.getSleep())
                  modelo.setValueAt("x", y, 1);
                else
                     modelo.setValueAt(" ", y, 1);
                
                if(thread.getCriticalZone())
                  modelo.setValueAt("x", y, 2);
                else
                    modelo.setValueAt(" ", y, 2);
                
                if(thread.getLife())
                    modelo.setValueAt("x", y, 3);
            }
 
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Stop All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setText("Number of Thread");

        jLabel1.setText(".");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void MakeThread(int Number){
        
        if(!flag){
            String Name = "";
            for(int x=0;x<Number;x++){
                vector2 = new Vector();
                Name = "Thread"+x;
                Main_Thread thread = new Main_Thread(Name,numberThreadtoBegin);
                list.add(thread);
                thread.start();
                
                vector2.add(Name);
                vector2.add("");
                vector2.add("");
                modelo.addRow(vector2);
            }
        }
        flag= true;
    }
    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            int numberThread = Integer.parseInt( this.jTextField1.getText() );
            MakeThread(numberThread);
            this.jLabel1.setText("Check the table");
          
        }
        catch(Exception e){
            this.jLabel1.setText("Only Numbers");
            System.err.println(e);
        }   
    }//GEN-LAST:event_jButton1ActionPerformed
    
   private boolean Stop= false;
    private void StopAll(){
        if(flag)
            for(int x=0;x<list.size();x++){
                Main_Thread thread = (Main_Thread)list.get(x);
                thread.StopThread();
         }
        Stop= true;
     
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try{
            StopAll();
        }finally{
            System.err.println("Stop All Finished");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

   
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new View().setVisible(true);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        
            this.Checkbehavior();
    
}
}
