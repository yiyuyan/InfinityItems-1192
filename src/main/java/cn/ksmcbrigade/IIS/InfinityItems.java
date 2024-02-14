package cn.ksmcbrigade.IIS;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.commands.Commands;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mod("iis")
@Mod.EventBusSubscriber
public class InfinityItems {

    public static int exp = 7;

    public InfinityItems() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        File file = new File("config/iis-config.json");
        if(!file.exists()){
            JsonObject json = new JsonObject();
            json.addProperty("exp",exp);
            Files.write(file.toPath(),json.toString().getBytes());
        }
        exp = JsonParser.parseString(Files.readString(file.toPath())).getAsJsonObject().get("exp").getAsInt();
    }

    @SubscribeEvent
    public static void RegisterCommand(RegisterCommandsEvent event){
        event.getDispatcher().register(Commands.literal("inject").executes(context -> {
            Player entity = (Player)context.getSource().getEntity();
            if (entity != null) {
                if(!entity.getMainHandItem().isEmpty()){
                    if(entity.experienceLevel>=exp){
                        entity.getMainHandItem().enchant(Enchantments.INFINITY_ARROWS,1);
                        entity.giveExperienceLevels(-exp);
                    }
                    else{
                        entity.sendSystemMessage(Component.nullToEmpty(I18n.get("commands.iis.cannot_xp").replace("{x}",String.valueOf(exp))));
                    }
                }
                else if(!entity.getOffhandItem().isEmpty()){
                    if(entity.experienceLevel>=exp){
                        entity.getOffhandItem().enchant(Enchantments.INFINITY_ARROWS,1);
                        entity.giveExperienceLevels(-exp);
                    }
                    else{
                        entity.sendSystemMessage(Component.nullToEmpty(I18n.get("commands.iis.cannot_xp").replace("{x}",String.valueOf(exp))));
                    }
                }
                else{
                    entity.sendSystemMessage(Component.translatable("commands.iis.empty"));
                }
            }
            return 0;
        }));
    }

    public static boolean HAS(ListTag tags){
        for(Tag tag:tags){
            //System.out.println(JsonParser.parseString(tag.getAsString().trim()).getAsJsonObject().get("id").getAsString().trim());
            if(JsonParser.parseString(tag.getAsString().trim()).getAsJsonObject().get("id").getAsString().trim().equals("minecraft:infinity")){
                return true;
            }
        }
        return false;
    }
}
