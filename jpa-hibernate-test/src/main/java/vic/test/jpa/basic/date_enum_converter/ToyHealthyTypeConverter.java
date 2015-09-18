package vic.test.jpa.basic.date_enum_converter;

import javax.persistence.AttributeConverter;

/**
 * Demonstrate customized data converter
 */
public class ToyHealthyTypeConverter implements AttributeConverter<ToyHealthyType, String> {

	@Override
	public String convertToDatabaseColumn(ToyHealthyType attribute) {
		switch (attribute) {
			case GOOD :
				return "g";
			case NORMAL :
				return "n";
			default :
				throw new IllegalArgumentException("Unknown healthy type: " + attribute);
		}
	}

	@Override
	public ToyHealthyType convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			throw new IllegalStateException("Healthy type data must not be null");
		}
		
		if ("g".equals(dbData)) {
			return ToyHealthyType.GOOD;
		} else if ("n".equals(dbData)) {
			return ToyHealthyType.NORMAL;
		} else {
			throw new IllegalStateException("Unknown healthy type data: " + dbData);
		}
	}

}

