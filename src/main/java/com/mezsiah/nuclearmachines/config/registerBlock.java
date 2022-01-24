package com.mezsiah.nuclearmachines.config;

import com.mezsiah.nuclearmachines.NuclearMachines;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import com.mezsiah.nuclearmachines.block.Uranium_ore;
import com.mezsiah.nuclearmachines.block.custom.Uranium_cable;
import com.mezsiah.nuclearmachines.block.custom.Uranium_generator;


public class registerBlock {

    public static final Block URANIUM_ORE = new Uranium_ore( FabricBlockSettings
    .of(Material.STONE)
    .strength(4.0f)
    .requiresTool()
    );

    public static final Block URANIUM_CABLE = new Uranium_cable(FabricBlockSettings.of(Material.METAL).strength(3.0f).requiresTool());
    public static final Block URANIUM_GENERATOR = new Uranium_generator( FabricBlockSettings.of(Material.METAL).strength(3.0f).requiresTool());
    public static final Block MOLTEN_URANIUM_BLOCK = new FluidBlock( registerFluid.STILL_MOLTEN_URANIUM, FabricBlockSettings.copy(Blocks.WATER)){};

    private void register(Registry registry, String blockName, Block block){
        registry.register(registry.BLOCK, new Identifier(NuclearMachines.MODID, blockName), block);
        if(block instanceof FluidBlock){ return; }
        registry.register(registry.ITEM, getBlockId(blockName), new BlockItem(block, new FabricItemSettings().group(ItemGroup.MATERIALS)));
    }
    
    public registerBlock(Registry registry){
        this.register(registry, "uranium_ore", URANIUM_ORE);
        this.register(registry, "uranium_generator", URANIUM_GENERATOR);
        this.register(registry, "uranium_cable", URANIUM_CABLE);
        this.register(registry, "molten_uranium_liquid", MOLTEN_URANIUM_BLOCK);
    }


    public static Identifier getBlockId(String blockName){
        return new Identifier(NuclearMachines.MODID, blockName);
    }


}
