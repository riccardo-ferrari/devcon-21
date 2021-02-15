package com.liferay.headless.commerce.aws.personalize.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author riccardoferrari
 * @generated
 */
@Generated("")
@GraphQLName("Interactions")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Interactions")
public class Interactions {

	public static Interactions toDTO(String json) {
		return ObjectMapperUtil.readValue(Interactions.class, json);
	}

	@Schema(description = "Product SKU")
	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String ITEM_ID) {
		this.ITEM_ID = ITEM_ID;
	}

	@JsonIgnore
	public void setITEM_ID(
		UnsafeSupplier<String, Exception> ITEM_IDUnsafeSupplier) {

		try {
			ITEM_ID = ITEM_IDUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Product SKU")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String ITEM_ID;

	@Schema(description = "Event timestamp")
	public Long getTIMESTAMP() {
		return TIMESTAMP;
	}

	public void setTIMESTAMP(Long TIMESTAMP) {
		this.TIMESTAMP = TIMESTAMP;
	}

	@JsonIgnore
	public void setTIMESTAMP(
		UnsafeSupplier<Long, Exception> TIMESTAMPUnsafeSupplier) {

		try {
			TIMESTAMP = TIMESTAMPUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Event timestamp")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long TIMESTAMP;

	@Schema(description = "User ID (or Account ID)")
	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String USER_ID) {
		this.USER_ID = USER_ID;
	}

	@JsonIgnore
	public void setUSER_ID(
		UnsafeSupplier<String, Exception> USER_IDUnsafeSupplier) {

		try {
			USER_ID = USER_IDUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "User ID (or Account ID)")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String USER_ID;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Interactions)) {
			return false;
		}

		Interactions interactions = (Interactions)object;

		return Objects.equals(toString(), interactions.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (ITEM_ID != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ITEM_ID\": ");

			sb.append("\"");

			sb.append(_escape(ITEM_ID));

			sb.append("\"");
		}

		if (TIMESTAMP != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"TIMESTAMP\": ");

			sb.append(TIMESTAMP);
		}

		if (USER_ID != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"USER_ID\": ");

			sb.append("\"");

			sb.append(_escape(USER_ID));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		defaultValue = "com.liferay.headless.commerce.aws.personalize.dto.v1_0.Interactions",
		name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		return string.replaceAll("\"", "\\\\\"");
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\":");

			Object value = entry.getValue();

			Class<?> clazz = value.getClass();

			if (clazz.isArray()) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(value);
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}