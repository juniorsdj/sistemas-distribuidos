/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolLayer;

/**
 *
 * @author QueroDelivery
 */
public enum ENUM_MESSAGE {
    INSERT_RECORD_CLIENT(1), GET_ALL_RECORD_CLIENT(2), DEL_RECORD_CLIENT(3), MESSAGE(4), RECORD_CLIENT(5);
     
    private final int valor;
    ENUM_MESSAGE(int valorOpcao){
        valor = valorOpcao;
    }
    public int getValor(){
        return valor;
    }
}
