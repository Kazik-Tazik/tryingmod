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
import net.yurkevichkazimir.tryingmod.entity.animations.ModKamizelkaAnimationDefinitions;
import net.yurkevichkazimir.tryingmod.entity.custom.KamizelkaEntity;

public class KamizelkaModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart kamizelka;
	private final ModelPart head;



	public KamizelkaModel(ModelPart root) {
		this.kamizelka = root.getChild("kamizelka");
		this.head = kamizelka.getChild("full_body").getChild("body").getChild("head");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition kamizelka = partdefinition.addOrReplaceChild("kamizelka", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition full_body = kamizelka.addOrReplaceChild("full_body", CubeListBuilder.create(), PartPose.offset(-4.0F, -7.6F, 0.5F));

		PartDefinition body = full_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(27, 12).addBox(-4.0F, -1.0F, -2.0F, 8.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-5.0F, -3.0F, -3.0F, 10.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(23, 21).addBox(-4.0F, -5.0F, -2.0F, 8.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-3.0F, -8.0F, -1.0F, 6.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, 3.6F, -0.5F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(15, 32).addBox(-2.0F, 3.0F, -1.0F, 4.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

		PartDefinition cap = head.addOrReplaceChild("cap", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 2.0F, -5.0F, 12.0F, 1.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 21).addBox(-4.0F, 1.0F, -3.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(26, 28).addBox(-3.0F, 0.0F, -2.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(15, 29).addBox(-2.0F, -1.0F, -1.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition nose = full_body.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(35, 0).addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 12).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -1.4F, -2.5F));

		PartDefinition rightleg = full_body.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 5).addBox(-0.5F, 3.0F, -3.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.6F, 0.0F));

		PartDefinition leftleg = full_body.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(0, 5).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-0.5F, 3.0F, -3.5F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(6.5F, 3.6F, 0.0F));

		PartDefinition left_arm = full_body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(34, 18).addBox(-2.0F, -0.5F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 0.0F, 0.0F));

		PartDefinition right_arm = full_body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(26, 33).addBox(-5.0F, -0.5F, -0.5F, 7.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModKamizelkaAnimationDefinitions.KAMIZELKA_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(KamizelkaEntity.idleAnimationState, ModKamizelkaAnimationDefinitions.KAMIZELKA_IDLE, ageInTicks, 1f);
		this.animate(((KamizelkaEntity) entity).attackAnimationState, ModKamizelkaAnimationDefinitions.KAMIZELKA_ATTACK, ageInTicks, 1f);

	}

	private void applyHeadRotation(float pNetHeadYaw, float pHeadPitch, float pAgeInTicks) {
		pNetHeadYaw = Mth.clamp(pNetHeadYaw, -30.0F, 30.0F);
		pHeadPitch = Mth.clamp(pHeadPitch, -25.0F, 45.0F);

		this.head.yRot = pNetHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = pHeadPitch * ((float)Math.PI / 90F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		kamizelka.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return kamizelka;
	}
}