package com.xseillier.ov2.item;

/**
 * 
 * @author xseillier
 *
 */

public interface Ov2Item {

	public int getType();
	public String accept( Ov2ItemVisitor visitor );
}
