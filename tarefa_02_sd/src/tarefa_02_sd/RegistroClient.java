/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefa_02_sd;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author QueroDelivery
 */
class RegistroClient implements Serializable {

    private String nomeClient;
    private int portClient;
    private Boolean isDisponivel;
    private InetAddress ipClient;

    /**
     * @return the nomeClient
     */
    public String getNomeClient() {
        return nomeClient;
    }

    /**
     * @param nomeClient the nomeClient to set
     */
    public void setNomeClient(String nomeClient) {
        this.nomeClient = nomeClient;
    }

    /**
     * @return the portClient
     */
    public int getPortClient() {
        return portClient;
    }

    /**
     * @param portClient the portClient to set
     */
    public void setPortClient(int portClient) {
        this.portClient = portClient;
    }

    /**
     * @return the isDisponivel
     */
    public Boolean getIsDisponivel() {
        return isDisponivel;
    }

    /**
     * @param isDisponivel the isDisponivel to set
     */
    public void setIsDisponivel(Boolean isDisponivel) {
        this.isDisponivel = isDisponivel;
    }

    /**
     * @return the ipClient
     */
    public InetAddress getIpClient() {
        return ipClient;
    }

    /**
     * @param ipClient the ipClient to set
     */
    public void setIpClient(InetAddress ipClient) {
        this.ipClient = ipClient;
    }

}
