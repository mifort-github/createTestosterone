package net.mifort.testosterone.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class BillBoardUtils {
    public static Quaternionf calculateRotationAndRotate(float yaw, float pitch, Vec3 camPos, Vec3 particlePos) {
        Quaternionf initialRot = new Quaternionf().rotateLocalZ(pitch).rotateLocalY(yaw);

        Quaternionf billboard = calculateRotation(yaw, pitch, camPos, particlePos);

        // combine rotations
        Quaternionf finalRot = billboard.mul(initialRot);

        return finalRot;
    }

    public static Quaternionf calculateRotation(float yaw, float pitch, Vec3 camPos, Vec3 particlePos) {
        Quaternionf rotation = new Quaternionf().rotateLocalZ(pitch).rotateLocalY(yaw);

        // X BASE VECTOR
        Vector3f baseDirX = new Vector3f(1.0f, 0.0f, 0.0f);
        baseDirX.rotate(rotation);
        Vector3f rotXOfParticleAsVec = new Vector3f(baseDirX).normalize();

        // get rotation so that the particle faces Z+
        Quaternionf fixRotation = new Quaternionf()
                .rotationTo(rotXOfParticleAsVec, new Vector3f(0.0f, 0.0f, 1.0f));

        Vector3f up = new Vector3f(0, 1, 0);
        up.rotate(rotation);
        Vector3f upToFix = new Vector3f(up).rotate(fixRotation).normalize();
        Quaternionf fixUpRot = new Quaternionf().rotationTo(upToFix, new Vector3f(0, 1, 0));

        Vector3f Z = new Vector3f(0, 0, -1);
        Z.rotate(rotation);
        Vector3f ZToFix = new Vector3f(Z).rotate(fixRotation).rotate(fixUpRot).normalize();
        Quaternionf fixZRot = new Quaternionf().rotationTo(ZToFix, new Vector3f(1, 0, 0));

        // get player position rotated relative to the Z+ version of the particle
        Vector3f playerRelativeToParticle = camPos.subtract(particlePos).toVector3f().rotate(fixRotation).rotate(fixUpRot).rotate(fixZRot);
        float angle = (float) Math.atan2(playerRelativeToParticle.y, playerRelativeToParticle.x);

        Quaternionf billboard = new Quaternionf().rotateAxis(angle, rotXOfParticleAsVec);

        return billboard;
    }

    public static Quaternionf calculateRotationAndRotateWithDebugging(float yaw, float pitch, Vec3 camPos, Vec3 particlePos) {
        Quaternionf initialRot = new Quaternionf().rotateLocalZ(pitch).rotateLocalY(yaw);

        Quaternionf billboard = calculateRotationWithDebugging(yaw, pitch, camPos, particlePos);

        // combine rotations
        Quaternionf finalRot = billboard.mul(initialRot);

        return finalRot;
    }

    public static Quaternionf calculateRotationWithDebugging(float yaw, float pitch, Vec3 camPos, Vec3 particlePos) {
        addDebugParticle(new Vector3f(0f), 0f, 0f, 0f, particlePos);
        Quaternionf rotation = new Quaternionf().rotateLocalZ(pitch).rotateLocalY(yaw);

        // X BASE VECTOR
        Vector3f baseDirX = new Vector3f(1.0f, 0.0f, 0.0f);
        baseDirX.rotate(rotation);
        Vector3f rotXOfParticleAsVec = new Vector3f(baseDirX).normalize();
        drawVector(rotXOfParticleAsVec, 1f, 0f, 0f, particlePos);

        // get rotation so that the particle faces Z+
        Quaternionf fixRotation = new Quaternionf()
                .rotationTo(rotXOfParticleAsVec, new Vector3f(0.0f, 0.0f, 1.0f));
        drawVector(new Vector3f(baseDirX).rotate(fixRotation), 1f, 0.4f, 0f, particlePos);

        Vector3f up = new Vector3f(0, 1, 0);
        up.rotate(rotation);
        drawVector(up, 0, 1, 0, particlePos);
        Vector3f upToFix = new Vector3f(up).rotate(fixRotation).normalize();
        Quaternionf fixUpRot = new Quaternionf().rotationTo(upToFix, new Vector3f(0, 1, 0));
        drawVector(upToFix, 0.4f, 1f, 0f, particlePos);
        drawVector(new Vector3f(upToFix).rotate(fixUpRot).normalize(), 1f, 1f, 0f, particlePos);

        Vector3f Z = new Vector3f(0, 0, -1);
        Z.rotate(rotation);
        drawVector(Z, 0, 0, 1, particlePos);
        Vector3f ZToFix = new Vector3f(Z).rotate(fixRotation).rotate(fixUpRot).normalize();
        Quaternionf fixZRot = new Quaternionf().rotationTo(ZToFix, new Vector3f(1, 0, 0));
        drawVector(ZToFix, 0.4f, 0f, 1f, particlePos);
        drawVector(new Vector3f(ZToFix).rotate(fixZRot).normalize(), 1f, 1f, 1f, particlePos);

        // get player position rotated relative to the Z+ version of the particle
        Vector3f playerRelativeToParticle = camPos.subtract(particlePos).toVector3f().rotate(fixRotation).rotate(fixUpRot).rotate(fixZRot);
        float angle = (float) Math.atan2(playerRelativeToParticle.y, playerRelativeToParticle.x);

        Quaternionf billboard = new Quaternionf().rotateAxis(angle, rotXOfParticleAsVec);
        return billboard;
    }

    public static Quaternionf billboardAroundVector(Vector3f vec, Vec3 camPos, Vec3 particlePos) {
        float[] rots = directionToPitchYaw(vec);

        return calculateRotation(rots[0], rots[1], camPos, particlePos);
    }

    public static Quaternionf billboardAroundVectorWithDebugging(Vector3f vec, Vec3 camPos, Vec3 particlePos) {
        float[] rots = directionToPitchYaw(vec);

        return calculateRotationWithDebugging(rots[0], rots[1], camPos, particlePos);
    }

    private static float[] directionToPitchYaw(Vector3f dir) {
        Vector3f d = new Vector3f(dir).normalize();
        float horizontalDist = (float) Math.sqrt(d.x * d.x + d.z * d.z);
        float pitch = (float) Math.atan2(-d.y, horizontalDist);
        float yaw   = (float) Math.atan2(d.z, -d.x);
        return new float[]{yaw, pitch};
    }

    public static void addDebugParticle(Vector3f vec, float r, float g, float b, Vec3 particlePos) {
        if (Minecraft.getInstance().isPaused()) return;

        Minecraft.getInstance().player.level().addParticle(new DustParticleOptions(new Vector3f(r, g, b), 0.2f),
                particlePos.x + vec.x, particlePos.y + vec.y, particlePos.z + vec.z,
                0, 0, 0);
    }

    public static void drawVector(Vector3f vec, float r, float g, float b, Vec3 particlePos) {
        if (Minecraft.getInstance().isPaused()) return;
        int times = 5;
        Vector3f length = vec.div(times);
        for (int i = 0; i < times; i++) {
            addDebugParticle(new Vector3f(length).mul(i+1), r, g, b, particlePos);
        }
    }
}
