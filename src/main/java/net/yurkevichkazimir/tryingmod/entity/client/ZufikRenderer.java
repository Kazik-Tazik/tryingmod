package net.yurkevichkazimir.tryingmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yurkevichkazimir.tryingmod.entity.custom.ZufikEntity;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ZufikRenderer extends MobRenderer<ZufikEntity, ZufikModel<ZufikEntity>> {
    public ZufikRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ZufikModel<>(pContext.bakeLayer(ModModelLayers.ZUFIK_LAYER)), 0.7f);
    }

    @Override
    public void render(ZufikEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.7f, 0.7f, 0.7f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ZufikEntity pEntity) {
        return new ResourceLocation(tryingMod.MOD_ID, "textures/entity/zufik.png");
    }

}