package com.xseillier.ov2.item;

/**
 * 
 * @author xseillier
 * 
 * SIMPLE POI RECORD:
 * 1 byte T: type (always 2)
 * 4 bytes L: length of this record in bytes (including the T and L fields)
 * 4 bytes X: longitude coordinate of the POI
 * 4 bytes Y: latitude coordinate of the POI
 * L−13 bytes Name: zero−terminated ASCII string specifying the name
 * of the POI
 */
public class Ov2ItemType2 extends AbstractOv2Item {

	
	private float  longitude;
	private float  latitude;
	private String description;
	
	public Ov2ItemType2()
	{
		super( 2 );
	}
	
	protected Ov2ItemType2( int type )
	{
		super( type );
	}
	
	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float aLongitude) {
		longitude = aLongitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float aLatitude) {
		latitude = aLatitude;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String aDescription) {
		description = aDescription;
	}

	
	@Override
	public String accept(Ov2ItemVisitor visitor) {
		return visitor.visit( this );
	}
	
	@Override
	public String toString() {
		return "Ov2ItemType2 [longitude=" + longitude + ", latitude="
				+ latitude + ", description=" + description + ", type=" + type
				+ "]";
	}

	
}
