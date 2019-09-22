/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolLayer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author QueroDelivery
 */
public class Protocol implements ProtocolInterface {

    private byte[] sendData = new byte[1024];
    private byte[] receiveData = new byte[1024];
    private DatagramSocket socket;
    private DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
    private int port;

    public Protocol() throws IOException {
        this.socket = new DatagramSocket();
    }

    public Protocol(int port) throws IOException {
        this.socket = new DatagramSocket(port);
        this.port = port;
    }

    public void send(Message msgSend) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(msgSend);
        oos.flush();
        byte[] Buf;
        Buf = baos.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(Buf, Buf.length, msgSend.getIpDestinatario(), msgSend.getPortDestinatario());
        System.out
                .println("Enviando pacote UDP para " + msgSend.getIpDestinatario() + ":" + msgSend.getPortDestinatario());
        socket.send(sendPacket);
    }

    public Message receive() throws IOException, ClassNotFoundException {
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        System.out.println("Esperando por datagrama UDP na porta " + this.getPortRemetente());
        socket.receive(receivePacket);

        ByteArrayInputStream baos = new ByteArrayInputStream(receiveData);
        ObjectInputStream oos = new ObjectInputStream(baos);

        Message msg = (Message) oos.readObject();
        msg.setIpRemetente(receivePacket.getAddress());
        msg.setPortRemetente(receivePacket.getPort());
        
        return msg;

    }

    public DatagramPacket getReceivePacket() {
        return receivePacket;
    }

    /**
     * @return the portRemetente
     */
    public int getPortRemetente() {
        return this.socket.getLocalPort();
    }

    /**
     * @return the ipRemetente
     */
    public InetAddress getIpRemetente() {
        return this.socket.getLocalAddress();
    }

}
