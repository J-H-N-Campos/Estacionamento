/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Caixa;
import br.univates.estacionamento.persistencia.BDFactory;
import br.univates.estacionamento.persistencia.EstacionamentoCaixaBD;
import br.univates.system32.db.DataBaseException;
import br.univates.system32.db.DuplicateKeyException;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author joaoh
 */
public class TelaCadastroCaixa extends javax.swing.JFrame
{
    private boolean novo;
    private Caixa caixa;
    private TelaEstacionamento telaEstacionamento;

    public TelaCadastroCaixa()
    {
        novo = false;
        this.caixa = caixa;
        initComponents();
        
        ArrayList<Caixa> caixas = new ArrayList();
        try 
        {
            EstacionamentoCaixaBD bd = BDFactory.newCaixaBD();
            caixas = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        CaixasTableModel tm = new CaixasTableModel(caixas);
        tabelaCaixa.setModel(tm);
        tabelaCaixa.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ListSelectionModel selectionModel = tabelaCaixa.getSelectionModel();
        selectionModel.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            { 
                int row = tabelaCaixa.getSelectedRow();
                
                if (row >=0)
                {
                    CaixasTableModel tableModel = (CaixasTableModel)tabelaCaixa.getModel();
                    caixa = tableModel.getCaixas().get(tabelaCaixa.getSelectedRow());
                    campoFormatadoCodigo.setInteger( caixa.getIdCaixa());
                    campoFormatadoPreco.setText(caixa.getValor());
                    campoFormatadoDescricao.setText( caixa.getDescricao());
                    novo = false;
                }
            }
        } );
    }
    
    public void setTelaEstacionamento(TelaEstacionamento telaEstacionamento)
    {
        this.telaEstacionamento = telaEstacionamento;
    }
    
    public void atualizarTabela()
    {
        ArrayList<Caixa> caixas = new ArrayList();
        try 
        {
            EstacionamentoCaixaBD bd = BDFactory.newCaixaBD();
            caixas = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        CaixasTableModel tableModel = (CaixasTableModel)tabelaCaixa.getModel();
        
        tableModel.setCaixas(caixas);
        tabelaCaixa.revalidate();
        tabelaCaixa.repaint();
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoNovo = new javax.swing.JButton();
        botaoDeletar = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCaixa = new javax.swing.JTable();
        campoFormatadoCodigo = new br.univates.system32.components.JMyNumberField();
        campoFormatadoDescricao = new javax.swing.JFormattedTextField();
        campoFormatadoPreco = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Valores");
        setName("Cadastro de Valores"); // NOI18N
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

        botaoNovo.setBackground(new java.awt.Color(204, 204, 204));
        botaoNovo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoNovo.setText("Novo");
        botaoNovo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        botaoDeletar.setBackground(new java.awt.Color(204, 204, 204));
        botaoDeletar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoDeletar.setText("Deletar");
        botaoDeletar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });

        botaoSalvar.setBackground(new java.awt.Color(204, 204, 204));
        botaoSalvar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoSalvar.setText("Salvar");
        botaoSalvar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        tabelaCaixa.setBackground(new java.awt.Color(153, 153, 153));
        tabelaCaixa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        tabelaCaixa.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        tabelaCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Código", "Preço", "Descrição"
            }
        ));
        jScrollPane1.setViewportView(tabelaCaixa);

        campoFormatadoCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoCodigo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        campoFormatadoDescricao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoDescricao.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        campoFormatadoPreco.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoPreco.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("Código *");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setText("Preço *");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel3.setText("Descrição *");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 583, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoDeletar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoNovo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(botaoSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(campoFormatadoDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                            .addComponent(campoFormatadoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoDeletar, botaoFechar, botaoNovo, botaoSalvar, campoFormatadoDescricao, campoFormatadoPreco});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoNovo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoFormatadoPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(botaoSalvar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoFormatadoDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(botaoDeletar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botaoDeletar, botaoFechar, botaoNovo, botaoSalvar});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoFormatadoCodigo, campoFormatadoDescricao, campoFormatadoPreco});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        telaEstacionamento.setVisible(true);
        dispose();
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        novo = true;
        campoFormatadoCodigo.setText("");
        campoFormatadoPreco.setText("");
        campoFormatadoDescricao.setText("");
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        int id           = campoFormatadoCodigo.getInteger();
        String preco     = campoFormatadoPreco.getText();
        String descricao = campoFormatadoDescricao.getText();
     
        try 
        {
            EstacionamentoCaixaBD bd = BDFactory.newCaixaBD();
            if (novo)
            {
                caixa = new Caixa(id, preco, descricao);
                bd.create(caixa);
                novo = false;
            }
            else
            {
                caixa.setValor(preco);
                caixa.setDescricao(descricao);
                bd.edit(caixa);
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
        
        atualizarTabela();
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        try
        {
            EstacionamentoCaixaBD bd = BDFactory.newCaixaBD();
            bd.delete(caixa);
        } 
        catch (DataBaseException ex)
        {
            System.out.println(ex.getMessage());
        }

        atualizarTabela();
    }//GEN-LAST:event_botaoDeletarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoSalvar;
    private br.univates.system32.components.JMyNumberField campoFormatadoCodigo;
    private javax.swing.JFormattedTextField campoFormatadoDescricao;
    private javax.swing.JFormattedTextField campoFormatadoPreco;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaCaixa;
    // End of variables declaration//GEN-END:variables
}
