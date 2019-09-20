/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefa_02_sd;

import java.net.InetAddress;

/**
 *
 * @author QueroDelivery
 */
public class Protocol {
    private Mensagem msg;
    private int port;
    private InetAddress ip;

    /**
     * @return the msg
     */
    public Mensagem getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(Mensagem msg) {
        this.msg = msg;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the ip
     */
    public InetAddress getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(InetAddress ip) {
        this.ip = ip;
    }
    
    
}
