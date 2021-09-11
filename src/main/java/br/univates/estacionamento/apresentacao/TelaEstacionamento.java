/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.main.Sistema;
import br.univates.estacionamento.negocio.Caixa;
import br.univates.estacionamento.negocio.Movimento;
import br.univates.estacionamento.negocio.Estacionamento;
import br.univates.estacionamento.negocio.Pessoa;
import br.univates.estacionamento.negocio.Veiculo;
import br.univates.estacionamento.negocio.Vip;
import br.univates.estacionamento.persistencia.BDFactory;
import br.univates.estacionamento.persistencia.EstacionamentoBD;
import br.univates.estacionamento.persistencia.EstacionamentoCaixaBD;
import br.univates.estacionamento.persistencia.EstacionamentoMovimentoBD;
import br.univates.estacionamento.persistencia.EstacionamentoPessoaBD;
import br.univates.estacionamento.persistencia.EstacionamentoVeiculoBD;
import br.univates.estacionamento.persistencia.EstacionamentoVipBD;
import br.univates.system32.db.DataBaseException;
import br.univates.system32.db.DuplicateKeyException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author joaoh
 */
public class TelaEstacionamento extends javax.swing.JFrame
{
    private Estacionamento          estacionamento;
    private TelaMenu                telaMenu;
    private Movimento               data;
    private Pessoa                  pessoa;
    private Vip                     vip;
    private Veiculo                 veiculo;
    private TelaCadastroMovimento   telaDataRegistro;
    private boolean                 novo;
            
