package Application;

import Entities.Switch;
import Entities.Host;
import Entities.Cable;
import Entities.Packet;

public class App {
    public static void main(String[] args) throws Exception {

        Switch switch1 = new Switch("11:11:11:11:11:11", 3);
        Switch switch2 = new Switch("22:22:22:22:22:22", 3);

        Host h1 = new Host(Settings.ipSource1, Settings.macSource1);
        Host h2 = new Host(Settings.ipSource2, Settings.macSource2);
        Host h3 = new Host(Settings.ipSource3, Settings.macSource3);
        Host h4 = new Host(Settings.ipSource4, Settings.macSource4);

        new Cable(switch1.getPorts().get(0), h1.getPort());
        new Cable(switch1.getPorts().get(1), h2.getPort());
        new Cable(switch1.getPorts().get(2), switch2.getPorts().get(0));
        new Cable(switch2.getPorts().get(1), h3.getPort());
        new Cable(switch2.getPorts().get(2), h4.getPort());

        Packet pack = new Packet(h1.getMac(), Settings.macBroadcast, h1.getIp(), h2.getIp(), "Arp Request");

        h1.sendMessage(pack);

        System.out.println("");
        System.out.println("TABLES FROM SWITCH1");
        System.out.println(switch1.getMacTable().toString());
        System.out.println(h1.getMacTable().toString());
        System.out.println(h2.getMacTable().toString());
        System.out.println("---------------------------------------");
        System.out.println("");
        System.out.println("TABLES FROM SWITCH2");
        System.out.println(switch2.getMacTable().toString());
        System.out.println(h3.getMacTable().toString());
        System.out.println(h4.getMacTable().toString());
    }
}
