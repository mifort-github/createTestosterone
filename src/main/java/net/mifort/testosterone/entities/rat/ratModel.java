package net.mifort.testosterone.entities.rat;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ratModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "rat"), "main");
	private final ModelPart bone;

	public ratModel(ModelPart root) {
		this.bone = root.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addBox(5.0F, -10.0F, -6.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 1).addBox(-8.0F, -10.0F, -6.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(6.0F, 0.0F, -8.5F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).mirror().addBox(-11.0F, -2.0F, -8.5F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).mirror().addBox(-11.0F, 0.0F, -8.5F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 0).addBox(6.0F, -2.0F, -8.5F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).addBox(4.0F, 5.0F, -9.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).mirror().addBox(-8.0F, 5.0F, -9.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(0, 5).addBox(4.0F, 5.0F, 8.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 5).mirror().addBox(-8.0F, 5.0F, 8.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(48, 8).addBox(-2.0F, 4.0F, 8.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(52, 3).addBox(-1.0F, 5.0F, 12.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 12).addBox(-3.0F, 0.0F, -12.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(30, 42).addBox(-4.0F, 0.0F, -11.0F, 8.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition Tail_r1 = bone.addOrReplaceChild("Tail_r1", CubeListBuilder.create().texOffs(0, 3).addBox(-3.0F, -2.0F, 0.0F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 8.0F, 0.0F, 1.5708F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}