package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.InfinityItems;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public class BlockItemInfinityMixin {
    @Inject(method = "useOn",at = @At("RETURN"))
    public void useOn(UseOnContext p_40581_, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack ITEM = p_40581_.getItemInHand();
        Player player = p_40581_.getPlayer();
        if(cir.getReturnValue().consumesAction() && player!=null && !player.isCreative() && InfinityItems.HAS(ITEM.getEnchantmentTags()) && !ITEM.getItem().equals(Items.POWDER_SNOW_BUCKET) && ITEM.getCount()<=1){
            ITEM.setCount(ITEM.getCount()+1);
        }
    }
}
