package com.Denfop.proxy;

import java.util.HashMap;
import java.util.Map;

import com.Denfop.Config;
import com.Denfop.IUCore;
import com.Denfop.Recipes.BasicRecipe;
import com.Denfop.Recipes.CannerRecipe;
import com.Denfop.Recipes.CentrifugeRecipe;
import com.Denfop.Recipes.CompressorRecipe;
import com.Denfop.Recipes.FurnaceRecipes;
import com.Denfop.Recipes.MaceratorRecipe;
import com.Denfop.integration.Avaritia.AvaritiaIntegration;
import com.Denfop.integration.Botania.BotaniaIntegration;
import com.Denfop.integration.DE.DraconicIntegration;
import com.Denfop.integration.crafttweaker.CTCore;
import com.Denfop.render.Cable.RenderBlock;
import com.Denfop.tiles.Mechanism.*;
import com.Denfop.tiles.NeutroniumGenerator.TileBitGen2;
import com.Denfop.tiles.base.TileEntityChargepadBlock;
import com.Denfop.tiles.base.TileEntityElectricBlock;
import com.Denfop.tiles.base.TileEntityMolecularTransformer;
import com.Denfop.tiles.base.TileEntityMultiMachine;
import com.Denfop.tiles.base.TileEntityMultiMachine1;
import com.Denfop.tiles.base.TileEntityMultiMatter;
import com.Denfop.tiles.base.TileEntitySolarPanel;
import com.Denfop.tiles.base.TileSintezator;
import com.Denfop.tiles.wiring.Storage.TileEntityElectricMFE;
import com.Denfop.tiles.wiring.Storage.TileEntityElectricMFSU;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderException;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import modtweaker2.utils.TweakerPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class CommonProxy implements IGuiHandler{
  public boolean isClient() {
    return false;
  }

  public boolean isSimulating() {
    return !FMLCommonHandler.instance().getEffectiveSide().isClient();
  }
  public int getRenderId(String name) {
    return -1;
  }
  public RenderBlock getRender(String name) {
    return null;
  }
  public boolean isRendering() {
    return !isSimulating();
  }
  public void registerTabs(CreativeTabs tab, ItemStack icon) {}

  public static void sendPlayerMessage(EntityPlayer player, String message) {
    if (IUCore.isSimulating())
      player.addChatMessage((IChatComponent)new ChatComponentTranslation(message, new Object[0])); 
  }

  public void registerEvents() {

  }

  public void registerPackets(SimpleNetworkWrapper netInstance) {}

  public void initCore() {
    
    TileEntityAlloySmelter.init();
    TileEntityMolecularTransformer.init();
    TileEntityGenerationMicrochip.init();
    TileEntityGenerationStone.init();
    
  }

  public static Void throwInitException(LoaderException e) {
    throw e;
  }

  public void registerRecipe() {
    
    if(Config.BotaniaLoaded && Config.Botania)
      BotaniaIntegration.recipe();
    BasicRecipe.recipe();
    if(Config.DraconicLoaded && Config.Draconic)
     DraconicIntegration.Recipes();
   if(Config.AvaritiaLoaded && Config.Avaritia )
    AvaritiaIntegration.recipe();


  CompressorRecipe.recipe();
  CannerRecipe.recipe();
  FurnaceRecipes.recipe();  
  CentrifugeRecipe.init();
  MaceratorRecipe.recipe();

}

public void integration() { 
  Config.DraconicLoaded = Loader.isModLoaded("DraconicEvolution");
  Config.AvaritiaLoaded = Loader.isModLoaded("Avaritia");
  Config.BotaniaLoaded = Loader.isModLoaded("Botania");
  Config.EnchantingPlus = Loader.isModLoaded("eplus");
  Config.MineFactory = Loader.isModLoaded("MineFactoryReloaded");
  if(Loader.isModLoaded("modtweaker2")) {
    TweakerPlugin.register("industrialupgrade", CTCore.class);
    
  }
  if(Config.DraconicLoaded && Config.Draconic) {
    DraconicIntegration.init();
  }
  if(Config.AvaritiaLoaded && Config.Avaritia ) {
    AvaritiaIntegration.init();
  }

  if(Config.BotaniaLoaded && Config.Botania) {
    BotaniaIntegration.init();
  }
}

public void check() {}



if (te instanceof TileEntityAlloySmelter) {
  return ((TileEntityAlloySmelter)te).getGuiContainer(player);
}
if (te instanceof TileEntityElectricMFE)
{
  return ((TileEntityElectricMFE)te).getGuiContainer(player);
}
if (te instanceof TileEntityElectricMFSU)
{
  return ((TileEntityElectricMFSU)te).getGuiContainer(player);
}
if (te instanceof TileEntityElectricBlock)
{
  return ((TileEntityElectricBlock )te).getGuiContainer(player);
}
if (te instanceof TileBitGen2)
{
  return ((TileBitGen2)te).getGuiContainer(player);
}
if (te instanceof TileEntityGenerationMicrochip)
{
  return ((TileEntityGenerationMicrochip)te).getGuiContainer(player);
}
if (te instanceof TileEntityMultiMachine) {
  return ((TileEntityMultiMachine) te).getGuiContainer(player);
}
if (te instanceof TileEntityMultiMachine1) {
  return ((TileEntityMultiMachine1) te).getGuiContainer(player);
}
if (te instanceof TileEntityChargepadBlock) {
  return ((TileEntityChargepadBlock) te).getGuiContainer(player);
}
if (te instanceof TileEntityGenerationStone)
  return ((TileEntityGenerationStone) te).getGuiContainer(player);
if (te instanceof TileEntityQuantumQuarry)
  return ((TileEntityQuantumQuarry) te).getGuiContainer(player);

return null;
}

