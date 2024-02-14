package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.InfinityItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public class MilkBucketMixin {
    @Inject(method = "finishUsingItem",at = @At("RETURN"), cancellable = true)
    public void finishUsingItem(ItemStack p_42923_, Level p_42924_, LivingEntity p_42925_, CallbackInfoReturnable<ItemStack> cir){
        if(InfinityItems.HAS(p_42923_.getEnchantmentTags())){
            ItemStack itemStack = Items.MILK_BUCKET.getDefaultInstance();
            itemStack.enchant(Enchantments.INFINITY_ARROWS,1);
            cir.setReturnValue(itemStack);
        }
    }
}
