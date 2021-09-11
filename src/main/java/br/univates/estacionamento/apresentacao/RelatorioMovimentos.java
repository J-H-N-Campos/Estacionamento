/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Veiculo;
import br.univates.system32.reports.Report;
import java.util.HashMap;

/**
 *
 * @author joaoh
 */
public class RelatorioMovimentos implements Report
{
    private Veiculo veiculo;
    
    public void setVeiculo(Veiculo veiculo)
    {
        this.veiculo = veiculo;
    }
    
    @Override
    public HashMap getParameters()
    {
        HashMap map = new HashMap();
        map.put("veiculo_id",    veiculo.getId());
        map.put("placa",         veiculo.getPlaca());
        
        return map;
    }

    @Override
    public String getPath()
    {
        return "/br/univates/estacionamento/resources/reports/RelatorioRegistrosFiltros.jrxml";
    }
}