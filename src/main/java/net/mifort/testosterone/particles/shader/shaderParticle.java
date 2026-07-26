package net.mifort.testosterone.particles.shader;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.apache.logging.log4j.core.pattern.UuidPatternConverter;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public class shaderParticle extends Particle {
    // TODO: make player independent

    static final Map<Integer, Map<Integer, shaderParticle>> ACTIVE = new HashMap<>();
    static final Map<Integer, Integer> NEXT_ID = new HashMap<>();
    private final int groupId;
    private final int id;

    private Vec3[] topCoords;

    public float rotation;
    protected shaderParticle(ClientLevel level, double x, double y, double z, float rotation, int groupId) {
        super(level, x, y, z);
        this.lifetime = 2000;
        this.hasPhysics = false;
        this.xd = 0;
        this.yd = 0;
        this.zd = 0;
        this.rotation = rotation;

        this.groupId = groupId;

        if (!ACTIVE.containsKey(groupId)) {
            ACTIVE.put(groupId, new HashMap<>());
        }

        if (!NEXT_ID.containsKey(groupId)) {
            NEXT_ID.put(groupId, 0);
        } else {
            NEXT_ID.put(groupId, NEXT_ID.get(groupId) + 1);
        }

        Map<Integer, shaderParticle> group = ACTIVE.get(groupId);

        int id;
        do {
            id = NEXT_ID.get(groupId);
        } while (group.containsKey(id));

        group.put(id, this);
        this.id = id;

//        boolean dist = ACTIVE.get(this.groupId).get(this.id-1).getPos().distanceToSqr(new Vec3(x, y, z)) < 2;
//        if (dist) {
//            this.remove();
//        }

//        printMap(ACTIVE);
//        printMap(NEXT_ID);
    }

    public Vec3[] getTopCoords() {
        return topCoords;
    }

    @Override
    public void remove() {
        super.remove();
        ACTIVE.get(groupId).remove(id);
        if (ACTIVE.get(groupId).isEmpty()) {
            ACTIVE.remove(groupId);
        }
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public ParticleRenderType getRenderType() {
        return RenderTypeWithCustomShaders.INSTANCE;
    }

    @Override
    public void render(VertexConsumer buffer,
                       Camera camera,
                       float partialTick) {

        this.topCoords = ConnectedParticleGeometry.emit(buffer, camera, this.x, this.y, this.z,
                1f,
                1f,
                rotation,
                ACTIVE.get(this.groupId).get(this.id-1),
                this.getLightColor(partialTick));



//        if (getRenderType() instanceof cylinderParticleRenderType renderType) {
//            var testUniform = renderType.shader.getUniform("prevPos");
//            if (testUniform != null) {
//                testUniform.set(prevPos.toVector3f());
//            }
//        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<shaderParticleData> {
        public Factory(SpriteSet sprites) {
        }

        @Override
        public Particle createParticle(shaderParticleData data, ClientLevel level,
                                       double x, double y, double z,
                                       double dx, double dy, double dz) {
            // TODO: get velocity from server (check out particleData)
            Vec3 vel = Minecraft.getInstance().player.getDeltaMovement();
            float rot = (float) Math.atan2(vel.x, vel.z);
            return new shaderParticle(level, x, y, z, rot, data.id());
        }
    }

    public static void printMap(Map<?, ?> map) {
        printMap(map, 0);
    }

    private static void printMap(Map<?, ?> map, int indent) {
        String prefix = "  ".repeat(indent);

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            System.out.print(prefix + entry.getKey() + " -> ");

            Object value = entry.getValue();
            if (value instanceof Map<?, ?> nestedMap) {
                System.out.println("{");
                printMap(nestedMap, indent + 1);
                System.out.println(prefix + "}");
            } else {
                System.out.println(value);
            }
        }
    }
}
