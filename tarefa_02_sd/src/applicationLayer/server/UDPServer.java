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
                RecordClient record;
                switch (recebido.getType()) {
                    case INSERT_RECORD_CLIENT:

                        record = (RecordClient) recebido.getRecord();

                        record.setIp(IPAddressRecebido);
                        record.setPort(portRecebido);

                        this.addClient(record);
                        break;
                    case GET_ALL_RECORD_CLIENT:
                        this.showRecordsClients(IPAddressRecebido, portRecebido);
                        break;
                    case DEL_RECORD_CLIENT:

                        record = (RecordClient) recebido.getRecord();

                        record.setIp(IPAddressRecebido);
                        record.setPort(portRecebido);
                        this.rmClient(record);
                        break;

                    case MESSAGE_TO_CLIENT:
                        System.out.println("MESSAGE_TO_CLIENT");
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

    @Override
    public boolean rmClient(RecordClient record) {
        int indexRemove = getIndexClientFromList(record);
        if (indexRemove < 0) {
            System.out.println("Registro não encontrado");
            return false;
        }
        this.listRecords.remove(indexRemove);
        return true;
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

    private int getIndexClientFromList(RecordClient record) {
        for (RecordClient temp : this.listRecords) {
            if (record.getName().equalsIgnoreCase(temp.getName()) && record.getPort() == temp.getPort() && record.getIp().equals(temp.getIp())) {
                System.out.println("Removendo registro de " + temp.getName() + " ip :" + temp.getIp().getHostName() + " porta: " + temp.getPort());
                return this.listRecords.indexOf(temp);
            }
        }
        return -1;
    }
}
