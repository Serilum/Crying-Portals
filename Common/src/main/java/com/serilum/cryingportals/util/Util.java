package com.serilum.cryingportals.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CryingObsidianBlock;

public class Util {
	public static boolean isNextToCryingObsidian(Level level, BlockPos blockPos) {
		for (Direction direction : Direction.values()) {
			if (level.getBlockState(blockPos.relative(direction)).getBlock() instanceof CryingObsidianBlock) {
				return true;
			}
		}

		return false;
	}

	public static Direction.Axis getPreferredPortalAxis(Level level, Direction direction) {
		if (direction.getAxis().isHorizontal()) {
			return direction.getCounterClockWise().getAxis();
		}

		return Direction.Plane.HORIZONTAL.getRandomAxis(level.getRandom());
	}
}
