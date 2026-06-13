package com.dimsteams.sodiumpp.scripting.modules;

import com.dimsteams.scripting.Getter;
import com.dimsteams.scripting.type.CustomType;
import net.minecraft.network.protocol.Packet;

@CustomType(name = "PacketEvent")
public class PacketEvent {

    private final Packet<?> packet;

    public PacketEvent(Packet<?> packet) {
        this.packet = packet;
    }

    @Getter(name = "packet")
    public Packet<?> getPacket() {
        return packet;
    }
}