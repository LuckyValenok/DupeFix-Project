package com.focamacho.dupefixproject.mixin.arcanearchives;

import com.aranaira.arcanearchives.inventory.ContainerRadiantChest;
import com.aranaira.arcanearchives.tileentities.RadiantChestTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.ParametersAreNonnullByDefault;

@Mixin(value = ContainerRadiantChest.class, remap = false)
public abstract class ContainerRadiantChestMixin extends Container {

	@Shadow protected RadiantChestTileEntity tile;

	@Override
	@ParametersAreNonnullByDefault
	public boolean canInteractWith(EntityPlayer player) {
		BlockPos pos = this.tile.getPos();
		if (this.tile.getWorld().getTileEntity(pos) != this.tile) return false;
		else return player.getDistanceSq(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D) <= 64.0D;
	}

}