package UT3.SimulacroExamen;

import java.io.IOException;
import java.net.InetAddress;

public class Connection {
    private String multicastAddress = "225.10.10.10";
    private int multicastPort = 6998;
    private InetAddress privateAddress;
    private int privatePort = 6668;
    private InetAddress inetAddress; //(group)

    public Connection() throws IOException {
        privateAddress = InetAddress.getLocalHost();
        //Es lo mismo :
//        privateAddress = InetAddress.getByName("localhost");
        this.inetAddress = InetAddress.getByName(multicastAddress);
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public int getMulticastPort() {
        return multicastPort;
    }

    public void setMulticastPort(int multicastPort) {
        this.multicastPort = multicastPort;
    }


    public int getPrivatePort() {
        return privatePort;
    }

    public void setPrivatePort(int privatePort) {
        this.privatePort = privatePort;
    }

    public InetAddress getPrivateAddress() {
        return privateAddress;
    }

    public void setPrivateAddress(InetAddress privateAddress) {
        this.privateAddress = privateAddress;
    }
}
