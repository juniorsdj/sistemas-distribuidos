package tarefa_02_sd;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer implements UDPServerInterface {

    private int port;
    private DatagramSocket serverSocket;
    private List<RegistroClient> listaRegistros = new ArrayList();
    private Boolean isListening;

    byte[] receiveData = new byte[2048];
    byte[] sendData = new byte[2048];

    public void start(int port) throws SocketException, IOException {
        this.serverSocket = new DatagramSocket(port);
        this.port = port;
        this.listen();
    }

    @Override
    public void listen() throws IOException {
        this.isListening = true;
        while (this.isListening) {
            try {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                System.out.println("Esperando por datagrama UDP na porta " + this.port);
                serverSocket.receive(receivePacket);

                ByteArrayInputStream baos = new ByteArrayInputStream(receiveData);
                ObjectInputStream oos = new ObjectInputStream(baos);

                Mensagem mensagemRecebida = (Mensagem) oos.readObject();

                
                switch (mensagemRecebida.getType()) {
                    case ADICIONAR:
                        System.out.println("Adicionar");
                        break;
                    case REMOVER:
                        System.out.println("Remover");
                        break;
                    case LISTAR_DISPONIVEIS:
                        System.out.println("Listar disponiveis");
                        break;
                    case MENSAGEM:
                        System.out.println("Mensagem");
                        break;
                    default:
                        System.out.println("Não está obedecendo os tipos do protocolo!");

                }

                //InetAddress IPAddress = receivePacket.getAddress();
                //int port = receivePacket.getPort();
                //String capitalizedSentence = sentence.toUpperCase();
                //sendData = capitalizedSentence.getBytes();
                //DatagramPacket sendPacket = new DatagramPacket(sendData,
                //       sendData.length, IPAddress, port);
                //System.out.print("Enviando " + capitalizedSentence + "...");
                //serverSocket.send(sendPacket);
                //System.out.println("OK\n");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public boolean removerCliente(RegistroClient registro) {
        return true;
    }

    @Override
    public boolean adicionarCliente(RegistroClient registro) {
        this.listaRegistros.add(registro);
        return true;
    }

}
