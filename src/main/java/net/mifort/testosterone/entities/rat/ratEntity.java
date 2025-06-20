package net.mifort.testosterone.entities.rat;

import net.mifort.testosterone.blocks.testosteroneModBlocks;
import net.mifort.testosterone.config.ConfigRegistry;
import net.mifort.testosterone.entities.testosteroneEntities;
import net.mifort.testosterone.items.testosteroneModItems;
import net.mifort.testosterone.testosterone;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cat;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class ratEntity extends Animal implements PlayerRideableJumping {
    public ratEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Ocelot.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(0, new AvoidEntityGoal<>(this, Cat.class, 6.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.15D));
        this.goalSelector.addGoal(3, new net.minecraft.world.entity.ai.goal.TemptGoal(this, 1.2D, Ingredient.of(testosteroneModItems.CHEESE_ON_A_STICK.get(), testosteroneModBlocks.CHEESE_BLOCK), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 3f));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

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
        return pStack.is(testosteroneModBlocks.CHEESE_BLOCK.asItem());
    }

    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob parent) {
        return testosteroneEntities.RAT.get().create(level);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (this.getPassengers().isEmpty()
                && isHoldingStick(player)
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

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 120;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    protected void tickRidden(@NotNull Player pPlayer, @NotNull Vec3 pTravelVector) {
        super.tickRidden(pPlayer, pTravelVector);

        if (isHoldingStick(pPlayer)) {
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

    public boolean isHoldingStick(Player player) {
        return player.isHolding(testosteroneModItems.CHEESE_ON_A_STICK.get());
    }

    protected float getRiddenSpeed(Player pPlayer) {
        if (isHoldingStick(pPlayer)) {
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

    @Override
    protected void checkFallDamage(double pY, boolean pOnGround, BlockState pState, BlockPos pPos) {
        super.checkFallDamage(pY, pOnGround, pState, pPos);

        if (pOnGround) {
            this.fallDistance = this.fallDistance - 24;
        }
    }

    @Override
    public void onPlayerJump(int pJumpPower) {
        this.extraJump = this.onGround();
        this.addDeltaMovement(new Vec3(0, (double) pJumpPower / 50, 0));
    }

    private boolean extraJump = true;

    @Override
    public boolean canJump() {
        if (this.getControllingPassenger() != null) {
            return (this.onGround() || this.extraJump) && isHoldingStick(this.getControllingPassenger());
        } else {
            return false;
        }
    }

    @Override
    public void handleStartJump(int pJumpPower) {

    }

    @Override
    public void handleStopJump() {

    }
}
