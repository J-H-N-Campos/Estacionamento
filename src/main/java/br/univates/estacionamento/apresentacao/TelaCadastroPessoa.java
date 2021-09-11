/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Movimento;
import br.univates.estacionamento.negocio.Pessoa;
import br.univates.estacionamento.negocio.Veiculo;
import br.univates.estacionamento.negocio.Vip;
import br.univates.estacionamento.persistencia.BDFactory;
import br.univates.estacionamento.persistencia.EstacionamentoMovimentoBD;
import br.univates.system32.db.DataBaseException;
import br.univates.system32.db.DuplicateKeyException;
import java.util.ArrayList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import br.univates.estacionamento.persistencia.EstacionamentoPessoaBD;
import br.univates.estacionamento.persistencia.EstacionamentoVeiculoBD;
import br.univates.estacionamento.persistencia.EstacionamentoVipBD;
import javax.swing.JOptionPane;


/**
 *
 * @author joaoh
 */
public class TelaCadastroPessoa extends javax.swing.JFrame
{
    private Pessoa      pessoa;
    private Veiculo     veiculo;
    private Movimento   movimento;
    private TelaMenu    telaMenu;
    private boolean     novo;
    
    public TelaCadastroPessoa(Pessoa p)
    {
        novo = false;
        initComponents();
        
        // popular comboBox
        ArrayList<Vip> vips = new ArrayList();
        try 
        {
            EstacionamentoVipBD bd = BDFactory.newVipBD();
            vips = bd.readAll();
            
            this.cbVip.removeAllItems();
            for (Vip vip: vips)
            {
                this.cbVip.addItem(vip);
            }
            
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }

        ArrayList<Pessoa> pessoas = new ArrayList();
        try 
        {
            EstacionamentoPessoaBD bd = BDFactory.newPessoaBD();
            pessoas = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        PessoasTableModel tm = new PessoasTableModel(pessoas);
        tabelaCadastroPessoas.setModel(tm);
        tabelaCadastroPessoas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ListSelectionModel selectionModel = tabelaCadastroPessoas.getSelectionModel();
        selectionModel.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            { 
                int row = tabelaCadastroPessoas.getSelectedRow();
                
                if (row >=0)
                {
                    PessoasTableModel tableModel = (PessoasTableModel)tabelaCadastroPessoas.getModel();
                    pessoa = tableModel.getPessoas().get(tabelaCadastroPessoas.getSelectedRow());
                    campoFormatadoCodigo.setInteger(pessoa.getId() );
                    campoFormatadoNome.setText(pessoa.getNome() );
                    campoFormatadoEndereco.setText(pessoa.getEndereco() );
                    campoFormatadoCPF.setText(pessoa.getCPF() );
                    campoFormatadoIdade.setInteger(pessoa.getIdade() );
                    campoFormatadoLogradouro.setText(pessoa.getLogradouro() );
                    cbVip.setSelectedItem( pessoa.getVip());

                    novo = false;
                }
            }
        } );        
    }

    public void atualizarTabela()
    {
        ArrayList<Pessoa> pessoas = new ArrayList();
        try 
        {
            EstacionamentoPessoaBD bd = BDFactory.newPessoaBD();
            pessoas = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        PessoasTableModel tableModel = (PessoasTableModel)tabelaCadastroPessoas.getModel();
        
        tableModel.setPessoas(pessoas);
        tabelaCadastroPessoas.revalidate();
        tabelaCadastroPessoas.repaint();
    }
    
    public void setTelaMenu(TelaMenu telaMenu)
    {
        this.telaMenu = telaMenu;
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
        campoFormatadoCPF = new br.univates.system32.components.JMyCpfField();
        rotuloCPF = new javax.swing.JLabel();
        campoFormatadoEndereco = new javax.swing.JFormattedTextField();
        rotuloEndereco = new javax.swing.JLabel();
        campoFormatadoIdade = new br.univates.system32.components.JMyNumberField();
        rotuloIdade = new javax.swing.JLabel();
        campoFormatadoCodigo = new br.univates.system32.components.JMyNumberField();
        rotuloCodigo = new javax.swing.JLabel();
        rotuloNome = new javax.swing.JLabel();
        campoFormatadoNome = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCadastroPessoas = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        botaoNovo = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoDeletar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        cbVip = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        campoFormatadoLogradouro = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de Cadastro de Pessoas");
        setBackground(new java.awt.Color(153, 153, 153));
        setName("Tela de Cadastro de Pessoas"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        campoFormatadoCPF.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoCPF.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        campoFormatadoCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFormatadoCPFActionPerformed(evt);
            }
        });

        rotuloCPF.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        rotuloCPF.setText("CPF*");

        campoFormatadoEndereco.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoEndereco.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N

        rotuloEndereco.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        rotuloEndereco.setText("Endereço*");

        campoFormatadoIdade.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoIdade.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        campoFormatadoIdade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFormatadoIdadeActionPerformed(evt);
            }
        });

        rotuloIdade.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        rotuloIdade.setText("Idade*");

        campoFormatadoCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoCodigo.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        campoFormatadoCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFormatadoCodigoActionPerformed(evt);
            }
        });

        rotuloCodigo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        rotuloCodigo.setText("Código*");

        rotuloNome.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        rotuloNome.setText("Nome*");

        campoFormatadoNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoNome.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        campoFormatadoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoFormatadoNomeActionPerformed(evt);
            }
        });

        tabelaCadastroPessoas.setBackground(new java.awt.Color(153, 153, 153));
        tabelaCadastroPessoas.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        tabelaCadastroPessoas.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        tabelaCadastroPessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Endereço", "CPF", "Idade", "Categoria Vip", "Logradouro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelaCadastroPessoas.setToolTipText("");
        tabelaCadastroPessoas.setGridColor(new java.awt.Color(153, 153, 153));
        tabelaCadastroPessoas.setName(""); // NOI18N
        jScrollPane1.setViewportView(tabelaCadastroPessoas);

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));

        botaoNovo.setBackground(new java.awt.Color(204, 204, 204));
        botaoNovo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoNovo.setText("Novo");
        botaoNovo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
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

        botaoDeletar.setBackground(new java.awt.Color(204, 204, 204));
        botaoDeletar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoDeletar.setText("Deletar");
        botaoDeletar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });

        botaoFechar.setBackground(new java.awt.Color(204, 204, 204));
        botaoFechar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoFechar.setText("Fechar");
        botaoFechar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoDeletar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoDeletar, botaoFechar, botaoNovo, botaoSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoDeletar)
                    .addComponent(botaoSalvar)
                    .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botaoDeletar, botaoFechar, botaoNovo, botaoSalvar});

        cbVip.setBackground(new java.awt.Color(204, 204, 204));
        cbVip.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        cbVip.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        cbVip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVipActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("Categorias Vip");

        campoFormatadoLogradouro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoLogradouro.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setText("Logradouro");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rotuloNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rotuloCPF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rotuloEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                            .addComponent(rotuloIdade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rotuloCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(campoFormatadoIdade, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(campoFormatadoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(campoFormatadoNome)
                                        .addComponent(campoFormatadoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoFormatadoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(88, 88, 88)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(48, 48, 48)
                                            .addComponent(cbVip, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(83, 83, 83)
                                            .addComponent(jLabel2))))))
                        .addGap(0, 255, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {campoFormatadoCPF, campoFormatadoEndereco, campoFormatadoLogradouro, campoFormatadoNome, cbVip});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rotuloNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoFormatadoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbVip, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoFormatadoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rotuloEndereco)
                            .addComponent(jLabel2))))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloCPF)
                    .addComponent(campoFormatadoCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoFormatadoLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloCodigo)
                    .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rotuloIdade, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoFormatadoIdade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, rotuloCPF, rotuloCodigo, rotuloEndereco, rotuloIdade, rotuloNome});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoFormatadoCPF, campoFormatadoCodigo, campoFormatadoEndereco, campoFormatadoIdade, campoFormatadoLogradouro, campoFormatadoNome, cbVip});

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

    private void campoFormatadoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFormatadoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFormatadoNomeActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
         novo = true;
         campoFormatadoCodigo.setText("");
         campoFormatadoNome.setText("");
         campoFormatadoEndereco.setText("");
         campoFormatadoCPF.setText("");
         campoFormatadoIdade.setText("");
         campoFormatadoLogradouro.setText("");
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        int id                  = campoFormatadoCodigo.getInteger();
        String nome             = campoFormatadoNome.getText();
        String endereco         = campoFormatadoEndereco.getText();
        String cpf              = campoFormatadoCPF.getText();
        int idade               = campoFormatadoIdade.getInteger();
        Vip vip                 = (Vip)cbVip.getSelectedItem();
        String logradouro       = campoFormatadoLogradouro.getText();
        
        if(campoFormatadoCPF.isRight() && idade >= 18 && idade <=100 && id > 0 && !nome.isBlank() && !endereco.isBlank()&& !logradouro.isBlank())
        {
            try
            {
                EstacionamentoPessoaBD bd = BDFactory.newPessoaBD();
                
                if (novo)
                {
                    pessoa = new Pessoa(id, nome, endereco, cpf, idade, vip, logradouro); 
                    bd.create(pessoa);
                    novo = false;
                }
                else
                {
                    pessoa.setId(id);
                    pessoa.setNome(nome);
                    pessoa.setEndereco(endereco);
                    pessoa.setCPF(cpf);
                    pessoa.setIdade(idade);
                    pessoa.setVip(vip);
                    pessoa.setLogradouro(logradouro);
                    bd.edit(pessoa);
                }
            }catch (DuplicateKeyException ex)
            {
                System.out.println("Chave duplicada");
            }
            catch (DataBaseException ex)
            {
                System.out.println( ex.getMessage() );
            }
            atualizarTabela();
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Preencha todos os campos com um CPF válido e idade válida.");
        }
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
       dispose();
       telaMenu.setVisible(true);

    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        try
        {   
            EstacionamentoPessoaBD bd = BDFactory.newPessoaBD();
            bd.delete(pessoa);
        } 
        catch (DataBaseException ex)
        {
            System.out.println(ex.getMessage());
        }

        atualizarTabela();
    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void campoFormatadoCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFormatadoCodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoFormatadoCodigoActionPerformed

    private void campoFormatadoIdadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFormatadoIdadeActionPerformed
        
    }//GEN-LAST:event_campoFormatadoIdadeActionPerformed

    private void campoFormatadoCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoFormatadoCPFActionPerformed
    }//GEN-LAST:event_campoFormatadoCPFActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
        telaMenu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void cbVipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVipActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbVipActionPerformed
/**/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoSalvar;
    private br.univates.system32.components.JMyCpfField campoFormatadoCPF;
    private br.univates.system32.components.JMyNumberField campoFormatadoCodigo;
    private javax.swing.JFormattedTextField campoFormatadoEndereco;
    private br.univates.system32.components.JMyNumberField campoFormatadoIdade;
    private javax.swing.JFormattedTextField campoFormatadoLogradouro;
    private javax.swing.JFormattedTextField campoFormatadoNome;
    private javax.swing.JComboBox<Vip> cbVip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel rotuloCPF;
    private javax.swing.JLabel rotuloCodigo;
    private javax.swing.JLabel rotuloEndereco;
    private javax.swing.JLabel rotuloIdade;
    private javax.swing.JLabel rotuloNome;
    private javax.swing.JTable tabelaCadastroPessoas;
    // End of variables declaration//GEN-END:variables
}