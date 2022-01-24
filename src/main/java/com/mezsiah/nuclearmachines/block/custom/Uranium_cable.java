package com.mezsiah.nuclearmachines.block.custom;

import com.mezsiah.nuclearmachines.block.entity.UraniumCableEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;

public class Uranium_cable extends BlockWithEntity {

    public Uranium_cable(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new UraniumCableEntity(pos, state);
    }
    
}
