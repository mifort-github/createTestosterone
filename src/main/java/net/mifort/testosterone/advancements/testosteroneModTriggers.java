package net.mifort.testosterone.advancements;

import net.mifort.testosterone.testosterone;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class testosteroneModTriggers {

    public static final DeferredRegister<CriterionTrigger<?>> TRIGGERS =
            DeferredRegister.create(Registries.TRIGGER_TYPE, testosterone.MOD_ID);

    public static final Supplier<damageAdvancementCriteria> DAMAGE_TAKEN =
            TRIGGERS.register("damage_taken", damageAdvancementCriteria::new);

    public static final Supplier<inebriateAdvancementCriteria> INEBRIATE =
            TRIGGERS.register("inebriate", inebriateAdvancementCriteria::new);

    public static final Supplier<roadkillAdvancementCriteria> ROADKILL =
            TRIGGERS.register("roadkill", roadkillAdvancementCriteria::new);
}