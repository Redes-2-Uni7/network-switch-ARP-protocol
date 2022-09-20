package Entities;

import Application.Settings;

public class Packet {
    private String sourceMac;
    private String destinationMac;
    private String sourceIp;
    private String destinationIp;
    private String payload;

    public Packet(
        String sourceMac, 
        String destinationMac, 
        String sourceIp, 
        String destinationIp, 
        String payload)
    {
        this.sourceMac = sourceMac;
        this.destinationMac = destinationMac;
        this.sourceIp = sourceIp;
        this.destinationIp = destinationIp;
        this.payload = payload;
    }

    public String getSourceMac() {
        return sourceMac;
    }

    public String getDestinationMac() {
        return destinationMac;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public String getPayload() {
        return payload;
    }

    public void setSourceMac(String sourceMac) {
        this.sourceMac = sourceMac;
    }

    public void setDestinationMac(String destinationMac) {
        this.destinationMac = destinationMac;
    }

    public void setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
    }

    public void setDestinationIp(String destinationIp) {
        this.destinationIp = destinationIp;
    }
    
    public void setPayload(String payload) {
        this.payload = payload;
    }
    
    public Boolean isArp() {
        return this.destinationMac == Settings.macBroadcast;
    }
}
