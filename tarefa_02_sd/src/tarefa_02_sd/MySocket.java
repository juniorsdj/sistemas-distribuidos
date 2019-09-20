/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefa_02_sd;

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
public class MySocket {

    private byte[] sendData = new byte[1024];
    private byte[] receiveData = new byte[1024];
    private DatagramSocket socketRef;
    private int port;

    private DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

    public MySocket(int port) throws IOException {
        this.port = port;
        this.socketRef = new DatagramSocket(port);
    }

    public void send(Protocol protocolSend) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        oos.writeObject(protocolSend.getMsg());
        oos.flush();
        byte[] Buf;
        Buf = baos.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(Buf, Buf.length, protocolSend.getIp(), protocolSend.getPort());
        System.out
                .println("Enviando pacote UDP para " + protocolSend.getIp() + ":" + protocolSend.getPort());
        socketRef.send(sendPacket);
    }

    public Protocol receive() throws IOException, ClassNotFoundException {
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        System.out.println("Esperando por datagrama UDP na porta " + this.getPort());
        socketRef.receive(receivePacket);
        ByteArrayInputStream baos = new ByteArrayInputStream(receiveData);
        ObjectInputStream oos = new ObjectInputStream(baos);

        socketRef.receive(receivePacket);

        Protocol protocolReturn = new Protocol();

        Mensagem msg = (Mensagem) oos.readObject();
        protocolReturn.setMsg(msg);
        protocolReturn.setIp(receivePacket.getAddress());
        protocolReturn.setPort(receivePacket.getPort());

        return protocolReturn;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @return the receivePacket
     */
    public DatagramPacket getReceivePacket() {
        return receivePacket;
    }

}
