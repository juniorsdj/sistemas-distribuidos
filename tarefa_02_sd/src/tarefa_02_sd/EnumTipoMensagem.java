/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefa_02_sd;

/**
 *
 * @author QueroDelivery
 */
public enum EnumTipoMensagem {
    ADICIONAR(1), MENSAGEM(2), REMOVER(3), LISTAR_DISPONIVEIS(4);
     
    private final int valor;
    EnumTipoMensagem(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }
}
