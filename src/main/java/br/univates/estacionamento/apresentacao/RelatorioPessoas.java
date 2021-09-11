/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univates.estacionamento.apresentacao;

import br.univates.estacionamento.negocio.Vip;
import br.univates.system32.reports.Report;
import java.util.HashMap;

/**
 *
 * @author joaoh
 */
public class RelatorioPessoas implements Report
{
    private Vip    vip;
    private String nomeIni;
    private String nomeFinal;
    
    public void setVip(Vip vip)
    {
        this.vip = vip;
    }
    
    public void setNomeInicial(String nomeIni)
    {
        this.nomeIni = nomeIni;
    }
    
    public void setNomeFinal(String nomeFinal)
    {
        this.nomeFinal = nomeFinal;
    }
    
    
    @Override
    public HashMap getParameters()
    {
        HashMap map = new HashMap();
        map.put("vip_id",    vip.getIdVip() );
        map.put("descricao", vip.getDescricao() );
        map.put("nomeIni",   this.nomeIni.trim() );
        map.put("nomeFinal", this.nomeFinal.trim()+"zzzzzzzzzzzzzz" );
        
        return map;
    }

    @Override
    public String getPath()
    {
        return "/br/univates/estacionamento/resources/reports/RelatorioPessoasFiltros.jrxml";
    }
    
}
