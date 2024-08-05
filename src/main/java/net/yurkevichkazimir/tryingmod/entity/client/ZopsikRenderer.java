package net.yurkevichkazimir.tryingmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yurkevichkazimir.tryingmod.entity.custom.ZopsikEntity;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class ZopsikRenderer extends MobRenderer<ZopsikEntity, ZopsikModel<ZopsikEntity>> {
    public ZopsikRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ZopsikModel<>(pContext.bakeLayer(ModModelLayers.ZOPSIK_LAYER)), 0.6f);
    }

    @Override
    public void render(ZopsikEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ZopsikEntity pEntity) {
        return new ResourceLocation(tryingMod.MOD_ID, "textures/entity/zopsik.png");
    }

}