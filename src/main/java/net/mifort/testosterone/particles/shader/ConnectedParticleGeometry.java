package net.mifort.testosterone.particles.shader;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.mifort.testosterone.particles.BillBoardUtils;
import net.minecraft.client.Camera;
import net.minecraft.network.chat.Component;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import javax.annotation.Nullable;

public class ConnectedParticleGeometry {
    public static Vec3[] emit(VertexConsumer buffer, Camera camera,
                              double wx, double wy, double wz,
                              float width, float height,
                              float rotRadians,
                              @Nullable shaderParticle shaderParticle,
                              int packedLight) {

        rotRadians += (float) (Math.PI / 2);

        Vec3 camPos = camera.getPosition();
        float cx = (float)(wx - camPos.x);
        float cy = (float)(wy - camPos.y);
        float cz = (float)(wz - camPos.z);

        int lu = packedLight & 0xFFFF;
        int lv = (packedLight >> 16) & 0xFFFF;

        float hw = width * 0.5f;
        float hh = height * 0.5f;

        Vector3f[] corners = new Vector3f[]{
                new Vector3f(-hw, -hh, 0.0f),   // bottom-left
                new Vector3f(-hw, hh, 0.0f),    // top-left
                new Vector3f( hw, hh, 0.0f),    // top-right
                new Vector3f( hw, -hh, 0.0f)    // bottom-right
        };

        for (Vector3f c : corners) {
            c.rotateY(rotRadians);
        }

// Figure out the connection edge (in camera-relative world space) up front
        Vector3f connectedAvg;
        if (shaderParticle != null) {
            Vec3[] toConnect = shaderParticle.getTopCoords();
            connectedAvg = new Vector3f(toConnect[0].toVector3f()).add(toConnect[1].toVector3f()).mul(0.5f);
        } else {
            connectedAvg = new Vector3f(corners[0]).add(corners[1]).mul(0.5f).add(cx, cy, cz);
        }

// This particle's own (still local, untranslated) right-edge midpoint
        Vector3f ownLocalAvg = new Vector3f(corners[2]).add(corners[3]).mul(0.5f).add(cx, cy, cz);

        Vector3f directionOfParticle = new Vector3f(connectedAvg).sub(ownLocalAvg);

        BillBoardUtils.drawVector(directionOfParticle, 1, 1, 1, new Vec3(wx, wy, wz));

        for (Vector3f c : corners) {
            c.rotate(BillBoardUtils.billboardAroundVector(directionOfParticle, camPos, new Vec3(wx, wy, wz))); // now has directionOfParticle available if the signature needs it
            c.add(cx, cy, cz); // camera space
        }
        BillBoardUtils.addDebugParticle(new Vector3f().zero(), 1f, 0f, 0f, new Vec3(corners[3]).subtract(cx, cy, cz).add(wx, wy, wz));

        if (shaderParticle != null) {
            Vec3[] toConnect = shaderParticle.getTopCoords();
            if (toConnect != null) {
                Vec3 placeAt = new Vec3(toConnect[0].toVector3f()).subtract(cx, cy, cz).add(wx, wy, wz);
                Vector3f v0 = new Vector3f(toConnect[1].toVector3f()).sub(toConnect[0].toVector3f());
                Vector3f v1 = new Vector3f(corners[3]).sub(toConnect[0].toVector3f()).normalize();
                Vector3f v2 = new Vector3f(corners[2]).sub(toConnect[0].toVector3f()).normalize();

                float dot01 = v0.dot(v1);
                float dot02 = v0.dot(v2);

                camera.getEntity().sendSystemMessage(Component.literal(dot01 + " : " + dot02));


                BillBoardUtils.drawVector(v0, 0f, 0.23f, 1f, placeAt);
                BillBoardUtils.drawVector(v1, 1f, 1f, 0.11f, placeAt);
                BillBoardUtils.drawVector(v2, 0.69f, 0.23f, 0.11f, placeAt);

                corners[0] = toConnect[dot01 > dot02 ? 1 : 0].toVector3f();
                corners[1] = toConnect[dot01 > dot02 ? 0 : 1].toVector3f();
            }
        }



        vertex(buffer, corners[0], 0f, 1f, lu, lv);
        vertex(buffer, corners[1], 0f, 0f, lu, lv);
        vertex(buffer, corners[2], 1f, 0f, lu, lv);
        vertex(buffer, corners[3], 1f, 1f, lu, lv);

        return new Vec3[]{
                new Vec3(corners[3].x, corners[3].y, corners[3].z), // bottom-right (own)
                new Vec3(corners[2].x, corners[2].y, corners[2].z)  // top-right (own)
        };
    }

    private static Vector3f rotate(Vector3f vec, Quaternionf q) {
        return vec.rotate(q);
    }

    private static void vertex(VertexConsumer buf,
                               float x, float y, float z,
                               float u, float v,
                               int lu, int lv) {
        buf.addVertex(x, y, z)
                .setUv(u, v)
                .setColor(1f, 1f, 1f, 1f)
                .setUv2(lu, lv);
    }

    private static void vertex(VertexConsumer buf,
                               Vector3f xyz,
                               float u, float v,
                               int lu, int lv) {
        buf.addVertex(xyz)
                .setUv(u, v)
                .setColor(1f, 1f, 1f, 1f)
                .setUv2(lu, lv);
    }
}