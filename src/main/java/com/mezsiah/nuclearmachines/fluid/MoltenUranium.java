package com.mezsiah.nuclearmachines.fluid;

import com.mezsiah.nuclearmachines.config.registerBlock;
import com.mezsiah.nuclearmachines.config.registerFluid;
import com.mezsiah.nuclearmachines.config.registerItem;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.util.math.Direction;

public abstract class MoltenUranium extends FlowableFluid {


    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
		Block.dropStacks(state, world, pos, blockEntity);
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return 4;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    protected boolean isInfinite() {
        return false;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid,
            Direction direction) {
        return false;
    }

    @Override
    protected float getBlastResistance() {
        return 100.0F;
    }

    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public Fluid getStill() {
        return registerFluid.STILL_MOLTEN_URANIUM;
    }
    @Override
    public Fluid getFlowing() {
        return registerFluid.FLOWING_MOLTEN_URANIUM;
    }

    @Override
    public Item getBucketItem() {
        return registerItem.MOLTEN_URANIUM_BUCKET;
    }

    @Override
    protected BlockState toBlockState(FluidState state) {
        return registerBlock.MOLTEN_URANIUM_BLOCK.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(state));
    }


}


