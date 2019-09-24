package applicationLayer.client;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author QueroDelivery
 */
public class MainClient {

    public static void main(String[] args) throws IOException, UnknownHostException, ClassNotFoundException {
        Scanner input = new Scanner(System.in);

        System.out.println("iniciar o cliente");
        UDPClient client = new UDPClient();
        int opcao = 0;
        int opcaoConversa;
        String texto;
        do {
            System.out.println("Digite 1 para conversar com outro usuário");
            System.out.println("Digite 2 para visualizar todos os usuários conectados");
            System.out.println("Digite 3 desconectar do servidor");
            System.out.println("Digite 4 para ficar disponível");
            System.out.println("Digite -1 sair");
            opcao = input.nextInt();
            switch (opcao) {
                case 1:
                    System.out.println("Digite o id do usuário que deseja conversar");
                    opcaoConversa = input.nextInt();
                    input.nextLine();
                    System.out.println("digite a mensagem");
                    texto = input.nextLine();
                    client.sendMessageToClient(texto, opcaoConversa);
                    break;
                case 2:
                    client.getRecordsConnected();
                    break;
                case 3:
                    client.removeRecordOnServer();
                    break;
                case 4:
                    client.listen();
                    break;
                case -1:
                    System.out.println("Até a próxima");
                    client.removeRecordOnServer();
                    break;
                default:
                    System.out.println("Opcao não identificada");
            }
        } while (opcao
                != -1);

    }

}
