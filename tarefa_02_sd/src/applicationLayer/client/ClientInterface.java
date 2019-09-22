/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationLayer.client;

import java.io.IOException;
import java.net.UnknownHostException;

/**
 *
 * @author QueroDelivery
 */
public interface ClientInterface {
    void listen() throws IOException;
    void recordOnServer()throws UnknownHostException, IOException, ClassNotFoundException;
    void removeRecordOnServer() throws UnknownHostException, IOException, ClassNotFoundException;
}
