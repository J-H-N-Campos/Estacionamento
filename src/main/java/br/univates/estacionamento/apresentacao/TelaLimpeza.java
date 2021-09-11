/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Limpeza;
import br.univates.estacionamento.negocio.Veiculo;
import br.univates.estacionamento.persistencia.BDFactory;
import br.univates.estacionamento.persistencia.EstacionamentoLimpezaBD;
import br.univates.estacionamento.persistencia.EstacionamentoVeiculoBD;
import br.univates.system32.db.DataBaseException;
import br.univates.system32.db.DuplicateKeyException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author joaoh
 */
public class TelaLimpeza extends javax.swing.JFrame
{
    private TelaEstacionamento telaEstacionamento;
    private boolean novo;
    private Limpeza limpeza;

    public TelaLimpeza()
    {
        novo = false;
        initComponents();
        
         // popular comboBox
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
        
        ArrayList<Limpeza> limpezas = new ArrayList();
        try 
        {
            EstacionamentoLimpezaBD bd = BDFactory.newLimpezaBD();
            limpezas = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
    }
    
    public void setTelaEstacionamento(TelaEstacionamento telaEstacionamento)
    {
        this.telaEstacionamento = telaEstacionamento;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        cbVeiculo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        botaoLimpeza = new javax.swing.JButton();
        botaoNovo = new javax.swing.JButton();
        campoFormatadoCodigo = new br.univates.system32.components.JMyNumberField();
        jLabel2 = new javax.swing.JLabel();
        botaoDeletar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela Cadastro de Limpeza");
        setName("Tela Cadastro de Limpeza"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        botaoFechar.setBackground(new java.awt.Color(204, 204, 204));
        botaoFechar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoFechar.setText("Fechar");
        botaoFechar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        cbVeiculo.setBackground(new java.awt.Color(204, 204, 204));
        cbVeiculo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        cbVeiculo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        cbVeiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVeiculoActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("Veículo");

        botaoLimpeza.setBackground(new java.awt.Color(204, 204, 204));
        botaoLimpeza.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoLimpeza.setText("Confirmar");
        botaoLimpeza.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoLimpeza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimpezaActionPerformed(evt);
            }
        });

        botaoNovo.setBackground(new java.awt.Color(204, 204, 204));
        botaoNovo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoNovo.setText("Novo");
        botaoNovo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        campoFormatadoCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoCodigo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        campoFormatadoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFormatadoCodigoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setText("Código");

        botaoDeletar.setBackground(new java.awt.Color(204, 204, 204));
        botaoDeletar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoDeletar.setText("Deletar Dados");
        botaoDeletar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botaoDeletar)
                    .addComponent(botaoNovo)
                    .addComponent(cbVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpeza, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoDeletar, botaoFechar, botaoLimpeza, botaoNovo, cbVeiculo});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbVeiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpeza, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoDeletar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botaoDeletar, botaoFechar, botaoLimpeza, botaoNovo, campoFormatadoCodigo, cbVeiculo});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2});

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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        telaEstacionamento.setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
         dispose();
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void cbVeiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVeiculoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVeiculoActionPerformed

    private void botaoLimpezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimpezaActionPerformed
        int id            = campoFormatadoCodigo.getInteger();
        Veiculo veiculo   = (Veiculo)cbVeiculo.getSelectedItem();
        
        try
        {
            EstacionamentoLimpezaBD bd = BDFactory.newLimpezaBD();
            if (novo)
            {
                    limpeza = new Limpeza(id, veiculo);
                    bd.create(limpeza);
                    novo = false;
                    JOptionPane.showMessageDialog(null, "Encaminhando o carro para a limpeza.");
                    JOptionPane.showMessageDialog(null, "Carro limpo!!");
                }
                else
                {
                    bd.edit(limpeza);
                }
            }
            catch (DuplicateKeyException ex)
            {
                System.out.println("Chave duplicada");
            }
            catch (DataBaseException ex)
            {
                System.out.println( ex.getMessage() );
            }
    }//GEN-LAST:event_botaoLimpezaActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        novo = true;
        campoFormatadoCodigo.setText("");
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void campoFormatadoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFormatadoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFormatadoCodigoActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
       try
       {
           EstacionamentoLimpezaBD bd = BDFactory.newLimpezaBD();
            bd.delete(limpeza);
            JOptionPane.showMessageDialog(null, "Dados Deletados!!");
        } 
        catch (DataBaseException ex)
        {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_botaoDeletarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoLimpeza;
    private javax.swing.JButton botaoNovo;
    private br.univates.system32.components.JMyNumberField campoFormatadoCodigo;
    private javax.swing.JComboBox<Veiculo> cbVeiculo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
