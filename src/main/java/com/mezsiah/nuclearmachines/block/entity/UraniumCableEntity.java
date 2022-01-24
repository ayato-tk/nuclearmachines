package com.mezsiah.nuclearmachines.block.entity;

import com.mezsiah.nuclearmachines.config.registerBlockEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class UraniumCableEntity extends BlockEntity {

    public UraniumCableEntity( BlockPos pos, BlockState state ) {
        super( registerBlockEntity.URANIUM_CABLE_ENTITY, pos, state);
    }
    
    
    
}
