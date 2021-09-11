/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Estacionamento;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author joaoh
 */
public class EstacionamentosTableModel implements TableModel
{
    private ArrayList<Estacionamento> estacionamentos;
    
    public EstacionamentosTableModel(ArrayList<Estacionamento> estacionamentos)
    {
        this.estacionamentos = estacionamentos;
    }

    public ArrayList<Estacionamento> getEstacionamento()
    {
        return estacionamentos;
    }

    public void setMovimentos(ArrayList<Estacionamento> estacionamentos)
    {
        this.estacionamentos = estacionamentos;
    }
    
    @Override
    public int getRowCount()
    {
        return estacionamentos.size();
    }

    @Override
    public int getColumnCount()
    {
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        String[] vet = { "Código","Cliente","Veículo","Registro Entrada","Registro Saída","Vip","Valor","Descrição"};
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
        Estacionamento aux = estacionamentos.get( rowIndex );
        Object[] vet = { aux.getIdVaga(), aux.getPessoa().getNome(), aux.getVeiculo().getPlaca(), aux.getMovimento().getDtIni(),aux.getMovimento().getDtFim(), aux.getVip().getDescricao(), aux.getCaixa().getValor(), aux.getCaixa().getDescricao()};
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