package com.xseillier.ov2.decode;

import com.xseillier.ov2.item.Ov2Item;

/**
 * 
 * @author xseillier
 *
 */

public interface Ov2DecodeItem {

	public Ov2Item decodeItem( byte[] rawItem);	
	public Ov2DecodeItem getNext();
	public void setNext( Ov2DecodeItem nextItem);
	public Ov2Item decode(  byte[] rawItem );
}
