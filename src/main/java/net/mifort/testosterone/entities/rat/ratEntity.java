package net.mifort.testosterone.entities.rat;

import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.items.testosteroneModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ratEntity extends Animal {
    public ratEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(2, new net.minecraft.world.entity.ai.goal.TemptGoal(this, 1.2D, Ingredient.of(testosteroneModItems.CHEESE_ON_A_STICK.get()), false));
        this.goalSelector.addGoal(3, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.COOKED_BEEF);
    }

    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob parent) {
        return testosteroneEntities.RAT.get().create(level);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (this.getPassengers().isEmpty()
                && player.isHolding(testosteroneModItems.CHEESE_ON_A_STICK.get())
                && !this.isBaby()) {
            player.startRiding(this);
            return InteractionResult.SUCCESS;
        }
        return super.mobInteract(player, hand);
    }



    @Nullable
    public Player getControllingPassenger() {
        for (Entity passenger : this.getPassengers()) {
            if (passenger instanceof Player player) {
                return player;
            }
        }
        return null;
    }

    protected void tickRidden(@NotNull Player pPlayer, @NotNull Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);

        if (pPlayer.isHolding(testosteroneModItems.CHEESE_ON_A_STICK.get())) {
            this.setRot(pPlayer.getYRot(), pPlayer.getXRot() * 0.5F);
            this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();

            if (isBoosting()) {
                ItemStack stick = getStick(pPlayer);

                stick.hurtAndBreak(1, pPlayer, entity -> {
                    EquipmentSlot slot = getStickHand(pPlayer);
                    entity.setItemSlot(slot, Items.FISHING_ROD.getDefaultInstance());
                    entity.broadcastBreakEvent(slot);
                });
            }
        }
    }

    protected float getRiddenSpeed(Player pPlayer) {
        if (pPlayer.isHolding(testosteroneModItems.CHEESE_ON_A_STICK.get())) {
            if (isBoosting()) {
                return (float) (this.getAttributeValue(Attributes.MOVEMENT_SPEED) * ConfigRegistry.RAT_BOOST_MULTIPLIER.get());

            } else {
                return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED);
            }

        } else {
            return 0;
        }
    }

    public boolean isBoosting() {
        Player rider = getControllingPassenger();

        if (rider != null) {
            ItemStack stick = getStick(rider);

            if (stick.hasTag()) {
                return stick.getTag().getBoolean("Boost");
            }
        }

        return false;
    }

    public ItemStack getStick(Player player) {
        ItemStack mainHandItem = player.getMainHandItem();
        ItemStack offHandItem = player.getOffhandItem();

        return mainHandItem.getItem().equals(testosteroneModItems.CHEESE_ON_A_STICK.get()) ? mainHandItem : offHandItem;
    }

    public EquipmentSlot getStickHand(Player player) {
        ItemStack mainHandItem = player.getMainHandItem();

        return mainHandItem.getItem().equals(testosteroneModItems.CHEESE_ON_A_STICK.get()) ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
    }
}
