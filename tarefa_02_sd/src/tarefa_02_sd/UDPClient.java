package tarefa_02_sd;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {

    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private ObjectOutputStream oos = new ObjectOutputStream(baos);

    private RegistroClient registro = new RegistroClient();

    private String servidor;
    private int portaServidor;
    private int portaClientDefault = 4000;
    private DatagramSocket clientSocket = new DatagramSocket(portaClientDefault);
    private String name;

    private Scanner input = new Scanner(System.in);

    private InetAddress IPAddressServer;

    private byte[] sendData = new byte[1024];
    private byte[] receiveData = new byte[1024];

    public UDPClient() throws IOException {

    }

    public void registrarServidor() throws UnknownHostException, IOException, ClassNotFoundException {
        System.out.println("Informe qual é o servidor");
        this.servidor = input.nextLine();
        this.IPAddressServer = InetAddress.getByName(this.servidor);
        System.out.println("Informe o seu nome:");
        this.name = input.nextLine();
        System.out.println("Informe qual é a porta servidor");
        this.portaServidor = input.nextInt();

        registro.setNomeClient(this.name);
        registro.setPortClient(this.portaClientDefault);

        Mensagem msg = new Mensagem();
        msg.setBody(registro);
        msg.setType(EnumTipoMensagem.ADICIONAR);

        this.sendMensage(msg);
        this.esperandoMensage();
    }

    private void esperandoMensage() throws IOException, ClassNotFoundException {
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        System.out.println("Esperando por datagrama UDP na porta " + this.portaClientDefault);
        clientSocket.receive(receivePacket);
        ByteArrayInputStream baos = new ByteArrayInputStream(receiveData);
        ObjectInputStream oos = new ObjectInputStream(baos);

        Mensagem msg = (Mensagem) oos.readObject();
        InetAddress IPAddressRecebido = receivePacket.getAddress();
        int portClient = receivePacket.getPort();
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
}
