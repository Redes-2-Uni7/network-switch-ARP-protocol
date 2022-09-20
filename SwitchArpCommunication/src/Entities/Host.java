package Entities;

import java.util.HashMap;
import java.util.Map;
import Application.Settings;

public class Host {
    private String ip;
    private String mac;
    private Map<String, String> macTable = new HashMap<>(); //mac e ip
    private Port port;

    public Host(String ip, String mac) {
        this.ip = ip;
        this.mac = mac;
        port = new Port(mac, this);
    }

    public String getIp() {
        return ip;
    }

    public String getMac() {
        return mac;
    }

    public Map<String, String> getMacTable() {
        return macTable;
    }

    public Port getPort() {
        return port;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public void setMac(String mac) {
        this.mac = mac;
    }

    public void setMacTable(Map<String, String> macTable) {
        this.macTable = macTable;
    }

    public Boolean isToMe(Packet pack) {
        return pack.getDestinationIp().equals(this.ip);
    }

    public Boolean needToAnswer(Packet pack) {
        return !pack.getPayload().equals("Arp Reply") && !pack.getPayload().equals("Ack Reply");
    }

    public Boolean shouldSave(Packet pack) {
        return !macTable.containsKey(pack.getSourceIp());
    }

    public void receiveMessage(Packet pack) {
        System.out.println("Recebendo pacote pelo host" + (mac.equals(Settings.macSource1) ? "1" 
                                                        : mac.equals(Settings.macSource2) ? "2" 
                                                        : mac.equals(Settings.macSource3) ? "3" 
                                                        : mac.equals(Settings.macSource4) ? "4" 
                                                        : "" ));
        pack.print();

        Boolean isArp = pack.isArp();
        Boolean isToMe = isToMe(pack);

        if (!isToMe && !isArp) { return; }

        if (shouldSave(pack)) {
            macTable.put(pack.getSourceMac(), pack.getSourceIp());
        }

        if (isToMe && needToAnswer(pack)) {
            if (isArp)
                sendMessage(new Packet(this.mac, Settings.macBroadcast, this.ip, pack.getSourceIp(), "Arp Reply"));
            else
                sendMessage(new Packet(this.mac, pack.getSourceMac(), this.ip, pack.getSourceIp(), "Ack Reply"));
        }
    }
    
    public void sendMessage(Packet pack) {
        System.out.println("Enviando pacote pelo host" + (mac.equals(Settings.macSource1) ? "1" 
                                                        : mac.equals(Settings.macSource2) ? "2" 
                                                        : mac.equals(Settings.macSource3) ? "3" 
                                                        : mac.equals(Settings.macSource4) ? "4" 
                                                        : "" ));
        pack.print();
        port.sendMessageByCable(pack);
    }
}
