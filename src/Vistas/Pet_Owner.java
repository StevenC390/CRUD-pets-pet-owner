/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Modelos.Conexion;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Steven
 */
public class Pet_Owner extends javax.swing.JDialog {

    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;

    /**
     * Creates new form Pet_Owner
     */
    public Pet_Owner(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

    }

    public Pet_Owner() {
        initComponents();
        show_owners();
        setLocationRelativeTo(null); // Para que el JFrame se vea centrado en la pantalla
    }

    void show_owners() {
        String sql = "SELECT * FROM tb_pet_owners";
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
//Los datos que devuelve la consulta se muestran en la tabla
            Object[] owner = new Object[6];
            modelo = (DefaultTableModel) tb_owners.getModel();
            while (rs.next()) {
                owner[0] = rs.getString("owner");
                owner[1] = rs.getInt("id_document");
                owner[2] = rs.getString("document_type");
                owner[3] = rs.getInt("document");
                owner[4] = rs.getString("contact");
                owner[5] = rs.getString("gender");
                modelo.addRow(owner);
                System.out.println(rs.getInt("id"));
            }
            tb_owners.setModel(modelo);
        } catch (SQLException e) {
        }
    }

    void add_owner() {
        String name = txt_name.getText();
        int id_document = Integer.parseInt(txt_doc_id.getText());
        String doc_type = txt_doc_type.getText();
        int document = Integer.parseInt(txt_doc.getText());
        String contact = txt_contact.getText();
        String gender = txt_gender.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del Dueño");
        } else {
            String query = "INSERT INTO `tb_pet_owners`(`owner`,`id_document`,`document_type`,`document`,`contact`,`gender`) VALUES('" + name + "'  ," + id_document + ",'" + doc_type + "'  ," + document + "  ,'" + contact + "'  ,'" + gender + "')";

            try {
                cn = con.getConnection();
                st = cn.createStatement();

                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El Dueño ha sido creado");
                clear_rows_table();
                show_owners();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "No se pudo crear el Dueño");
            }
        }
    }

    void edit_owner() {
//Hacemos nuevamente lectura de los valores contenidos en los JTextField
//Para identificar si el usuario modifico algún valor
        String name = txt_name.getText();
        int id_document = Integer.parseInt(txt_doc_id.getText());
        String doc_type = txt_doc_type.getText();
        int document = Integer.parseInt(txt_doc.getText());
        String contact = txt_contact.getText();
        String gender = txt_gender.getText();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre del departamento");
        } else {
            String query = "UPDATE tb_pet_owners SET owner = '"
                    + name + "', id_document= " + id_document + ", document_type= '" + doc_type + "', document=" + document + ", contact='" + contact + "', gender='" + gender + "' WHERE document = " + document;
//UPDATE tb_persons SET dni =dni, nombre= 'name' WHERE id = id
            try {

                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El dueño ha sido modificado con éxito");;
                clear_rows_table();
                show_owners();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "No se pudo modificar el dueño");
            }
        }
    }

    void delete_owner() {
        int fila = tb_owners.getSelectedRow();
        int cedula = Integer.parseInt(txt_doc.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado un dueño");
        } else {
            String query = "DELETE FROM tb_pet_owners WHERE document = " + cedula;
            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "El dueño ha sido eliminado con exito");
                clear_rows_table();
                show_owners();
            } catch (HeadlessException | SQLException e) {
            }
        }
    }

    void clear_rows_table() {
        for (int i = 0; i < tb_owners.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
        txt_contact.setText("");
        txt_doc.setText("");
        txt_doc_id.setText("");
        txt_doc_type.setText("");
        txt_gender.setText("");
        txt_name.setText("");
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_name = new javax.swing.JTextField();
        txt_doc_id = new javax.swing.JTextField();
        txt_doc_type = new javax.swing.JTextField();
        txt_doc = new javax.swing.JTextField();
        txt_contact = new javax.swing.JTextField();
        txt_gender = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_owners = new javax.swing.JTable();
        btn_ver_mascotas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pet Owners Hospital");

        jLabel2.setText("Nombre dueño");

        jLabel3.setText("Id documento");

        jLabel4.setText("Tipo documento");

        jLabel5.setText("Documento");

        jLabel6.setText("Contacto");

        jLabel7.setText("Sexo");

        txt_doc_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_doc_typeActionPerformed(evt);
            }
        });

        btn_add.setText("Agregar Dueño");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_edit.setText("Editar informacion");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_delete.setText("Eliminar Dueño");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        tb_owners.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre dueño", "Id documento", "Tipo documento", "Documento", "Contacto", "Sexo"
            }
        ));
        tb_owners.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_ownersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_owners);

        btn_ver_mascotas.setText("Ver mascotas");
        btn_ver_mascotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ver_mascotasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel4))
                            .addGap(34, 34, 34)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(txt_doc_type, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel7))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txt_doc_id, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel6))))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_gender, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addGap(260, 260, 260)
                                            .addComponent(btn_delete)))
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addGap(17, 17, 17))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(83, 83, 83)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(btn_add)
                                    .addGap(84, 84, 84)
                                    .addComponent(btn_edit)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(279, 279, 279)
                        .addComponent(btn_ver_mascotas)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_doc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_doc_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_contact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txt_gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_doc_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add)
                    .addComponent(btn_edit)
                    .addComponent(btn_delete))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ver_mascotas)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        add_owner();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        edit_owner();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        delete_owner();
    }//GEN-LAST:event_btn_deleteActionPerformed

    private void tb_ownersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_ownersMouseClicked
        // TODO add your handling code here:
        int row = tb_owners.getSelectedRow();
        System.out.println(row);
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar un dueño");

        } else {
            String name = (String) tb_owners.getValueAt(row, 0);
            int id_document = Integer.parseInt((String) tb_owners.getValueAt(row,1).toString());
            String doc_type = (String) tb_owners.getValueAt(row, 2);
            int document = Integer.parseInt((String) tb_owners.getValueAt(row,3).toString());
            String contact = (String) tb_owners.getValueAt(row, 4);
            String gender = (String) tb_owners.getValueAt(row, 5);

            txt_name.setText("" + name);
            txt_doc_id.setText("" + id_document);
            txt_doc_type.setText("" + doc_type);
            txt_doc.setText("" + document);
            txt_contact.setText("" + contact);
            txt_gender.setText("" + gender);
        }
    }//GEN-LAST:event_tb_ownersMouseClicked

    private void btn_ver_mascotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ver_mascotasActionPerformed
        // TODO add your handling code here:
        new Pet(this).setVisible(true);
    }//GEN-LAST:event_btn_ver_mascotasActionPerformed

    private void txt_doc_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_doc_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_doc_typeActionPerformed

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
            java.util.logging.Logger.getLogger(Pet_Owner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pet_Owner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pet_Owner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pet_Owner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pet_Owner dialog = new Pet_Owner(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_delete;
    private javax.swing.JButton btn_edit;
    private javax.swing.JButton btn_ver_mascotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_owners;
    private javax.swing.JTextField txt_contact;
    private javax.swing.JTextField txt_doc;
    private javax.swing.JTextField txt_doc_id;
    private javax.swing.JTextField txt_doc_type;
    private javax.swing.JTextField txt_gender;
    private javax.swing.JTextField txt_name;
    // End of variables declaration//GEN-END:variables
}
