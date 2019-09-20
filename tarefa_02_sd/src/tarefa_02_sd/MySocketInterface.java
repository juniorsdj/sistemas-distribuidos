/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefa_02_sd;

import java.io.IOException;

/**
 *
 * @author QueroDelivery
 */
public interface MySocketInterface {

    public void send(Protocol protocol) throws IOException;
    
    public Protocol receive() throws IOException, ClassNotFoundException;

    
}
