package net.mifort.testosterone.network.packet;

import java.util.HashMap;
import java.util.Map;

public class ClientEffectData {
    private static final Map<Integer, Integer> EFFECT_MAP = new HashMap<>();

    private static long endOfCooldownTick;
    private static long actualBeginTick;
    private static long beginTick;
    private static long duration;

    public static void setEffect(int entityId, int effectInt) {
        EFFECT_MAP.put(entityId, effectInt);
    }

    public static int getEffect(int entityId) {
        return EFFECT_MAP.getOrDefault(entityId, 0);
    }

    public static void setHudData(long endOfCooldownTick, long actualBeginTick, long duration) {
        ClientEffectData.endOfCooldownTick = endOfCooldownTick;
        ClientEffectData.actualBeginTick = actualBeginTick;
        ClientEffectData.beginTick = endOfCooldownTick - actualBeginTick;
        ClientEffectData.duration = duration;
    }

    public static void resetHudData() {
        endOfCooldownTick = 0;
        actualBeginTick = 0;
        beginTick = 0;
        duration = 0;
    }

    public static long getEndOfCooldownTick() { return endOfCooldownTick; }
    public static long getActualBeginTick()   { return actualBeginTick; }
    public static long getBeginTick()         { return beginTick; }
    public static long getDuration()          { return duration; }
}
