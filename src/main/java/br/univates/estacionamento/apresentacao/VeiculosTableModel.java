/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Veiculo;
import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

/**
 *
 * @author joaoh
 */
public class VeiculosTableModel implements TableModel
{
    private ArrayList<Veiculo> veiculos;
    
    public VeiculosTableModel(ArrayList<Veiculo> veiculos)
    {
        this.veiculos = veiculos;
    }

    public ArrayList<Veiculo> getVeiculos()
    {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos)
    {
        this.veiculos = veiculos;
    }
    
    @Override
    public int getRowCount()
    {
        return veiculos.size();
    }

    @Override
    public int getColumnCount()
    {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex)
    {
        String[] vet = { "Código", "Cliente", "Modelo", "Placa"};
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
        Veiculo aux = veiculos.get( rowIndex );
        Object[] vet = { aux.getId(), aux.getPessoa().getNome(), aux.getPlaca(), aux.getModelo()};
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