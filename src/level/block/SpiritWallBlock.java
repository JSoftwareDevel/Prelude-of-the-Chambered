package com.unk.PoC.level.block;

import com.unk.PoC.Art;
import com.unk.PoC.VideoStream.FastRandom;
import com.unk.PoC.entities.*;
import com.unk.PoC.gui.Sprite;

public class SpiritWallBlock extends Block {
	public SpiritWallBlock() {
		floorTex = 7;
		ceilTex = 7;
		blocksMotion = true;
		for (int i = 0; i < 6; i++) {
			double x = (FastRandom.nextDouble() - 0.5);
			double y = (FastRandom.nextDouble() - 0.7) * 0.3;
			double z = (FastRandom.nextDouble() - 0.5);
			addSprite(new Sprite(x, y, z, 4 * 8 + 6 + FastRandom.nextInt(2), Art.getCol(0x202020)));
		}
	}

	public boolean blocks(Entity entity) {
		if (entity instanceof Bullet) return false;
		return super.blocks(entity);
	}
}
