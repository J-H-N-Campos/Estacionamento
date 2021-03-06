/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Usuario;
import br.univates.estacionamento.persistencia.BDFactory;
import br.univates.estacionamento.persistencia.EstacionamentoUsuarioBD;
import br.univates.system32.db.DataBaseException;
import br.univates.system32.db.DuplicateKeyException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author joaoh
 */
public class TelaCadastroUsuario extends javax.swing.JFrame
{
    private Usuario usuario;
    private TelaMenu telaMenu;
    private boolean novo;
    
    public TelaCadastroUsuario(Usuario u)
    {
        novo = false;
        this.usuario = usuario;
        initComponents();
        
        ArrayList<Usuario> usuarios = new ArrayList();
        try 
        {
            EstacionamentoUsuarioBD bd = BDFactory.newUsuarioBD();
            usuarios = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        UsuariosTableModel tm = new UsuariosTableModel(usuarios);
        tabelaCadastroUsuarios.setModel(tm);
        tabelaCadastroUsuarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        ListSelectionModel selectionModel = tabelaCadastroUsuarios.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            { 
                int row = tabelaCadastroUsuarios.getSelectedRow();
                
                if (row >=0)
                {
                    UsuariosTableModel tableModel = (UsuariosTableModel)tabelaCadastroUsuarios.getModel();
                    usuario = tableModel.getUsuarios().get(tabelaCadastroUsuarios.getSelectedRow());
                    campoFormatadoCodigo.setInteger(usuario.getId());
                    campoFormatadoLogin.setText(usuario.getLogin() );
                    campoFormatadoSenha.setText(usuario.getPassword());
                    novo = false;
                }
            }
        } );
    }
    
    public void atualizarTabela()
    {
        ArrayList<Usuario> usuarios = new ArrayList();
        try 
        {
            EstacionamentoUsuarioBD bd = BDFactory.newUsuarioBD();
            usuarios = bd.readAll();
        } 
        catch (DataBaseException ex) 
        {
            System.out.println( ex.getMessage() );
        }
        
        UsuariosTableModel tableModel = (UsuariosTableModel)tabelaCadastroUsuarios.getModel();
        
        tableModel.setUsuarios(usuarios);
        tabelaCadastroUsuarios.revalidate();
        tabelaCadastroUsuarios.repaint();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCadastroUsuarios = new javax.swing.JTable();
        botaoNovo = new javax.swing.JButton();
        botaoSalvar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        rotuloLogin = new javax.swing.JLabel();
        rotuloSenha = new javax.swing.JLabel();
        campoFormatadoLogin = new javax.swing.JFormattedTextField();
        campoFormatadoSenha = new javax.swing.JPasswordField();
        botaoDeletar = new javax.swing.JButton();
        campoFormatadoCodigo = new br.univates.system32.components.JMyNumberField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Tela Cadastro de Usu??rios");
        setName("Tela Cadastro de Usu??rios"); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        tabelaCadastroUsuarios.setBackground(new java.awt.Color(153, 153, 153));
        tabelaCadastroUsuarios.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        tabelaCadastroUsuarios.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        tabelaCadastroUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "C??digo", "Login"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabelaCadastroUsuarios);

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

        botaoFechar.setBackground(new java.awt.Color(204, 204, 204));
        botaoFechar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoFechar.setText("Fechar");
        botaoFechar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        rotuloLogin.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        rotuloLogin.setText("Login *");

        rotuloSenha.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        rotuloSenha.setText("Senha *");

        campoFormatadoLogin.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoLogin.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N

        campoFormatadoSenha.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        campoFormatadoSenha.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));

        botaoDeletar.setBackground(new java.awt.Color(204, 204, 204));
        botaoDeletar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        botaoDeletar.setText("Deletar");
        botaoDeletar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        botaoDeletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDeletarActionPerformed(evt);
            }
        });

        campoFormatadoCodigo.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 3, true));
        campoFormatadoCodigo.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("C??digo *");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setText("Senha Inalter??vel");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rotuloLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rotuloSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoFormatadoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(campoFormatadoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                                        .addComponent(jLabel1)
                                        .addGap(30, 30, 30)
                                        .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(15, 15, 15))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel2)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(36, 36, 36))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(botaoDeletar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botaoFechar)
                        .addGap(29, 29, 29))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {campoFormatadoLogin, campoFormatadoSenha});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoDeletar, botaoFechar, botaoNovo, botaoSalvar});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, rotuloLogin});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rotuloLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(campoFormatadoLogin)
                    .addComponent(campoFormatadoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(2, 2, 2)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoFormatadoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rotuloSenha))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSalvar)
                    .addComponent(botaoDeletar)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botaoDeletar, botaoFechar, botaoNovo, botaoSalvar});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, rotuloLogin, rotuloSenha});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {campoFormatadoCodigo, campoFormatadoLogin, campoFormatadoSenha});

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

    private void botaoNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoActionPerformed
         
        novo = true;
        campoFormatadoCodigo.setText("");
        campoFormatadoLogin.setText("");
        campoFormatadoSenha.setText("");
    }//GEN-LAST:event_botaoNovoActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
        
        int id       = campoFormatadoCodigo.getInteger();
        String login = campoFormatadoLogin.getText();
        String senha = campoFormatadoSenha.getText();
        
            try
            {
                EstacionamentoUsuarioBD bd = BDFactory.newUsuarioBD();
                
                if (novo)
                {
                    usuario = new Usuario(id, login, senha.hashCode()+""); 
                    bd.create(usuario);
                    novo = false;
                }else
                {
                    usuario.setId(id);
                    usuario.setLogin(login);
                    usuario.setPassword(senha.hashCode()+"");
                    bd.edit(usuario);
                }
            }catch (DuplicateKeyException ex){
                System.out.println("Chave duplicada");
            }
            catch (DataBaseException ex){
                System.out.println( ex.getMessage() );
            }
            atualizarTabela();
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDeletarActionPerformed
       
        try
        {
            EstacionamentoUsuarioBD bd = BDFactory.newUsuarioBD();
            bd.delete(usuario);
        } 
        catch (DataBaseException ex)
        {
            System.out.println(ex.getMessage());
        }

        atualizarTabela();
    }//GEN-LAST:event_botaoDeletarActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        dispose();
        telaMenu.setVisible(true);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        dispose();
        telaMenu.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoDeletar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoNovo;
    private javax.swing.JButton botaoSalvar;
    private br.univates.system32.components.JMyNumberField campoFormatadoCodigo;
    private javax.swing.JFormattedTextField campoFormatadoLogin;
    private javax.swing.JPasswordField campoFormatadoSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel rotuloLogin;
    private javax.swing.JLabel rotuloSenha;
    private javax.swing.JTable tabelaCadastroUsuarios;
    // End of variables declaration//GEN-END:variables
}