public Object getClientGuiElement(final int ID, final EntityPlayer player, final World world, final int X, final int Y, final int Z) {
  return null;
}


public int addArmor(final String armorName) {
  return 0;
}

public static void sendPlayerMessage(EntityPlayer player, String message) {
  if (IUCore.isSimulating())
    player.addChatMessage((IChatComponent)new ChatComponentTranslation(message, new Object[0]));
}




public void initCore() {
  TileEntityAlloySmelter.init();
  TileEntityMolecularTransformer.init();
  TileEntityGenerationMicrochip.init();
  TileEntityGenerationStone.init();

}

public static Void throwInitException(LoaderException e) {
  throw e;
}

public void registerRecipe() {

  if (Config.BotaniaLoaded && Config.Botania)
    BotaniaIntegration.recipe();
  BasicRecipe.recipe();
  if (Config.DraconicLoaded && Config.Draconic)
    DraconicIntegration.Recipes();
  if (Config.AvaritiaLoaded && Config.Avaritia)
    AvaritiaIntegration.recipe();

  AlloySmelterRecipe.recipe();
  CompressorRecipe.recipe();
  CannerRecipe.recipe();
  FurnaceRecipes.recipe();
  CentrifugeRecipe.init();
  MaceratorRecipe.recipe();
}

public void integration() {
  Config.DraconicLoaded = Loader.isModLoaded("DraconicEvolution");
  Config.AvaritiaLoaded = Loader.isModLoaded("Avaritia");
  Config.BotaniaLoaded = Loader.isModLoaded("Botania");
  Config.EnchantingPlus = Loader.isModLoaded("eplus");
  Config.MineFactory = Loader.isModLoaded("MineFactoryReloaded");
  if (Loader.isModLoaded("modtweaker2")) {
    TweakerPlugin.register("industrialupgrade", CTCore.class);
  }
  if (Config.DraconicLoaded && Config.Draconic) {
    DraconicIntegration.init();
  }
  if (Config.AvaritiaLoaded && Config.Avaritia) {
    AvaritiaIntegration.init();
  }
  if(Config.BotaniaLoaded && Config.Botania) {
    BotaniaIntegration.init();
  }
}

public void check() {}
}
