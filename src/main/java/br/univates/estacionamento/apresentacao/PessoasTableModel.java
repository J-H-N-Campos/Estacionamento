/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Pessoa;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author joaoh
 */
public class PessoasTableModel implements TableModel
{
    private ArrayList<Pessoa> pessoas;
    
    public PessoasTableModel(ArrayList<Pessoa> pessoas)
    {
        this.pessoas = pessoas;
    }

    public ArrayList<Pessoa> getPessoas()
    {
        return pessoas;
    }

    public void setPessoas(ArrayList<Pessoa> pessoas)
    {
        this.pessoas = pessoas;
    }
    
    @Override
    public int getRowCount()
    {
        return pessoas.size();
    }

    @Override
    public int getColumnCount()
    {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        String[] vet = { "Código","Nome","Endereço","Logradouro","CPF","Idade","Categoria Vip",};
        return vet[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if (columnIndex == 0)
        {
            return Integer.class;
        }
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Pessoa aux = pessoas.get( rowIndex );
        Object[] vet = { aux.getId(), aux.getNome(), aux.getEndereco(),aux.getLogradouro() ,aux.getCPF(), aux.getIdade(), aux.getVip().getDescricao()};
        return vet[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addTableModelListener(TableModelListener l)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeTableModelListener(TableModelListener l)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}