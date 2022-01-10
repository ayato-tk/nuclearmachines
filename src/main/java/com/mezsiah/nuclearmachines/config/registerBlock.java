package com.mezsiah.nuclearmachines.config;

import com.mezsiah.nuclearmachines.NuclearMachines;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import com.mezsiah.nuclearmachines.block.Uranium_ore;


public class registerBlock {

    private final Block URANIUM_ORE = new Uranium_ore( FabricBlockSettings
    .of(Material.STONE)
    .strength(4.0f)
    .requiresTool()
    );

    private void register(Registry registry, String blockName, Block block){
        registry.register(registry.BLOCK, new Identifier(NuclearMachines.MODID, blockName), block);
        registry.register(registry.ITEM, new Identifier(NuclearMachines.MODID, blockName), new BlockItem(block, new FabricItemSettings().group(ItemGroup.MATERIALS)));
    }
    
    public registerBlock(Registry registry){
        this.register(registry, "uranium_ore", this.URANIUM_ORE);
    }


}
