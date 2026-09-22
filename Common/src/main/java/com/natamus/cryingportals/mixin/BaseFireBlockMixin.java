package com.natamus.cryingportals.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.CryingObsidianBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Predicate;

@Mixin(value = BaseFireBlock.class, priority = 1001)
public class BaseFireBlockMixin {
	@Redirect(method = "isPortal", at = @At(value = "INVOKE", target = "Ljava/util/function/Predicate;test(Ljava/lang/Object;)Z"))
	private static boolean BaseFireBlock_isPortal(Predicate<BlockState> framePredicate, Object object) {
		BlockState blockState = (BlockState)object;
		if (blockState.getBlock() instanceof CryingObsidianBlock) {
			return true;
		}

		return framePredicate.test(blockState);
	}
}
