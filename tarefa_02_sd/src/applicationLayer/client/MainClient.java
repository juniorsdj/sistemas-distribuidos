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
        
        
    }

}
