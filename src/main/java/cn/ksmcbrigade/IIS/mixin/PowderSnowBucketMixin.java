package cn.ksmcbrigade.IIS.mixin;

import cn.ksmcbrigade.IIS.InfinityItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SolidBucketItem.class)
public abstract class PowderSnowBucketMixin extends BlockItem implements DispensibleContainerItem {

    public PowderSnowBucketMixin(Block p_40565_, Properties p_40566_) {
        super(p_40565_, p_40566_);
    }

    /**
     * @author KSmc_brigade
     * @reason reUseOn
     */
    @Overwrite
    public InteractionResult useOn(UseOnContext p_151197_){
        InteractionResult interactionresult = super.useOn(p_151197_);
        Player player = p_151197_.getPlayer();
        if (interactionresult.consumesAction() && player != null && !player.isCreative()) {
            InteractionHand interactionhand = p_151197_.getHand();
            if(InfinityItems.HAS(player.getItemInHand(interactionhand).getEnchantmentTags())){
                ItemStack item = Items.POWDER_SNOW_BUCKET.getDefaultInstance();
                item.enchant(Enchantments.INFINITY_ARROWS,1);
                p_151197_.getPlayer().setItemInHand(interactionhand,item);
            }
            else{
                player.setItemInHand(interactionhand, Items.BUCKET.getDefaultInstance());
            }
        }

        return interactionresult;
    }
}
