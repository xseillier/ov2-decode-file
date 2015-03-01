package com.xseillier.convertor.csv;

import com.xseillier.ov2.item.Ov2ItemType0;
import com.xseillier.ov2.item.Ov2ItemType1;
import com.xseillier.ov2.item.Ov2ItemType2;
import com.xseillier.ov2.item.Ov2ItemType3;
import com.xseillier.ov2.item.Ov2ItemVisitor;

public class Ov2ToCsvVisitor implements Ov2ItemVisitor {

	@Override
	public String visit(Ov2ItemType0 ov2Item) {
		// DO nothing type0 is delete element
		return "";
	}

	@Override
	public String visit(Ov2ItemType1 ov2Item) {
		// DO nothing type0 is use by tomtom 
		return "";
	}

	@Override
	public String visit(Ov2ItemType2 ov2Item) {
		StringBuffer response = new StringBuffer();		
		
		response.append( ov2Item.getLatitude() );
		response.append( Ov2ToCsvFile.SEPARATOR );
		response.append( ov2Item.getLongitude() );
		response.append( Ov2ToCsvFile.SEPARATOR );
		response.append( ov2Item.getDescription().replaceAll(Ov2ToCsvFile.SEPARATOR, " ") ) ;
		
		return response.toString();
	}

	@Override
	public String visit(Ov2ItemType3 ov2Item) {
		StringBuffer response = new StringBuffer();		
		
		response.append( ov2Item.getLatitude() );
		response.append( Ov2ToCsvFile.SEPARATOR );
		response.append( ov2Item.getLongitude() );
		response.append( Ov2ToCsvFile.SEPARATOR );
		response.append( ov2Item.getDescription().replaceAll(Ov2ToCsvFile.SEPARATOR, " ") ) ;
		response.append( Ov2ToCsvFile.SEPARATOR );
		response.append( ov2Item.getId() ) ;
		
		return response.toString();
	}

}
