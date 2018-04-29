package contracts.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@IdClass(Adress.AdressPK.class)
public class Adress {

	@Column
	@Id
	@NotNull()
	private String state;
	@Column
	private String postIndex;
	@Column
	@Id
	@NotNull()
	private String region;
	@Column
	private String district;
	@Column
	@Id
	@NotNull()
	private String locality;
	@Column
	@Id
	@NotNull()
	private String street;
	@Column
	private int house;
	@Column
	private String housing;
	@Column
	private String building;
	@Column
	@Id
	@Min(value=1)
	private int apartment;
		
	public Adress() {
		super();
	}
		
	public Adress(String state, String postIndex, String region, String district, String locality, String street,
			int house, String housing, String building, int apartment) {
		super();
		this.state = state;
		this.postIndex = postIndex;
		this.region = region;
		this.district = district;
		this.locality = locality;
		this.street = street;
		this.house = house;
		this.housing = housing;
		this.building = building;
		this.apartment = apartment;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostIndex() {
		return postIndex;
	}

	public void setPostIndex(String postIndex) {
		this.postIndex = postIndex;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getHouse() {
		return house;
	}

	public void setHouse(int house) {
		this.house = house;
	}

	public String getHousing() {
		return housing;
	}

	public void setHousing(String housing) {
		this.housing = housing;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public int getApartment() {
		return apartment;
	}

	public void setApartment(int apartment) {
		this.apartment = apartment;
	}
	
	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	public static class AdressPK implements Serializable {
			
		private static final long serialVersionUID = -2031267120274158116L;
		
		protected String state;
		protected String region;
		protected String locality;
		protected String street;
		protected int apartment;
		
		public AdressPK() {}

		public AdressPK(String state, String region, String locality, String street, int apartment) {
			super();
			this.state = state;
			this.region = region;
			this.locality = locality;
			this.street = street;
			this.apartment=apartment;
		}
			
		@Override
		public boolean equals(Object that) {
			return EqualsBuilder.reflectionEquals(this, that);
		}
		@Override
		public int hashCode() {
			return HashCodeBuilder.reflectionHashCode(this);
		}
		
	}
		
}
