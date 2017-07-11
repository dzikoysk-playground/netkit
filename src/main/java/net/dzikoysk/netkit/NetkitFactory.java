package net.dzikoysk.netkit;

public class NetkitFactory {

    public Netkit createSynchronizedNetkit() {
        Netkit netkit = new Netkit();
        netkit.initialize(true);

        return netkit;
    }

    public Netkit createNetkit() {
        Netkit netkit = new Netkit();
        netkit.initialize(false);

        return netkit;
    }

}
