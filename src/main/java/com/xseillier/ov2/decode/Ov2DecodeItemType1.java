package com.xseillier.ov2.decode;

import java.nio.ByteBuffer;

import com.xseillier.ov2.item.Ov2Item;
import com.xseillier.ov2.item.Ov2ItemType1;

/**
 * 
 * @author xseillier
 *
 */

public class Ov2DecodeItemType1 extends AbstractOv2DecodeItem {

	public Ov2DecodeItemType1() {
		super( 1 );
	}

	@Override
	public Ov2Item decodeItem(byte[] rawItem) {
		
		Ov2ItemType1 response = new Ov2ItemType1();
		
		ByteBuffer oByteBuffer =  ByteBuffer.wrap( rawItem );
		readType( oByteBuffer );
		readLength( oByteBuffer );
		
		response.setX1( readCoordinate( oByteBuffer ) );
		response.setY1( readCoordinate( oByteBuffer ) );
		response.setX2( readCoordinate( oByteBuffer ) );
		response.setY2( readCoordinate( oByteBuffer ) );
		
		return response;
	}
}
