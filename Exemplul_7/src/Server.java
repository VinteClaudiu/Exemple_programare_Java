/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package CarteClientServerDB;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import org.apache.derby.jdbc.ClientDataSource;

/**
 *
 * @author Stud
 */
public class Server extends javax.swing.JFrame {

    private Connection conn;
    private ServerSocket servSocket;
    private Socket socket;
    private OutputStream out;
    private ObjectOutputStream fout;
    private InputStream in;
    private ObjectInputStream fin;
    private boolean startat;

    /**
     * Creates new form Server
     */
    public Server() {
        initComponents();
        try {
            ClientDataSource ds = new ClientDataSource();
            ds.setServerName("localhost");
            ds.setUser("server");
            ds.setPassword("server");
            ds.setDatabaseName("biblioteca");
            ds.setPortNumber(1527);
            conn = ds.getConnection();
        } catch (Exception ex) {
            jtaOut.append(ex + "\n");
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

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtaOut = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jButton1.setText("Start");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jtaOut.setEditable(false);
        jtaOut.setColumns(20);
        jtaOut.setRows(5);
        jScrollPane1.setViewportView(jtaOut);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        try {
            conn.close();
        } catch (Exception ex) {
        }
        try {
            servSocket.close();
        } catch (Exception ex) {
        }
        try {
            socket.close();
        } catch (Exception ex) {
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        if (!startat) {
            try {
                servSocket = new ServerSocket(2000);
                socket = servSocket.accept();
                jtaOut.append("Conexiune realizata.\n");
                in = socket.getInputStream();
                fin = new ObjectInputStream(in);
                out = socket.getOutputStream();
                fout = new ObjectOutputStream(out);
                startat = true;
                Thread firRaspuns = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        raspuns();
                    }
                });
                firRaspuns.start();
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.toString());
            }

        } else {
            JOptionPane.showMessageDialog(this, "Serverul a fost deja startat!");
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void raspuns() {
        while (true) {
            try {
                if (in.available() != 0) {
                    String cota = (String) fin.readObject();
                    jtaOut.append("Am primit cota " + cota + ".\n");
                    Statement sttm = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
                                    ResultSet.CONCUR_UPDATABLE);
                    ResultSet r = sttm.executeQuery("select * from carte where cota like '%"
                            + cota + "%'");
                    r.last();
                    int n = r.getRow();
                    fout.writeObject(new Integer(n));
                    r.beforeFirst();
                    while (r.next()) {
                        Carte carte = new Carte();
                        carte.setCota(r.getString(1));
                        carte.setTitlu(r.getString(2));
                        carte.setAutori(r.getString(3));
                        carte.setAn(r.getInt(4));
                        fout.writeObject(carte);
                    }
                }
                else {
                    Thread.sleep(200);
                }
            } catch (Exception ex) {
                jtaOut.append(ex + "\n");
            }
        }

    }

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Server().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtaOut;
    // End of variables declaration//GEN-END:variables
}