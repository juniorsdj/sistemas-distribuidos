/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefa_02_sd;

import java.io.Serializable;

/**
 *
 * @author QueroDelivery
 */
public class Mensagem implements Serializable {

    private EnumTipoMensagem type;
    private Object body;
    private String mensagem;

    /**
     * @return the body
     */
    public Object getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(Object body) {
        this.body = body;
    }

    /**
     * @return the mensagem
     */
    public String getMensagem() {
        return mensagem;
    }

    /**
     * @param mensagem the mensagem to set
     */
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    /**
     * @return the type
     */
    public EnumTipoMensagem getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(EnumTipoMensagem type) {
        this.type = type;
    }
}
