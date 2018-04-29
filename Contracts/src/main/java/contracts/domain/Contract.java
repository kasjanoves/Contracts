package contracts.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table
public class Contract {

	@Column
	@Id
	@Min(value=1)
	@Max(value=999999)
	private long number;
	@Column
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull()
	private Date formationDate;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="PERSON_ID", referencedColumnName = "ID")
	@NotNull()
	private Person policyHolder;
	@Column
	private String comment;
	@OneToOne(targetEntity = DefCalculationImpl.class, cascade = CascadeType.ALL)
	@NotNull()
	private Calculation calculation;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Adress adress;
	
	public Contract() {
		super();
	}
	
	public Contract(long number, Date formationDate, Person policyHolder, String comment, Calculation calculation,
			Adress adress) {
		super();
		this.number = number;
		this.formationDate = formationDate;
		this.policyHolder = policyHolder;
		this.comment = comment;
		this.adress = adress;
		setCalculation(calculation);
	}



	public long getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getFormationDate() {
		return formationDate;
	}

	public void setFormationDate(Date formationDate) {
		this.formationDate = formationDate;
	}

	public Person getPolicyHolder() {
		return policyHolder;
	}

	public void setPolicyHolder(Person policyHolder) {
		this.policyHolder = policyHolder;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Calculation getCalculation() {
		return calculation;
	}

	public void setCalculation(Calculation calculation) {
		this.calculation = calculation;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
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
