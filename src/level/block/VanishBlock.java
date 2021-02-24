package com.unk.PoC.level.block;

import com.unk.PoC.Sound;
import com.unk.PoC.entities.Item;
import com.unk.PoC.gui.RubbleSprite;
import com.unk.PoC.level.Level;

public class VanishBlock extends SolidBlock {
	private boolean gone = false;

	public VanishBlock() {
		tex = 1;
	}

	public boolean use(Level level, Item item) {
		if (gone) return false;

		gone = true;
		blocksMotion = false;
		solidRender = false;
		Sound.crumble.play();

		for (int i = 0; i < 32; i++) {
			RubbleSprite sprite = new RubbleSprite();
			sprite.col = col;
			addSprite(sprite);
		}

		return true;
	}
}
