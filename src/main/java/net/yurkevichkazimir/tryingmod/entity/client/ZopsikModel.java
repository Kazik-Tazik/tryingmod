package net.yurkevichkazimir.tryingmod.entity.client;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.yurkevichkazimir.tryingmod.entity.animations.ModZopsikAnimationDefinitions;
import net.yurkevichkazimir.tryingmod.entity.animations.ModZufikAnimationDefinition;
import net.yurkevichkazimir.tryingmod.entity.custom.ZopsikEntity;
import net.yurkevichkazimir.tryingmod.entity.custom.ZufikEntity;

public class ZopsikModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart zopsik;
	private final ModelPart head;

	public ZopsikModel(ModelPart root) {
		this.zopsik = root.getChild("zopsik");
		this.head = zopsik.getChild("full_body").getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition zopsik = partdefinition.addOrReplaceChild("zopsik", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition full_body = zopsik.addOrReplaceChild("full_body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = full_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 19).addBox(-1.0F, -13.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition pelvis = body.addOrReplaceChild("pelvis", CubeListBuilder.create().texOffs(36, 19).addBox(-11.0F, -1.0F, -1.0F, 21.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition right_leg = pelvis.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(36, 33).addBox(-24.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, 5.0F, 0.0F));

		PartDefinition left_leg = pelvis.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 33).addBox(10.0F, -6.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(36, 27).addBox(-12.0F, -1.0F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -11.0F, 0.0F));

		PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(36, 23).addBox(0.0F, -1.4F, -1.0F, 12.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -10.0F, 0.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition skull = head.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(36, 33).addBox(-4.0F, -1.0F, -6.0F, 8.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(42, 0).addBox(-4.0F, -11.0F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(-6.0F, -3.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 19).addBox(-6.0F, -10.0F, -6.0F, 12.0F, 2.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-7.0F, -8.0F, -7.0F, 14.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(42, 9).addBox(-5.0F, -3.0F, -7.0F, 10.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(6, 26).addBox(-0.5F, -6.0F, -7.6F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -13.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(0, 6).addBox(-4.6F, -21.7F, -7.6F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.6F, -22.0F, -7.7F, 4.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 1).addBox(-2.7F, -20.3F, -7.7F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(1.9F, -20.6F, -7.8F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModZopsikAnimationDefinitions.ZOPSIK_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(ZopsikEntity.idleAnimationState, ModZopsikAnimationDefinitions.ZOPSIK_IDLE, ageInTicks, 1f);
	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -10.0F, 10.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -10.0F, 10.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 200F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 120F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		zopsik.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return zopsik;
	}
}