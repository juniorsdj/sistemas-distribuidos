/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package protocolLayer;

import java.io.IOException;

/**
 *
 * @author QueroDelivery
 */
public interface ProtocolInterface {

    public void send(Message msgSend) throws IOException;
    
    public Message receive() throws IOException, ClassNotFoundException;

    
}
