package contracts.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Person {
	
	@Id
	@GeneratedValue
	@Column
	private long id;
	@Column
	@NotNull()
	private String name;
	@Column
	@NotNull()
	private String lastName;
	@Column
	private String fathersName;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull()
	private Date birthDate;
	@Column
	@Min(value=1)
	@Max(value=9999)
	private int passSeries;
	@Column
	@Min(value=1)
	@Max(value=999999)
	private int passNum;
	
	public Person() {
		super();
	}
	
	public Person(String name, String lastName, String fathersName, Date birthDate, int passSeries,
			int passNum) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.fathersName = fathersName;
		this.birthDate = birthDate;
		this.passSeries = passSeries;
		this.passNum = passNum;
	}
	
	public Person(long id, String name, String lastName, String fathersName, Date birthDate, int passSeries,
			int passNum) {
		this(name, lastName, fathersName, birthDate, passSeries, passNum);
		this.id=id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fatherName) {
		this.fathersName = fatherName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getPassSeries() {
		return passSeries;
	}

	public void setPassSeries(int passSeries) {
		this.passSeries = passSeries;
	}

	public int getPassNum() {
		return passNum;
	}

	public void setPassNum(int passNum) {
		this.passNum = passNum;
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
