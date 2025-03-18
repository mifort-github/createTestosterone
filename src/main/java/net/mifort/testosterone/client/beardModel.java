package net.mifort.testosterone.client;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class beardModel extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "beardmodel2"), "main");
	private final ModelPart bb_main;

	public beardModel(ModelPart root) {
        super(RenderType::entitySolid);
        this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(4, 17).addBox(-2.0F, -27.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(19, 18).addBox(-4.0F, -26.0F, -5.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(19, 18).mirror().addBox(2.0F, -26.0F, -5.0F, 2.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
				.texOffs(5, 3).addBox(-3.0F, -23.0F, -5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(27, 16).addBox(3.25F, -28.75F, -3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 18).addBox(3.25F, -27.75F, -4.25F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(27, 22).addBox(3.25F, -25.75F, -4.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(26, 18).addBox(-4.25F, -27.75F, -4.25F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(27, 16).addBox(-4.25F, -28.75F, -3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(27, 22).addBox(-4.25F, -25.75F, -4.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(20, 28).addBox(-2.0F, -22.0F, -5.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(4, 22).addBox(-2.0F, -25.0F, -5.0F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 23).addBox(1.0F, -26.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 23).addBox(-2.0F, -26.0F, -5.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}