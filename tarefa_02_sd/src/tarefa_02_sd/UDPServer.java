package tarefa_02_sd;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPServer implements UDPServerInterface {

    private int port;
    private MySocket serverSocket;
    private ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private ObjectOutputStream oos = new ObjectOutputStream(baos);

    private List<RegistroClient> listaRegistros = new ArrayList();
    private Boolean isListening;

    byte[] receiveData = new byte[2048];
    byte[] sendData = new byte[2048];

    public UDPServer() throws IOException {

    }

    public void start(int port) throws SocketException, IOException {
        this.serverSocket = new MySocket(port);
        this.port = port;
        this.listen();
    }

    @Override
    public void listen() throws IOException {
        this.isListening = true;
        while (this.isListening) {
            try {

                Protocol recebido = serverSocket.receive();

                Mensagem msg = (Mensagem) recebido.getMsg();
                InetAddress IPAddressRecebido = recebido.getIp();
                int portClient = recebido.getPort();

                switch (msg.getType()) {
                    case ADICIONAR:
                        RegistroClient registro;
                        registro = (RegistroClient) msg.getBody();

                        registro.setIpClient(IPAddressRecebido);
                        registro.setPortClient(portClient);

                        this.adicionarClient(registro);

                        this.listarDisponiveis(IPAddressRecebido, portClient);
                        break;
                    case REMOVER:
                        this.removerClient((RegistroClient) msg.getBody());
                        break;
                    case LISTAR_DISPONIVEIS:
                        this.listarDisponiveis(IPAddressRecebido, portClient);
                        break;

                    case MENSAGEM:
                        System.out.println("Mensagem");
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
    public boolean removerClient(RegistroClient registro
    ) {

        return true;
    }

    private void listarDisponiveis(InetAddress IPAddressRecebido, int portClient) throws IOException {
        Mensagem novaMensagem = new Mensagem();
        novaMensagem.setType(EnumTipoMensagem.REGISTRO);
        novaMensagem.setIsMultiPart(true);
        for (int i = 0; i < this.listaRegistros.size(); i++) {
            RegistroClient novoRegistro = (RegistroClient) this.listaRegistros.get(i);
            novaMensagem.setBody(novoRegistro);
            if (i == this.listaRegistros.size() - 1) {
                novaMensagem.setIsMultiPart(false);
            }

            Protocol protocolSend = new Protocol();
            protocolSend.setIp(IPAddressRecebido);
            protocolSend.setMsg(novaMensagem);
            protocolSend.setPort(portClient);

            serverSocket.send(protocolSend);

        }
    }

    @Override
    public boolean adicionarClient(RegistroClient registro) {
        this.listaRegistros.add(registro);
        System.out.println(registro.getNomeClient());
        return true;
    }

}
