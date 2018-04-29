package contracts.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class DefCalculationImpl implements Calculation, Serializable {
	
	private static final long serialVersionUID = -2790303972387671835L;
	
	@Id
	@GeneratedValue
	@Column
	private long id;
	@Column
	@Min(value=1)
	private int sum;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull()
	private Date beginDate;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull()
	private Date endDate;
	@ManyToOne
	@JoinColumn(name="PROPERTY_TYPE", referencedColumnName = "TYPE")
	@NotNull()
	private EstateType propertyType;
	@Column
	@Length(min=4, max=4)
	private String buildYear;
	@Column
	@NotNull()
	private String area;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date calculationDate;
	@Column
	private String price;
			
	public DefCalculationImpl() {
		super();
	}
	
	public DefCalculationImpl(Date beginDate, Date endDate, EstateType propertyType, String buildYear,
			String area) {
		super();
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.propertyType = propertyType;
		this.buildYear = buildYear;
		this.area = area;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public EstateType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(EstateType propertyType) {
		this.propertyType = propertyType;
	}

	public String getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(String buildYear) {
		this.buildYear = buildYear;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getCalculationDate() {
		return calculationDate;
	}

	public void setCalculationDate(Date calculationDate) {
		this.calculationDate = calculationDate;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Override
	public void calculate() throws Exception{
		int days = (int) Math.round((endDate.getTime() - beginDate.getTime()) / (double) 86400000);
		int price = (int) ((sum/days)*getEstateCoeff()*getBuildYearCoeff()*getAreaCoeff());
		this.price = String.valueOf(price);
		this.calculationDate = new Date();
		System.out.println("calculation: "+price);
	}

	@Override
	@JsonIgnore
	public float getEstateCoeff() {
		return propertyType.getValue();
	}

	@Override
	@JsonIgnore
	public float getBuildYearCoeff() throws NumberFormatException {
		int year = Integer.parseInt(buildYear);
		if(year < 2000) return 1.3f;
		if(year < 2014) return 1.6f;
		return 2;
	}

	@Override
	@JsonIgnore
	public float getAreaCoeff() throws NumberFormatException {
		int area = Integer.parseInt(this.area);
		if(area < 50) return 1.2f;
		if(area < 100) return 1.5f;
		return 2;
	}
	
	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "id");
	}
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "id");
	}
	
}
