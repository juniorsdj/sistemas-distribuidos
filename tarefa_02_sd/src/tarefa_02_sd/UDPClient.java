package tarefa_02_sd;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class UDPClient {

    public static void main(String args[]) throws Exception {

        Scanner input = new Scanner(System.in);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);

        DatagramSocket clientSocket = new DatagramSocket();

        String servidor = "localhost";
        int porta = 4000;

        InetAddress IPAddress = InetAddress.getByName(servidor);

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.println("Digite o texto a ser enviado ao servidor: ");
        String sentence = input.nextLine();
        Mensagem mensagemEnviada = new Mensagem();
        mensagemEnviada.setMensagem(sentence);
        mensagemEnviada.setType(EnumTipoMensagem.MENSAGEM);

        oos.writeObject(mensagemEnviada);
        oos.flush();
        byte[] Buf = baos.toByteArray();

        DatagramPacket sendPacket = new DatagramPacket(Buf,
                Buf.length, IPAddress, porta);

        System.out
                .println("Enviando pacote UDP para " + servidor + ":" + porta);
        clientSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData,
                receiveData.length);

        clientSocket.receive(receivePacket);
        System.out.println("Pacote UDP recebido...");

        String modifiedSentence = new String(receivePacket.getData());

        System.out.println("Texto recebido do servidor:" + modifiedSentence);
        clientSocket.close();
        System.out.println("Socket cliente fechado!");
    }
}
