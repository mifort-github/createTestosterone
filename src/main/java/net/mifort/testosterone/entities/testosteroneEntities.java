package net.mifort.testosterone.entities;

import net.mifort.testosterone.entities.rat.ratEntity;
import net.mifort.testosterone.testosterone;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class testosteroneEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, testosterone.MOD_ID);


    public static final RegistryObject<EntityType<ratEntity>> RAT =
            ENTITY_TYPES.register("rat", () -> EntityType.Builder.of(ratEntity::new, MobCategory.CREATURE)
                    .sized(1, 1).build("rat"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
