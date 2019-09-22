package applicationLayer.server;

import applicationLayer.client.RecordClient;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import protocolLayer.ENUM_MESSAGE;
import protocolLayer.Message;
import protocolLayer.Protocol;

public class UDPServer implements UDPServerInterface {

    private Protocol myProtocol;

    private List<RecordClient> listRecords = new ArrayList();

    public UDPServer() throws IOException {

    }

    @Override
    public void start(int port) throws SocketException, IOException {
        this.myProtocol = new Protocol(port);

        this.listen();
    }

    @Override
    public void listen() throws IOException {
        while (true) {
            try {

                Message recebido = myProtocol.receive();

                InetAddress IPAddressRecebido = recebido.getIpRemetente();
                int portRecebido = recebido.getPortRemetente();

                switch (recebido.getType()) {
                    case INSERT_RECORD_CLIENT:

                        RecordClient record;
                        record = (RecordClient) recebido.getRecord();

                        record.setIp(IPAddressRecebido);
                        record.setPort(portRecebido);

                        this.addClient(record);
                        this.showRecordsClients(IPAddressRecebido, portRecebido);
                        break;
                    case GET_ALL_RECORD_CLIENT:
                        System.out.println("GET_ALL_RECORD_CLIENT");
                        break;
                    case DEL_RECORD_CLIENT:
                        System.out.println("DEL_RECORD_CLIENT");
                        break;

                    case MESSAGE:
                        System.out.println("DEL_RECORD_CLIENT");
                        break;
                    case RECORD_CLIENT:
                        System.out.println("RECORD_CLIENT");
                        break;
                    default:
                        System.out.println("Não está obedecendo os tipos do protocolo!");
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }

    //  private void listarDisponiveis(InetAddress IPAddressRecebido, int portClient) throws IOException {
    //    Mensagem novaMensagem = new Mensagem();
    //  novaMensagem.setType(EnumTipoMensagem.REGISTRO);
    //novaMensagem.setIsMultiPart(true);
    //for (int i = 0; i < this.listaRegistros.size(); i++) {
    //   RegistroClient novoRegistro = (RegistroClient) this.listaRegistros.get(i);
    //  novaMensagem.setBody(novoRegistro);
    // if (i == this.listaRegistros.size() - 1) {
    //    novaMensagem.setIsMultiPart(false);
    // }
//
    //          Protocol protocolSend = new Protocol();
    //        protocolSend.setIp(IPAddressRecebido);
    //      protocolSend.setMsg(novaMensagem);
    //    protocolSend.setPort(portClient);
    //  serverSocket.send(protocolSend);
    //}
    //}
    @Override
    public boolean rmClient(RecordClient record) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addClient(RecordClient record) {
        this.listRecords.add(record);
        return true;
    }

    @Override
    public void showRecordsClients(InetAddress ip, int port) {
        
        try {
            for (int i = 0; i < this.listRecords.size(); i++) {
                Message msg = new Message();
                if (i < this.listRecords.size() - 1) {
                    msg.setIsMultiPart(Boolean.TRUE);
                } else {
                    msg.setIsMultiPart(Boolean.FALSE);
                }
                
                RecordClient recordToSend = listRecords.get(i);
                recordToSend.setId(i);
                
                msg.setRecord(recordToSend);
                msg.setIpDestinatario(ip);
                msg.setPortDestinatario(port);
                msg.setType(ENUM_MESSAGE.RECORD_CLIENT);
                this.myProtocol.send(msg);

            }
        } catch (IOException ex) {
            Logger.getLogger(UDPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
