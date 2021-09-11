/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Movimento;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author joaoh
 */
public class MovimentosTableModel implements TableModel
{
    private ArrayList<Movimento> movimentos;
    
    public MovimentosTableModel(ArrayList<Movimento> movimentos)
    {
        this.movimentos = movimentos;
    }

    public ArrayList<Movimento> getMovimentos()
    {
        return movimentos;
    }

    public void setMovimentos(ArrayList<Movimento> movimentos)
    {
        this.movimentos = movimentos;
    }
    
    @Override
    public int getRowCount()
    {
        return movimentos.size();
    }

    @Override
    public int getColumnCount()
    {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        String[] vet = { "Código","Entrada","Saída","Placa"};
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
        Movimento aux = movimentos.get( rowIndex );
        Object[] vet = { aux.getId(), aux.getDtIni(), aux.getDtFim(), aux.getVeiculo().getPlaca()};
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
