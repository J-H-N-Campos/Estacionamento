/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Pessoa;
import br.univates.system32.reports.Report;
import java.util.HashMap;

/**
 *
 * @author joaoh
 */
public class RelatorioVeiculos implements Report
{
    private String nomeIni;
    private String nomeFinal;
    private Pessoa pessoa_id;
    
    
    public void setNomeInicial(String nomeIni)
    {
        this.nomeIni = nomeIni;
    }
    
    public void setNomeFinal(String nomeFinal)
    {
        this.nomeFinal = nomeFinal;
    }
    
    public void setPessoa(Pessoa pessoa_id)
    {
        this.pessoa_id = pessoa_id;
    }
    
    @Override
    public HashMap getParameters()
    {
        HashMap map = new HashMap();
        map.put("pessoa_id", pessoa_id.getId());
        map.put("descricao", pessoa_id.getNome());
        map.put("nomeIni",   this.nomeIni.trim() );
        map.put("nomeFinal", this.nomeFinal.trim()+"zzzzzzzzzzzzzz" );
        
        return map;
    }

    @Override
    public String getPath()
    {
        return "/br/univates/estacionamento/resources/reports/RelatorioVeiculosFiltros.jrxml";
    }
}