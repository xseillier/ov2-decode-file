package com.xseillier.ov2.decode;

import com.xseillier.ov2.item.Ov2Item;
import com.xseillier.ov2.item.Ov2ItemType0;

/**
 * 
 * @author xseillier
 *
 */

public class Ov2DecodeItemType0 extends AbstractOv2DecodeItem {

	public Ov2DecodeItemType0() {
		super( 0 );
	}

	@Override
	public Ov2Item decodeItem(byte[] rawItem) {
		return new Ov2ItemType0();
	}
}
