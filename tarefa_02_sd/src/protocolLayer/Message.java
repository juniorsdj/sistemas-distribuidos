/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolLayer;

import applicationLayer.client.RecordClient;
import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author QueroDelivery
 */
public class Message implements Serializable {

    private ENUM_MESSAGE type;
    private String msg;
    private Boolean isMultiPart;
    private RecordClient record;
    private InetAddress ipDestinatario;
    private int portDestinatario;
    private int portRemetente;
    private InetAddress ipRemetente;

    /**
     * @return the type
     */
    public ENUM_MESSAGE getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ENUM_MESSAGE type) {
        this.type = type;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the isMultiPart
     */
    public Boolean getIsMultiPart() {
        return isMultiPart;
    }

    /**
     * @param isMultiPart the isMultiPart to set
     */
    public void setIsMultiPart(Boolean isMultiPart) {
        this.isMultiPart = isMultiPart;
    }

    /**
     * @return the record
     */
    public RecordClient getRecord() {
        return record;
    }

    /**
     * @param record the record to set
     */
    public void setRecord(RecordClient record) {
        this.record = record;
    }

    /**
     * @return the ipDestinatario
     */
    public InetAddress getIpDestinatario() {
        return ipDestinatario;
    }

    /**
     * @param ipDestinatario the ipDestinatario to set
     */
    public void setIpDestinatario(InetAddress ipDestinatario) {
        this.ipDestinatario = ipDestinatario;
    }

    /**
     * @return the ipRemetente
     */
    /**
     * @return the portDestinatario
     */
    public int getPortDestinatario() {
        return portDestinatario;
    }

    /**
     * @param portDestinatario the portDestinatario to set
     */
    public void setPortDestinatario(int portDestinatario) {
        this.portDestinatario = portDestinatario;
    }

    /**
     * @return the portRemetente
     */
    public int getPortRemetente() {
        return portRemetente;
    }

    /**
     * @param portRemetente the portRemetente to set
     */
    protected void setPortRemetente(int portRemetente) {
        this.portRemetente = portRemetente;
    }

    /**
     * @return the ipRemetente
     */
    public InetAddress getIpRemetente() {
        return ipRemetente;
    }

    /**
     * @param ipRemetente the ipRemetente to set
     */
    protected void setIpRemetente(InetAddress ipRemetente) {
        this.ipRemetente = ipRemetente;
    }

}
