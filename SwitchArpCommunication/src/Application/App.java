package Application;

import Entities.Switch;
import Entities.Host;
import Entities.Cable;
import Entities.Packet;

public class App {
    public static void main(String[] args) throws Exception {

        Switch switch1 = new Switch("11:11:11:11:11:11", 3);

        Host h1 = new Host(Settings.ipSource1, Settings.macSource1);
        Host h2 = new Host(Settings.ipSource2, Settings.macSource2);
        Host h3 = new Host(Settings.ipSource3, Settings.macSource3);

        new Cable(switch1.getPorts().get(0), h1.getPort());
        new Cable(switch1.getPorts().get(1), h2.getPort());
        new Cable(switch1.getPorts().get(2), h3.getPort());

        Packet pack = new Packet(h1.getMac(), Settings.macBroadcast, h1.getIp(), h2.getIp(), "Arp Request");

        h1.sendMessage(pack);

        System.out.println("");
        System.out.println("TABLES FROM SWITCH");
        System.out.println("----------------------------------------------------");
        System.out.println("Switch: " + switch1.getMacTable().toString().replaceAll(",", ";").replaceAll("=", ", "));
        System.out.println("Host1: " + h1.getMacTable().toString().replaceAll(",", ";").replaceAll("=", ", "));
        System.out.println("Host2: " + h2.getMacTable().toString().replaceAll(",", ";").replaceAll("=", ", "));
        System.out.println("Host3: " + h3.getMacTable().toString().replaceAll(",", ";").replaceAll("=", ", "));
    }
}
