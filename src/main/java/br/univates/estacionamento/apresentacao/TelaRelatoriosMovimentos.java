/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Veiculo;
import br.univates.estacionamento.persistencia.BDFactory;
import br.univates.estacionamento.persistencia.EstacionamentoVeiculoBD;
import br.univates.system32.db.DataBaseException;
import java.util.ArrayList;

/**
 *
 * @author joaoh
 */
public class TelaRelatoriosMovimentos extends javax.swing.JFrame
{
    private TelaCadastroMovimento telaCadastroMovimento;

    public TelaRelatoriosMovimentos()
    {
        this.telaCadastroMovimento = telaCadastroMovimento;
        initComponents();
        
        ArrayList<Veiculo> veiculos = new ArrayList();
        try 
        {
            EstacionamentoVeiculoBD bd = BDFactory.newVeiculoBD();
            veiculos = bd.readAll();
            
            this.cbVeiculo.removeAllItems();
            for (Veiculo veiculo: veiculos)
            {
                this.cbVeiculo.addItem(veiculo);
            }
            
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
    }
    
    public void setTelaCadastroMovimento(TelaCadastroMovimento telaCadastroMovimento)
    {
        this.telaCadastroMovimento = telaCadastroMovimento;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoCancelar = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        placa = new javax.swing.JLabel();
        cbVeiculo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Relatórios de Registros");
        setName("Relatórios de Registros"); // NOI18N
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        botaoCancelar.setBackground(new java.awt.Color(204, 204, 204));
        botaoCancelar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoCancelar.setText("Cancelar");
        botaoCancelar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoImprimir.setBackground(new java.awt.Color(204, 204, 204));
        botaoImprimir.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoImprimir.setText("Imprimir");
        botaoImprimir.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirActionPerformed(evt);
            }
        });

        placa.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        placa.setText("Placa");

        cbVeiculo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        cbVeiculo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(cbVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(botaoCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(placa, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCancelar, botaoImprimir});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(placa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoCancelar)
                    .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botaoCancelar, botaoImprimir});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
        telaCadastroMovimento.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed
        dispose();
        telaCadastroMovimento.setVisible(true);
    }//GEN-LAST:event_botaoCancelarActionPerformed

    private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed
        RelatorioMovimentos rel = new RelatorioMovimentos();
        rel.setVeiculo((Veiculo)this.cbVeiculo.getSelectedItem());

        Sistema.getInstance().getReportManager().createReport(rel);
        
        dispose();
        telaCadastroMovimento.setVisible(true);
    }//GEN-LAST:event_botaoImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoImprimir;
    private javax.swing.JComboBox<Veiculo> cbVeiculo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel placa;
    // End of variables declaration//GEN-END:variables
}
