package com.serilum.cryingportals.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.CryingObsidianBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PortalShape.class, priority = 1001)
public class PortalShapeMixin {
	@Shadow private static @Final @Mutable BlockBehaviour.StatePredicate FRAME;

	@Inject(method = "<clinit>", at = @At(value = "TAIL"))
	private static void PortalShape(CallbackInfo ci) {
		BlockBehaviour.StatePredicate originalFrame = FRAME;
		FRAME = (BlockState blockState, BlockGetter blockGetter, BlockPos blockPos) -> {
			return originalFrame.test(blockState, blockGetter, blockPos) || blockState.getBlock() instanceof CryingObsidianBlock;
		};
	}
}
