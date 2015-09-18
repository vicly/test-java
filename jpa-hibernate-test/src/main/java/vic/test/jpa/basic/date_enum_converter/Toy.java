package vic.test.jpa.basic.date_enum_converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import vic.test.jpa.BaseEntity;

import com.google.common.base.MoreObjects;

@Entity
public class Toy extends BaseEntity {

	// TemporalType.DATE: yyyy-MM-dd
	// TemporalType.TIME: hh:mm:ss
	// TemporalType.TIMESTAMP: nanoseconds, while util.Date only support milliseconds
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date boughtAt;

	private ToyMaterialType materialType;
	
	// EnumType.ORDINAL as integer, by default
	// EnumType.STRING as string
	@Enumerated(EnumType.STRING)
	private ToyType type;

	@Convert(converter = ToyHealthyTypeConverter.class)
	private ToyHealthyType healthyType;

	public Date getBoughtAt() {
		return boughtAt;
	}

	public void setBoughtAt(Date boughtAt) {
		this.boughtAt = boughtAt;
	}
	
	public ToyType getType() {
		return type;
	}

	public void setType(ToyType type) {
		this.type = type;
	}

	public ToyMaterialType getMaterialType() {
		return materialType;
	}

	public void setMaterialType(ToyMaterialType materialType) {
		this.materialType = materialType;
	}

	public ToyHealthyType getHealthyType() {
		return healthyType;
	}

	public void setHealthyType(ToyHealthyType healthyType) {
		this.healthyType = healthyType;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return MoreObjects.toStringHelper(this)
				.add("id", getId())
				.add("boughtAt", this.boughtAt == null ? null : sdf.format(this.boughtAt))
				.add("type", this.type)
				.add("materialType", this.materialType)
				.add("healthyType", this.healthyType)
				.toString();
	}
}
