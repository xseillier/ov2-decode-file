package com.xseillier;

import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

import com.xseillier.convertor.csv.Ov2ToCsvFile;
import com.xseillier.convertor.csv.Ov2ToCsvType;
import com.xseillier.convertor.csv.Ov2ToCsvVisitor;
import com.xseillier.ov2.Ov2DecodeFile;

public class Main {

	public static void main(String[] args) {
	
		
		URL url = Main.class.getClass().getResource( "/radarsFr.ov2" );
		
		Ov2ToCsvVisitor csvVisitor = new Ov2ToCsvVisitor();
		
		try
		{
			final Ov2ToCsvFile csvFile = new Ov2ToCsvFile("output.csv", Ov2ToCsvType.TOW );	
		    
		    Consumer<String> write = ( String csvString ) ->  {  
		    	try
		    	{
					csvFile.writeLine( csvString );
				} 
		    	catch (Exception e) {
		    		e.printStackTrace();
				}  
		    };
		
	
			Ov2DecodeFile.Ov2Items( url ).filter( oV2Item -> oV2Item.getType() == 2 )
			                             .map( oV2Item -> oV2Item.accept( csvVisitor ) )
			                             .forEach( string ->  write.accept( string )  );
		
			csvFile.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
