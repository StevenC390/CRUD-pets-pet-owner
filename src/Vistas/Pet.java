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
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Steven
 */
public class Pet extends javax.swing.JDialog {

    Conexion con = new Conexion();
    Connection cn;
    Statement st;
    ResultSet rs;
    DefaultTableModel modelo;
    
    

    /**
     * Creates new form Pet
     */
    public Pet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
    }

    public Pet(Pet_Owner parent) {
        initComponents();
        setLocationRelativeTo(parent);
        show_pets();

    }
    void show_pets() {
        String sql = "SELECT * FROM tb_pet,tb_pet_owners WHERE tb_pet.id_owner_pet = tb_pet_owners.id";
        try {
            cn = con.getConnection();
            st = cn.createStatement();
            rs = st.executeQuery(sql);
//Los datos que devuelve la consulta se muestran en la tabla
            Object[] pet = new Object[4];
            modelo = (DefaultTableModel) tb_pets.getModel();
            while (rs.next()) {
                pet[0] = rs.getInt("id");
                pet[1] = rs.getString("name");
                pet[2] = rs.getString("breed");
                pet[3] = rs.getInt("id_owner_pet");
                modelo.addRow(pet);
                System.out.println(pet[0]);
            }
            tb_pets.setModel(modelo);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    void add_pet() {
        String name = txt_name.getText();
        String breed = txt_breed.getText();
        int id_document = Integer.parseInt(txt_id_owner.getText());
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre de la mascota");
        } else {
            String query = "INSERT INTO `tb_pet`(`name`, `breed`,`id_owner_pet`) VALUES('" + name + "','" + breed + "', " + id_document + ")";
            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La mascota ha sido añadida");
                clear_rows_table();
                show_pets();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "No se pudo añadidir la mascota");
            }
        }
    }

    void edit_pet() {
//Hacemos nuevamente lectura de los valores contenidos en los JTextField
//Para identificar si el usuario modifico algún valor
        String name = txt_name.getText();
        String breed = txt_breed.getText();
        int id = Integer.parseInt(txt_cod_pet.getText());
        int id_document = Integer.parseInt(txt_id_owner.getText());
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Falta ingresar el nombre de la mascota");
        } else {
            String query = "UPDATE tb_pet SET name = '" + name + "', breed= '" + breed + "', id_owner_pet =" + id_document + " WHERE id = " + id;
            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "Macota modificada con éxito");
                clear_rows_table();
                show_pets();
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, "No se pudo modificar la mascota" +e);
            }
        }
    }

    void delete_pet() {
        int fila = tb_pets.getSelectedRow();
        int id = Integer.parseInt(txt_cod_pet.getText());
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "No has seleccionado una mascota");
        } else {
            System.out.println("ID: " + id);
            String query = "DELETE FROM tb_pet WHERE id = " + id;
            try {
                cn = con.getConnection();
                st = cn.createStatement();
                st.executeUpdate(query);
                JOptionPane.showMessageDialog(this, "La mascota ha sido eliminada con exito");
                clear_rows_table();
                show_pets();
            } catch (HeadlessException | SQLException e) {
            }
        }
    }

    void clear_rows_table() {
        for (int i = 0; i < tb_pets.getRowCount(); i++) {
            modelo.removeRow(i);
            i = i - 1;
        }
        txt_breed.setText("");
        txt_cod_pet.setText("");
        txt_id_owner.setText("");
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
        txt_name = new javax.swing.JTextField();
        txt_breed = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_id_owner = new javax.swing.JTextField();
        btn_add = new javax.swing.JButton();
        btn_edit = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_pets = new javax.swing.JTable();
        txt_cod_pet = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Mascotas");

        jLabel2.setText("Nombre ");

        jLabel3.setText("Raza ");

        jLabel4.setText("Id documento dueño");

        txt_id_owner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_ownerActionPerformed(evt);
            }
        });

        btn_add.setText("Agregar Mascota");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_edit.setText("Editar Informacion");
        btn_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editActionPerformed(evt);
            }
        });

        btn_delete.setText("Eliminar Mascota");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        tb_pets.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre mascota", "Raza", "Dueño"
            }
        ));
        tb_pets.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tb_petsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tb_pets);

        txt_cod_pet.setEditable(false);

        jLabel5.setText("Codigo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(262, 262, 262)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btn_add)
                                .addGap(51, 51, 51)
                                .addComponent(btn_edit)
                                .addGap(48, 48, 48)
                                .addComponent(btn_delete))
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_name, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                                    .addComponent(txt_breed)
                                    .addComponent(txt_id_owner)
                                    .addComponent(txt_cod_pet))))))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_breed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txt_id_owner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cod_pet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_add)
                    .addComponent(btn_edit)
                    .addComponent(btn_delete))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
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

    private void txt_id_ownerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_ownerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_ownerActionPerformed

    private void tb_petsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tb_petsMouseClicked
        // TODO add your handling code here:
        int row = tb_pets.getSelectedRow();
        System.out.println(row);
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Debes seleccionar una mascota");

        } else {
            int cod = Integer.parseInt((String) tb_pets.getValueAt(row, 0).toString());
            String name = (String) tb_pets.getValueAt(row, 1);

            String breed = (String) tb_pets.getValueAt(row, 2);

            int document = Integer.parseInt((String) tb_pets.getValueAt(row, 3).toString());

            txt_cod_pet.setText("" + cod);
            txt_name.setText("" + name);
            txt_breed.setText("" + breed);
            txt_id_owner.setText("" + document);
        }
    }//GEN-LAST:event_tb_petsMouseClicked

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed
        // TODO add your handling code here:
        add_pet();
    }//GEN-LAST:event_btn_addActionPerformed

    private void btn_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editActionPerformed
        // TODO add your handling code here:
        edit_pet();
    }//GEN-LAST:event_btn_editActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        delete_pet();
    }//GEN-LAST:event_btn_deleteActionPerformed

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
            java.util.logging.Logger.getLogger(Pet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Pet dialog = new Pet(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_pets;
    private javax.swing.JTextField txt_breed;
    private javax.swing.JTextField txt_cod_pet;
    private javax.swing.JTextField txt_id_owner;
    private javax.swing.JTextField txt_name;
    // End of variables declaration//GEN-END:variables
}
