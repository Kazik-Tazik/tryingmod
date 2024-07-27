package net.yurkevichkazimir.tryingmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.yurkevichkazimir.tryingmod.entity.custom.KamizelkaEntity;
import net.yurkevichkazimir.tryingmod.tryingMod;

public class KamizelkaRenderer extends MobRenderer<KamizelkaEntity, KamizelkaModel<KamizelkaEntity>> {
    public KamizelkaRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new KamizelkaModel<>(pContext.bakeLayer(ModModelLayers.KAMIZELKA_LAYER)), 0.5f);
    }

    @Override
    public void render(KamizelkaEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(5f, 5f, 5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(KamizelkaEntity pEntity) {
        return new ResourceLocation(tryingMod.MOD_ID, "textures/entity/kamizelka.png");
    }

}