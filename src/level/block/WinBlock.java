package com.unk.PoC.level.block;

import com.unk.PoC.entities.*;

public class WinBlock extends Block {
	public void addEntity(Entity entity) {
		super.addEntity(entity);
		if (entity instanceof Player) {
			((Player)entity).win();
		}
	}
}
