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
public interface UDPServerInterface {
    public void listen() throws IOException;
    public boolean removerClient(RegistroClient registro);
    public boolean adicionarClient(RegistroClient registro);
}
