package applicationLayer.client;

import applicationLayer.server.UDPServer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocolLayer.ENUM_MESSAGE;
import protocolLayer.Message;
import protocolLayer.Protocol;

public class UDPClient implements ClientInterface {

    private Protocol myProtocol = new Protocol();
    private RecordClient myRecord;
    private List<RecordClient> recordsClientsOnServer = new ArrayList();
    private InetAddress serverAddress;
    private int serverPort;
    private String name;
    private boolean isDisponivel;
    private Scanner input = new Scanner(System.in);

    public UDPClient() throws IOException, UnknownHostException, ClassNotFoundException {
        this.recordOnServer();
    }

    @Override
    public void recordOnServer() throws UnknownHostException, IOException, ClassNotFoundException {
        System.out.println("Informe qual é o servidor");
        this.serverAddress = InetAddress.getByName(input.nextLine());
        System.out.println("Informe o seu nome:");
        this.name = input.nextLine();
        System.out.println("Informe qual é a porta servidor");
        this.serverPort = input.nextInt();

        this.myRecord = new RecordClient(this.name);

        Message msg = new Message();
        msg.setRecord(this.myRecord);
        msg.setType(ENUM_MESSAGE.INSERT_RECORD_CLIENT);
        msg.setIpDestinatario(serverAddress);
        msg.setPortDestinatario(serverPort);

        this.setIsDisponivel(true);

        this.myProtocol.send(msg);
        this.getRecordsConnected();
    }

    @Override
    public void removeRecordOnServer() throws UnknownHostException, IOException, ClassNotFoundException {
        this.setIsDisponivel(false);
        Message msg = new Message();
        msg.setRecord(this.myRecord);
        msg.setType(ENUM_MESSAGE.DEL_RECORD_CLIENT);
        msg.setIpDestinatario(serverAddress);
        msg.setPortDestinatario(serverPort);

        this.myProtocol.send(msg);
    }

    public void sendMessageToClient(String text, int indexDestino) throws IOException {
        RecordClient record = this.recordsClientsOnServer.get(indexDestino);
        Message msg = new Message();

        msg.setMsg(text);
        msg.setPortDestinatario(record.getPort());
        msg.setIpDestinatario(record.getIp());
        msg.setType(ENUM_MESSAGE.MESSAGE_TO_CLIENT);

        this.myProtocol.send(msg);
        this.listen();
    }

    public void getRecordsConnected() throws IOException {
        Message msg = new Message();

        msg.setType(ENUM_MESSAGE.GET_ALL_RECORD_CLIENT);
        msg.setIpDestinatario(serverAddress);
        msg.setPortDestinatario(serverPort);

        this.myProtocol.send(msg);
        this.recordsClientsOnServer.clear();
        System.out.println(RecordClient.HeaderToString());
        this.listen();
    }

    @Override
    public void listen() throws IOException {
        try {

            Message recebido = myProtocol.receive();

            InetAddress IPAddressRecebido = recebido.getIpRemetente();
            int portRecebido = recebido.getPortRemetente();
            String resposta;
            switch (recebido.getType()) {
                case INSERT_RECORD_CLIENT:
                    System.out.println("ESSA MENSAGEM DEVE SER ENVIADA APENAS PARA SERVIDOR");
                    break;
                case GET_ALL_RECORD_CLIENT:
                    System.out.println("ESSA MENSAGEM DEVE SER ENVIADA APENAS PARA SERVIDOR");
                    break;
                case DEL_RECORD_CLIENT:
                    System.out.println("ESSA MENSAGEM DEVE SER ENVIADA APENAS PARA SERVIDOR");
                    break;

                case MESSAGE_TO_CLIENT:
                    System.out.println(recebido.getMsg() +  " do ip " + IPAddressRecebido.getHostAddress() + "da porta" + portRecebido);
                    input.nextLine();
                    resposta = input.nextLine();
                    Message msg = new Message();
                    msg.setMsg(resposta);
                    msg.setType(ENUM_MESSAGE.MESSAGE_TO_CLIENT);
                    msg.setPortDestinatario(portRecebido);
                    msg.setIpDestinatario(IPAddressRecebido);
                    this.myProtocol.send(msg);
                    this.listen();

                    break;
                case RECORD_CLIENT:
                    RecordClient novoClient = recebido.getRecord();
                    this.recordsClientsOnServer.add(novoClient);
                    System.out.println(novoClient.toString());
                    if (recebido.getIsMultiPart()) {
                        this.listen();
                    }
                    break;
                default:
                    System.out.println("Não está obedecendo os tipos do protocolo!");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /**
     * @return the isDisponivel
     */
    public boolean isDisponivel() {
        return isDisponivel;
    }

    /**
     * @param isDisponivel the isDisponivel to set
     */
    public void setIsDisponivel(boolean isDisponivel) {
        this.isDisponivel = isDisponivel;
    }
}
