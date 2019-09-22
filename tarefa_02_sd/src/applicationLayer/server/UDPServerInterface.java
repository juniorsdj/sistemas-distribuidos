/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationLayer.server;

import applicationLayer.client.RecordClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

/**
 *
 * @author QueroDelivery
 */
public interface UDPServerInterface {

    void listen() throws IOException;

    boolean rmClient(RecordClient record);

    boolean addClient(RecordClient record);

    void showRecordsClients(InetAddress ip, int port);
    
    //void start (int port) throws SocketException, IOException;
}