    public TelaEstacionamento()
    {
        this.estacionamento = estacionamento;
        this.novo           = false;
        initComponents();
        
       // popular comboBox
        ArrayList<Pessoa> pessoas = new ArrayList();
        try 
        {
            EstacionamentoPessoaBD bd = BDFactory.newPessoaBD();
            pessoas = bd.readAll();
            
            this.cbNome.removeAllItems();
            for (Pessoa pessoa: pessoas)
            {
                this.cbNome.addItem(pessoa);
            }
            
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }

        ArrayList<Veiculo> veiculos = new ArrayList();
        try 
        {
            EstacionamentoVeiculoBD bd = BDFactory.newVeiculoBD();
            veiculos = bd.readAll();
            
            this.cbPlaca.removeAllItems();
            for (Veiculo veiculo: veiculos)
            {
                this.cbPlaca.addItem(veiculo);
            }
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        ArrayList<Movimento> movimentos = new ArrayList();
        try 
        {
            EstacionamentoMovimentoBD bd = BDFactory.newMovimentoBD();
            movimentos = bd.readAll();
            
            this.cbRegistro.removeAllItems();
            for (Movimento movimento: movimentos)
            {
                this.cbRegistro.addItem(movimento);
            }
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
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
        
        ArrayList<Caixa> caixas = new ArrayList();
        try 
        {
            EstacionamentoCaixaBD bd = BDFactory.newCaixaBD();
            caixas = bd.readAll();
            
            this.cbCaixa.removeAllItems();
            for (Caixa caixa: caixas)
            {
                this.cbCaixa.addItem(caixa);
            }
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        ArrayList<Estacionamento> vagas = new ArrayList();
        try 
        {
            EstacionamentoBD bd = BDFactory.newEstacionamentoBD();
            vagas = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        EstacionamentosTableModel tm = new EstacionamentosTableModel(vagas);
        tabelaEstacionamento.setModel(tm);
        tabelaEstacionamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ListSelectionModel selectionModel = tabelaEstacionamento.getSelectionModel();
        selectionModel.addListSelectionListener( new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            { 
                int row = tabelaEstacionamento.getSelectedRow();
                
                if (row >=0)
                {
                    EstacionamentosTableModel tableModel = (EstacionamentosTableModel)tabelaEstacionamento.getModel();
                    estacionamento = tableModel.getEstacionamento().get(tabelaEstacionamento.getSelectedRow());
                    campoFormatadoCodigo.setInteger(estacionamento.getIdVaga());
                    cbNome.setSelectedItem(estacionamento.getPessoa());
                    cbPlaca.setSelectedItem(estacionamento.getVeiculo());
                    cbRegistro.setSelectedItem(estacionamento.getMovimento());
                    cbVip.setSelectedItem(estacionamento.getVip());
                    cbCaixa.setSelectedItem(estacionamento.getCaixa());
                    
                    novo = false;
                }
            }
        } );        
    }

    public void atualizarTabela()
    {
        ArrayList<Estacionamento> vagas = new ArrayList();
        try 
        {
            EstacionamentoBD bd = BDFactory.newEstacionamentoBD();
            vagas = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        EstacionamentosTableModel tableModel = (EstacionamentosTableModel)tabelaEstacionamento.getModel();
        
        tableModel.setMovimentos(vagas);
        tabelaEstacionamento.revalidate();
        tabelaEstacionamento.repaint();
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
        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoRegistrarEntradaSaida = new javax.swing.JButton();
        botaoCaixa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaEstacionamento = new javax.swing.JTable();
        campoFormatadoCodigo = new br.univates.system32.components.JMyNumberField();
        jLabel1 = new javax.swing.JLabel();
        botaoSalvar = new javax.swing.JButton();
        botaoDeletar = new javax.swing.JButton();
        botaoNovo = new javax.swing.JButton();
        cbNome = new javax.swing.JComboBox<>();
        cbPlaca = new javax.swing.JComboBox<>();
        botaoLimpeza = new javax.swing.JButton();
        cbRegistro = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbVip = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbCaixa = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela de estacionamento");
        setName("Tela de estacionamento"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));
        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        botaoFechar.setBackground(new java.awt.Color(204, 204, 204));
        botaoFechar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoFechar.setText("Fechar");
        botaoFechar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        botaoRegistrarEntradaSaida.setBackground(new java.awt.Color(204, 204, 204));
        botaoRegistrarEntradaSaida.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoRegistrarEntradaSaida.setText("Registrar Entrada/Saída");
        botaoRegistrarEntradaSaida.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoRegistrarEntradaSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRegistrarEntradaSaidaActionPerformed(evt);
            }
        });

        botaoCaixa.setBackground(new java.awt.Color(204, 204, 204));
        botaoCaixa.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoCaixa.setText("Registrar Valores");
        botaoCaixa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCaixaActionPerformed(evt);
            }
        });

        tabelaEstacionamento.setBackground(new java.awt.Color(153, 153, 153));
        tabelaEstacionamento.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        tabelaEstacionamento.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        tabelaEstacionamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Código", "Nome", "Placa", "Data", "Vip", "Valor"
            }
        ));
        jScrollPane1.setViewportView(tabelaEstacionamento);

        campoFormatadoCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoCodigo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("Código *");

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

        botaoNovo.setBackground(new java.awt.Color(204, 204, 204));
        botaoNovo.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoNovo.setText("Novo");
        botaoNovo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoActionPerformed(evt);
            }
        });

        cbNome.setBackground(new java.awt.Color(204, 204, 204));
        cbNome.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        cbNome.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        cbPlaca.setBackground(new java.awt.Color(204, 204, 204));
        cbPlaca.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        cbPlaca.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        botaoLimpeza.setBackground(new java.awt.Color(204, 204, 204));
        botaoLimpeza.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoLimpeza.setText("Lava Jato");
        botaoLimpeza.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoLimpeza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimpezaActionPerformed(evt);
            }
        });

        cbRegistro.setBackground(new java.awt.Color(204, 204, 204));
        cbRegistro.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        cbRegistro.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        cbRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbRegistroActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setText("Cliente *");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel3.setText("Veículo *");

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel4.setText("Registro *");

        cbVip.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        cbVip.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel5.setText("Vip *");

        jLabel6.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel6.setText("Valores *");

        cbCaixa.setBackground(new java.awt.Color(204, 204, 204));
        cbCaixa.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        cbCaixa.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(botaoNovo)
                            .addComponent(botaoSalvar)
                            .addComponent(botaoDeletar)
                            .addComponent(botaoLimpeza, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(botaoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoRegistrarEntradaSaida)
                            .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbVip, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNome, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPlaca, 0, 280, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbRegistro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(65, 65, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoCaixa, botaoDeletar, botaoFechar, botaoLimpeza, botaoNovo, botaoRegistrarEntradaSaida, botaoSalvar});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel6});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cbCaixa, cbNome, cbPlaca, cbRegistro, cbVip});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(cbCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNovo)
                    .addComponent(botaoRegistrarEntradaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoSalvar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botaoDeletar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoLimpeza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbVip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botaoCaixa, botaoDeletar, botaoFechar, botaoLimpeza, botaoNovo, botaoRegistrarEntradaSaida, botaoSalvar, cbCaixa, cbNome, cbPlaca, cbRegistro, cbVip});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel6});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        telaMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void botaoRegistrarEntradaSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRegistrarEntradaSaidaActionPerformed
        TelaCadastroMovimento tdr = new TelaCadastroMovimento(data);
        tdr.setTelaEstacionamento(this);
        tdr.setVisible(true);
    }//GEN-LAST:event_botaoRegistrarEntradaSaidaActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        dispose();
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
        try
        {
            EstacionamentoBD bd = BDFactory.newEstacionamentoBD();
            bd.delete(estacionamento);
        } 
        catch (DataBaseException ex)
        {
            System.out.println(ex.getMessage());
        }

        atualizarTabela();
    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        int id                      = campoFormatadoCodigo.getInteger();
        Pessoa pessoa               = (Pessoa)cbNome.getSelectedItem();
        Veiculo veiculo             = (Veiculo)cbPlaca.getSelectedItem();
        Movimento movimento         = (Movimento)cbRegistro.getSelectedItem();
        Vip vip                     = (Vip)cbVip.getSelectedItem();
        Caixa caixa                 = (Caixa)cbCaixa.getSelectedItem();
        
        try
        {
            EstacionamentoBD bd = BDFactory.newEstacionamentoBD();
            
            if (novo)
            {
                estacionamento = new Estacionamento(id, pessoa, veiculo, movimento, vip, caixa); 
                bd.create(estacionamento);
                novo = false;
            }
            else
            {
                estacionamento.setIdVaga(id);
                estacionamento.setPessoa(pessoa);
                estacionamento.setVeiculo(veiculo);
                estacionamento.setMovimento(movimento);
                estacionamento.setVip(vip);
                estacionamento.setCaixa(caixa);
                bd.edit(estacionamento);
            }
        }catch (DuplicateKeyException ex)
        {
            System.out.println("Chave duplicada");
        }catch (DataBaseException ex)
        {
            System.out.println( ex.getMessage() );
        }
        atualizarTabela();
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
        novo = true;
        campoFormatadoCodigo.setText("");
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoLimpezaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimpezaActionPerformed
        TelaLimpeza l = new TelaLimpeza();
        l.setTelaEstacionamento(this);
        l.setVisible(true);
    }//GEN-LAST:event_botaoLimpezaActionPerformed

    private void cbRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbRegistroActionPerformed

    private void botaoCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCaixaActionPerformed
        TelaCadastroCaixa c = new TelaCadastroCaixa();
        c.setTelaEstacionamento(this);
        c.setVisible(true);
    }//GEN-LAST:event_botaoCaixaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoCaixa;
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoLimpeza;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoRegistrarEntradaSaida;
    private javax.swing.JButton botaoSalvar;
    private br.univates.system32.components.JMyNumberField campoFormatadoCodigo;
    private javax.swing.JComboBox<Caixa> cbCaixa;
    private javax.swing.JComboBox<Pessoa> cbNome;
    private javax.swing.JComboBox<Veiculo> cbPlaca;
    private javax.swing.JComboBox<Movimento> cbRegistro;
    private javax.swing.JComboBox<Vip> cbVip;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaEstacionamento;
    // End of variables declaration//GEN-END:variables
}
