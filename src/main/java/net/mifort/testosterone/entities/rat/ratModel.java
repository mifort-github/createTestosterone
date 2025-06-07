package net.mifort.testosterone.entities.rat;// Made with Blockbench 4.12.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ratModel<T extends Entity> extends HierarchicalModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "rat"), "main");
	private final ModelPart body;
	private final ModelPart nose;
	private final ModelPart frontLleg;
	private final ModelPart frontRleg;
	private final ModelPart backLleg;
	private final ModelPart backRleg;
	private final ModelPart root;


	public ratModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.nose = this.body.getChild("nose");
		this.frontLleg = root.getChild("frontLleg");
		this.frontRleg = root.getChild("frontRleg");
		this.backLleg = root.getChild("backLleg");
		this.backRleg = root.getChild("backRleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -7.1667F, -5.4444F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(5.0F, -9.1667F, -3.4444F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(-8.0F, -9.1667F, -3.4444F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(6.0F, 0.8333F, -5.9444F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-11.0F, -1.1667F, -5.9444F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(-11.0F, 0.8333F, -5.9444F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).addBox(6.0F, -1.1667F, -5.9444F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(48, 8).addBox(-2.0F, 4.8333F, 10.5556F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 3).addBox(-1.0F, 5.8333F, 14.5556F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 15.1667F, -2.5556F));

		PartDefinition nose = body.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 12).addBox(-3.0F, -2.0F, -1.5F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(30, 42).addBox(-4.0F, -2.0F, -0.5F, 8.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.8333F, -7.9444F));

		PartDefinition frontLleg = partdefinition.addOrReplaceChild("frontLleg", CubeListBuilder.create().texOffs(0, 5).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 22.5F, -8.5F));

		PartDefinition frontRleg = partdefinition.addOrReplaceChild("frontRleg", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 22.5F, -8.5F));

		PartDefinition backLleg = partdefinition.addOrReplaceChild("backLleg", CubeListBuilder.create().texOffs(0, 5).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 22.5F, 8.5F));

		PartDefinition backRleg = partdefinition.addOrReplaceChild("backRleg", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 22.5F, 8.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

//		if (((ratEntity) entity).isBoosting()) {
//
//		}

		this.animateWalk(ratAnimations.WALK, limbSwing, limbSwingAmount, 2, 1);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontLleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		frontRleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		backLleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		backRleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}