/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationLayer.client;

import java.io.Serializable;
import java.net.InetAddress;

/**
 *
 * @author QueroDelivery
 */
public class RecordClient implements Serializable {

    private String name;
    private InetAddress ip;
    private int port;
    private int id;

    RecordClient(String name) {
        this.name = name;
    }

    RecordClient(String name, int port) {
        this.name = name;
        this.port = port;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    public static String HeaderToString() {
        return "Id \t\t Nome \t\t Ip \t\t";
    }

    @Override
    public String toString() {
        return this.getId() + "\t\t" + this.getName() + "\t\t" + this.getIp();
    }

}
