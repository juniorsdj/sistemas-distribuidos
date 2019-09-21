/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationLayer.server;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author QueroDelivery
 */
public class MainServer {

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        int port;
        UDPServer servidor = new UDPServer();
        System.out.println("iniciar servidor");
        System.out.println("Informe qual a porta que o servidor ficará ouvindo");
        port = input.nextInt();
        servidor.start(port);
    }
}
