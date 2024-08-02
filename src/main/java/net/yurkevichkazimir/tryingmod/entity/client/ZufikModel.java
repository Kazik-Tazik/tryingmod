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
import net.yurkevichkazimir.tryingmod.entity.animations.ModZufikAnimationDefinition;
import net.yurkevichkazimir.tryingmod.entity.custom.ZufikEntity;

public class ZufikModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart zufik;
	private final ModelPart head;

	public ZufikModel(ModelPart root) {
		this.zufik = root.getChild("zufik");
		this.head = zufik.getChild("torso").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition zufik = partdefinition.addOrReplaceChild("zufik", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition torso = zufik.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, -32.0F, -1.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, -9.0F, 16.0F, 5.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-6.0F, -3.0F, -7.0F, 12.0F, 3.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 2.0F));

		PartDefinition left_leg = torso.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(-2, -2).addBox(-1.0F, 30.0F, -6.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 31.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 1.0F));

		PartDefinition right_leg = torso.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(-2, 5).addBox(-1.0F, 30.0F, -6.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(8, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 31.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 1.0F));

		PartDefinition left_arm = torso.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(36, 21).addBox(-1.0F, -1.0F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(36, 25).addBox(5.0F, -3.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -1.3F, 1.0F));

		PartDefinition right_arm = torso.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(16, 36).addBox(-6.5F, -1.3F, -1.0F, 8.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 28).addBox(-6.5F, -3.3F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, -1.0F, 1.0F));

		PartDefinition nose = torso.addOrReplaceChild("nose", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = nose.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 21).addBox(-1.0F, -4.0F, -1.0F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.9F, -7.7F, -0.7418F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModZufikAnimationDefinition.ZUFIK_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(ZufikEntity.idleAnimationState, ModZufikAnimationDefinition.ZUFIK_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 180F);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		zufik.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return zufik;
	}
}