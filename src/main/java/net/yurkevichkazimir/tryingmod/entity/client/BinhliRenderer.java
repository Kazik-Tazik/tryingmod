package net.yurkevichkazimir.tryingmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yurkevichkazimir.tryingmod.entity.custom.BinhliEntity;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class BinhliRenderer extends MobRenderer<BinhliEntity, BinhliModel<BinhliEntity>> {
    public BinhliRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BinhliModel<>(pContext.bakeLayer(ModModelLayers.BINHLI_LAYER)), 0.7f);
    }

    @Override
    public void render(BinhliEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.7f, 0.7f, 0.7f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(BinhliEntity pEntity) {
        return new ResourceLocation(tryingMod.MOD_ID, "textures/entity/binhli.png");
    }

}