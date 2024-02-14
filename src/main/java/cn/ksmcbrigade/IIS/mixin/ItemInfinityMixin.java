package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.InfinityItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Item.class)
public class ItemInfinityMixin {
    @Inject(method = "inventoryTick",at = @At("TAIL"))
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_, CallbackInfo ci) {
        if(!p_41404_.isEmpty() && !(p_41404_.getItem() instanceof BlockItem) && InfinityItems.HAS(p_41404_.getEnchantmentTags())){
            p_41404_.setDamageValue(0);
            if(p_41404_.getCount()==1 && p_41404_.getMaxStackSize()!=1){
                p_41404_.setCount(2);
            }
        }
    }
}
