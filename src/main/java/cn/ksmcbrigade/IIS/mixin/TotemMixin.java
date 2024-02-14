package cn.ksmcbrigade.IIS.mixin;

import net.minecraft.advancements.critereon.UsedTotemTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static cn.ksmcbrigade.IIS.InfinityItems.HAS;

@Mixin(UsedTotemTrigger.class)
public abstract class TotemMixin {

    @Inject(method = "trigger",at = @At("TAIL"))
    public void SetAir(ServerPlayer p_74432_, ItemStack p_74433_, CallbackInfo ci){
        if(HAS(p_74433_.getEnchantmentTags())){
            ItemStack itemStack = Items.TOTEM_OF_UNDYING.getDefaultInstance();
            itemStack.enchant(Enchantments.INFINITY_ARROWS,1);
            p_74432_.setItemInHand(p_74432_.getUsedItemHand(),itemStack);
        }
    }
}
