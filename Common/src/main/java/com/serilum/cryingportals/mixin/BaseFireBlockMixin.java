package com.serilum.cryingportals.mixin;

import com.serilum.cryingportals.util.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BaseFireBlock.class, priority = 1001)
public class BaseFireBlockMixin {
	@Shadow
	private static boolean inPortalDimension(Level level) {
		throw new AssertionError();
	}

	@Inject(method = "isPortal", at = @At(value = "RETURN"), cancellable = true)
	private static void BaseFireBlock_isPortal(Level level, BlockPos blockPos, Direction direction, CallbackInfoReturnable<Boolean> cir) {
		if (cir.getReturnValue()) {
			return;
		}

		if (!inPortalDimension(level)) {
			return;
		}

		if (!Util.isNextToCryingObsidian(level, blockPos)) {
			return;
		}

		Direction.Axis preferredAxis = Util.getPreferredPortalAxis(level, direction);
		cir.setReturnValue(PortalShape.findEmptyPortalShape(level, blockPos, preferredAxis).isPresent());
	}
}
