package com.xseillier.ov2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.xseillier.ov2.decode.Ov2DecodeChain;
import com.xseillier.ov2.decode.Ov2DecodeItem;
import com.xseillier.ov2.decode.exception.Ov2DecodeException;
import com.xseillier.ov2.item.Ov2Item;

/**
 * 
 * @author xseillier
 *
 */
public class Ov2DecodeFile {
	
	public static Stream<Ov2Item> Ov2Items( String path) throws FileNotFoundException
	{
		
		Ov2DecodeFileIterator iterator = new Ov2DecodeFileIterator( path );
		
		Iterable<Ov2Item> iterable = new Iterable<Ov2Item>(){
			@Override
			public Iterator<Ov2Item> iterator() {
				return iterator;
			}};
		
		return StreamSupport.stream( iterable.spliterator(), false);
	}
	
	
	public static Stream<Ov2Item> Ov2Items( URL url) throws FileNotFoundException
	{
		return Ov2Items( url.getFile() );
	}
	
	
	private static class Ov2DecodeFileIterator implements Iterator<Ov2Item>
	{
		
		private long currentIndex = 0;
		private long fileLength;
		private byte[]  lengthByteArray = new byte[ 4 ];
		private BufferedInputStream inputSteam = null;
		
		private Ov2DecodeItem decodeChain;
		
		
		public Ov2DecodeFileIterator(String path ) throws FileNotFoundException
		{
			File file = new File( path );
			if( file.exists() )
			{
				fileLength = file.length();
				inputSteam = new BufferedInputStream( new FileInputStream( file ) );
				decodeChain = Ov2DecodeChain.getInstance().getDecodeChain();
			}
			else
			{
				throw new FileNotFoundException( path  + " not exists" );
			}
			
		}

		@Override
		public boolean hasNext() {
			
			return currentIndex < fileLength;
		}

		@Override
		public Ov2Item next() {
						
			int entryLength = -1;
			inputSteam.mark( 5 );
			int type;
			try {
				type = inputSteam.read();
					
				//hack for ov2 type 1 SKIPPER RECORD
				if( type == 1 )
				{
					entryLength = 21;
				}
				else
				{
					inputSteam.read( lengthByteArray );
					entryLength = readInt( lengthByteArray );
				}
				
				inputSteam.reset();
				byte[] entryData = new byte[ entryLength ];
				inputSteam.read( entryData );
				currentIndex += entryLength;
				return decodeChain.decode(  entryData );
			} catch (IOException e) {
				throw new Ov2DecodeException("Error to decode Ov2 Item");
			}
			finally
			{
				if( !hasNext() )
				{
					close();
				}
			}
		}
				
				
		/**
		 * array byte to int  (little  endian)
		 * @param array
		 * @return
		 */
		
		private int readInt( byte[] array )
		{
			int response = array[ 0 ];
			
			response+= array[ 1 ] << 8;
			response+= array[ 2 ] << 16;
			response+= array[ 3 ] << 24;
					
			return response;
		}
		
		/**
		 * close inputStrem
		 */
		private void close()
		{
			if( inputSteam != null )
			{
				try {
					inputSteam.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
}
