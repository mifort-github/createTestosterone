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
	private final ModelPart root;
	private final ModelPart body;
	private final ModelPart WhiskersL;
	private final ModelPart WhiskersR;
	private final ModelPart TailBig;
	private final ModelPart TailSmall;
	private final ModelPart nose;
	private final ModelPart snout;
	private final ModelPart EarL;
	private final ModelPart EarE;
	private final ModelPart frontLleg;
	private final ModelPart frontRleg;
	private final ModelPart backLleg;
	private final ModelPart backRleg;

	public ratModel(ModelPart root) {
		this.root = root;
		this.body = root.getChild("body");
		this.WhiskersL = this.body.getChild("WhiskersL");
		this.WhiskersR = this.body.getChild("WhiskersR");
		this.TailBig = this.body.getChild("TailBig");
		this.TailSmall = this.TailBig.getChild("TailSmall");
		this.nose = this.body.getChild("nose");
		this.snout = this.nose.getChild("snout");
		this.EarL = this.body.getChild("EarL");
		this.EarE = this.body.getChild("EarE");
		this.frontLleg = root.getChild("frontLleg");
		this.frontRleg = root.getChild("frontRleg");
		this.backLleg = root.getChild("backLleg");
		this.backRleg = root.getChild("backRleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -8.0F, -8.0F, 16.0F, 16.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

		PartDefinition WhiskersL = body.addOrReplaceChild("WhiskersL", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.5F, 0.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, -1.5F, 0.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, -0.5F, -8.5F));

		PartDefinition WhiskersR = body.addOrReplaceChild("WhiskersR", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-5.0F, -1.5F, 0.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 0).mirror().addBox(-5.0F, 0.5F, 0.0F, 5.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, -0.5F, -8.5F));

		PartDefinition TailBig = body.addOrReplaceChild("TailBig", CubeListBuilder.create().texOffs(48, 8).addBox(-2.0F, -2.0F, 0.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 8.0F));

		PartDefinition TailSmall = TailBig.addOrReplaceChild("TailSmall", CubeListBuilder.create().texOffs(52, 3).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 4.0F));

		PartDefinition nose = body.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(30, 42).addBox(-4.0F, -2.0F, -0.5F, 8.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -10.5F));

		PartDefinition snout = nose.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(0, 12).addBox(-5.0F, -3.0F, -1.0F, 6.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 1.0F, -0.5F));

		PartDefinition EarL = body.addOrReplaceChild("EarL", CubeListBuilder.create().texOffs(8, 1).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, -8.0F, -7.0F));

		PartDefinition EarE = body.addOrReplaceChild("EarE", CubeListBuilder.create().texOffs(8, 1).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-6.5F, -8.0F, -7.0F));

		PartDefinition frontLleg = partdefinition.addOrReplaceChild("frontLleg", CubeListBuilder.create().texOffs(0, 5).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 22.5F, -8.5F));

		PartDefinition frontRleg = partdefinition.addOrReplaceChild("frontRleg", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 22.5F, -8.5F));

		PartDefinition backLleg = partdefinition.addOrReplaceChild("backLleg", CubeListBuilder.create().texOffs(0, 5).addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(6.0F, 22.5F, 8.5F));

		PartDefinition backRleg = partdefinition.addOrReplaceChild("backRleg", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(-2.0F, -1.5F, -0.5F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-6.0F, 22.5F, 8.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (((ratEntity) entity).isBoosting()) {
			this.animateWalk(ratAnimations.SPIN, limbSwing, limbSwingAmount, 1, 1);
		} else {
			this.animateWalk(ratAnimations.WALK, limbSwing, limbSwingAmount, 2, 1);
		}

		this.animate(((ratEntity) entity).idleAnimationState, ratAnimations.IDLE, ageInTicks, 1f);
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