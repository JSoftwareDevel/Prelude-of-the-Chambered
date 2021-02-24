package com.unk.PoC.level.block;

import com.unk.PoC.entities.*;
import com.unk.PoC.level.Level;

public class LockedDoorBlock extends DoorBlock {
	public LockedDoorBlock() {
		tex = 5;
	}

	public boolean use(Level level, Item item) {
		return false;
	}

	public void trigger(boolean pressed) {
		open = pressed;
	}

}
