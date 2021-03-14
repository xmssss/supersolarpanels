
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import ic2.api.energy.EnergyNet;
import ic2.api.energy.IEnergyNet;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.*;
import ic2.api.energy.*;

public class IUCore {
    public static final CreativeTabSSP tabssp;
    @Mod.Instance("industrialupgrade")
    public static IUCore instance;
    @SidedProxy(clientSide = "com.Denfop.proxy.ClientProxy", serverSide = "com.Denfop.proxy.CommonProxy")
    public static CommonProxy proxy;
    public static CreativeTabSSP1 tabssp1;
    public static CreativeTabSSP2 tabssp2;
    public static CreativeTabSSP3 tabssp3;
    public static CreativeTabSSP4 tabssp4;
    public static Random random;
            proxy.throwInitException(new LoaderException("SuperSolarPanels is incompatible with Advanced Solar Panels. Please delete Advanced solar Panels"));
        proxy.integration();
        BlocksItems.init();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
        GenOre.init();
        Register.registertiles();
        BlocksItems.registermetadata();
        IUCore.proxy.registerRenderers();
        IUCore.proxy.load();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, (IGuiHandler) IUCore.proxy);

    public void load(final FMLInitializationEvent event) {
        if(Loader.isModLoaded("Waila"))
            FMLInterModComms.sendMessage("Waila", "register", "com.Denfop.integration.Waila.WailaHandler.callbackRegister");
    }

        tabssp1 = new CreativeTabSSP1();
        tabssp2 = new CreativeTabSSP2();
        tabssp3 = new CreativeTabSSP3();
        tabssp4 = new CreativeTabSSP4();
        IUCore.instance = new IUCore();