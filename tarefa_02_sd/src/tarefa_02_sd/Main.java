/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tarefa_02_sd;

import java.util.Scanner;

/**
 *
 * @author QueroDelivery
 */
public class Main {

    public static void main(String args[]) throws Exception {
        Scanner input = new Scanner(System.in);
        UDPServer server;
        UDPClient client;

        int option = 0;

        do {
            System.out.println("Informe 1 para servidor e 2 para client ou 0 para encerrar");
            option = input.nextInt();

            if (option > 2) {
                return;
            }

            if (option == 1) {
                server = new UDPServer();
                System.out.println("Informe a porta que deseja rodar o servidor");
                int portaServidor = input.nextInt();

                server.start(portaServidor);
            }
            if (option == 2) {
                client = new UDPClient();
            }
        } while (option > 0);

    }
}
