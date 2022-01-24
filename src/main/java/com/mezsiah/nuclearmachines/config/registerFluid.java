package com.mezsiah.nuclearmachines.config;

import com.mezsiah.nuclearmachines.NuclearMachines;

import com.mezsiah.nuclearmachines.fluid.states.MoltenUranium.FlowingMoltenUranium;
import com.mezsiah.nuclearmachines.fluid.states.MoltenUranium.StillMoltenUranium;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class registerFluid {
    
    public static FlowableFluid STILL_MOLTEN_URANIUM = new StillMoltenUranium();
    public static FlowableFluid FLOWING_MOLTEN_URANIUM = new FlowingMoltenUranium();

    private void register(Registry registry, String itemName, Fluid fluid){
        registry.register(Registry.FLUID, new Identifier(NuclearMachines.MODID, itemName), fluid);
    }

    public registerFluid(Registry registry){
        this.register(registry, "molten_uranium",  STILL_MOLTEN_URANIUM);
        this.register(registry, "flowing_molten_uranium", FLOWING_MOLTEN_URANIUM);
        FluidRenderHandlerRegistry.INSTANCE.register(STILL_MOLTEN_URANIUM, FLOWING_MOLTEN_URANIUM, new SimpleFluidRenderHandler(
            new Identifier("minecraft:block/water_still"),
            new Identifier("minecraft:block/water_flow"),
            0x1FCF57
        ));
        BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(), STILL_MOLTEN_URANIUM, FLOWING_MOLTEN_URANIUM);
    }
    
}
