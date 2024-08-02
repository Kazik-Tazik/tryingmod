package net.yurkevichkazimir.tryingmod.entity.client;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.yurkevichkazimir.tryingmod.entity.animations.ModBinhliAnimationDefinitions;
import net.yurkevichkazimir.tryingmod.entity.custom.BinhliEntity;

public class BinhliModel<T extends Entity> extends HierarchicalModel<T> {

	private final ModelPart binhli;
	private final ModelPart head;


	public BinhliModel(ModelPart root) {
		this.binhli = root.getChild("binhli");
		this.head = binhli.getChild("full_body").getChild("body").getChild("head");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition binhli = partdefinition.addOrReplaceChild("binhli", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition full_body = binhli.addOrReplaceChild("full_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = full_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 46).addBox(-5.0F, -4.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 45).addBox(-5.0F, -5.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(38, 52).addBox(-4.0F, -6.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(50, 48).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(42, 19).addBox(-5.0F, -3.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 1.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(30, 57).addBox(-2.0F, -2.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.1F, -1.4F, 0.0F, 0.0F, 0.0F, -0.6545F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(58, 53).addBox(-2.0F, -2.0F, -1.0F, 6.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, -2.6F, 0.0F, 0.0F, 0.0F, 0.6545F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(38, 0).addBox(-6.0F, -17.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(32, 37).addBox(-7.0F, -38.0F, -4.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 34).addBox(-8.0F, -20.0F, -4.0F, 12.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(34, 26).addBox(-8.0F, -37.0F, -4.0F, 12.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-9.0F, -35.0F, -5.0F, 14.0F, 15.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.0F, 0.0F));

		PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r3 = mouth.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 25).addBox(-9.0F, -7.0F, 0.0F, 19.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -19.0F, -6.0F, 0.0F, 0.0F, 0.0785F));

		PartDefinition left_eye = head.addOrReplaceChild("left_eye", CubeListBuilder.create().texOffs(48, 9).addBox(-1.0F, -29.0F, -6.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition right_eye = head.addOrReplaceChild("right_eye", CubeListBuilder.create().texOffs(36, 46).addBox(-14.0F, -6.0F, -1.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -26.0F, -5.0F));

		PartDefinition left_antena = head.addOrReplaceChild("left_antena", CubeListBuilder.create().texOffs(48, 15).addBox(7.4F, -3.0F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -37.0F, -0.5F));

		PartDefinition cube_r4 = left_antena.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -8.0F, 0.0F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.309F));

		PartDefinition right_antena = head.addOrReplaceChild("right_antena", CubeListBuilder.create().texOffs(25, 53).addBox(-19.4F, -38.8F, 0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -1.0F, -1.0F));

		PartDefinition cube_r5 = right_antena.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(48, 17).addBox(-7.0F, -1.0F, 0.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -36.0F, 0.5F, 0.0F, 0.0F, 0.2618F));

		PartDefinition left_leg = full_body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(8, 52).addBox(-1.0F, -14.0F, 1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(16, 53).addBox(-1.0F, -2.0F, -2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, -1.0F));

		PartDefinition right_leg = full_body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(49, 53).addBox(-7.0F, -2.0F, -2.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 52).addBox(-7.0F, -14.0F, 1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 0.0F, -1.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		//this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModBinhliAnimationDefinitions.BINHLI_WALK, limbSwing, limbSwingAmount, 1f, 3f);
		this.animate(BinhliEntity.idleAnimationState, ModBinhliAnimationDefinitions.BINHLI_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -10.0F, 10.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 200F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 200F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		binhli.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return binhli;
	}
}