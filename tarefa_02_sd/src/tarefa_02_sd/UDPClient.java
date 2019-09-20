package tarefa_02_sd;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private ObjectOutputStream oos = new ObjectOutputStream(baos);
    private DatagramSocket clientSocket = new DatagramSocket();

    private RegistroClient registro = new RegistroClient();

    private String servidor;
    private int portaServidor;
    private int portaClientDefault = 4000;

    private String name;

    private Scanner input = new Scanner(System.in);

    private InetAddress IPAddressServer;

    private byte[] sendData = new byte[1024];
    private byte[] receiveData = new byte[1024];
    

    public UDPClient() throws IOException {

    }

    public void registrarServidor() throws UnknownHostException, IOException {
        System.out.println("Informe qual é o servidor");
        this.servidor = input.nextLine();
        this.IPAddressServer = InetAddress.getByName(this.servidor);
        System.out.println("Informe qual é a porta servidor");
        this.portaServidor = input.nextInt();
        System.out.println("Informe o seu nome:");
        this.name = input.nextLine();

        registro.setNomeClient(this.name);
        registro.setPortClient(this.portaClientDefault);

        Mensagem msg = new Mensagem();
        msg.setBody(registro);
        msg.setType(EnumTipoMensagem.ADICIONAR);

        this.sendMensage(msg);

    }

    private void sendMensage(Mensagem msg) throws IOException {
        oos.writeObject(msg);
        oos.flush();
        byte[] Buf = baos.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(Buf, Buf.length, this.IPAddressServer, portaServidor);
        System.out
                .println("Enviando pacote UDP para " + servidor + ":" + portaServidor);
        clientSocket.send(sendPacket);
    }
   
    //
    //DatagramPacket receivePacket = new DatagramPacket(receiveData,
    //      receiveData.length);
    //clientSocket.receive(receivePacket);
    //System.out.println("Pacote UDP recebido...");
    //String modifiedSentence = new String(receivePacket.getData());
    //System.out.println("Texto recebido do servidor:" + modifiedSentence);
    //clientSocket.close();
    //System.out.println("Socket cliente fechado!");
}
