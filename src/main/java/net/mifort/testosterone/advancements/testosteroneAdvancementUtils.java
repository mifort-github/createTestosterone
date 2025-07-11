package net.mifort.testosterone.advancements;

import net.minecraft.advancements.CriteriaTriggers;

public class testosteroneAdvancementUtils {
    public static final damageAdvancementCriteria DAMAGE_TAKEN = new damageAdvancementCriteria();
    public static final inebriateAdvancementCriteria INEBRIATE = new inebriateAdvancementCriteria();
    public static final roadkillAdvancementCriteria ROADKILL = new roadkillAdvancementCriteria();

    public static void register() {
        CriteriaTriggers.register(DAMAGE_TAKEN);
        CriteriaTriggers.register(INEBRIATE);
        CriteriaTriggers.register(ROADKILL);
    }
}
