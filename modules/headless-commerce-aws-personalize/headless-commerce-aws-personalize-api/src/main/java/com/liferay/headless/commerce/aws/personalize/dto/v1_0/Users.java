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
@GraphQLName("Users")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Users")
public class Users {

	public static Users toDTO(String json) {
		return ObjectMapperUtil.readValue(Users.class, json);
	}

	@Schema(description = "Account Country")
	public String getCOUNTRY() {
		return COUNTRY;
	}

	public void setCOUNTRY(String COUNTRY) {
		this.COUNTRY = COUNTRY;
	}

	@JsonIgnore
	public void setCOUNTRY(
		UnsafeSupplier<String, Exception> COUNTRYUnsafeSupplier) {

		try {
			COUNTRY = COUNTRYUnsafeSupplier.get();
		}
		catch (RuntimeException re) {
			throw re;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@GraphQLField(description = "Account Country")
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String COUNTRY;

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

		if (!(object instanceof Users)) {
			return false;
		}

		Users users = (Users)object;

		return Objects.equals(toString(), users.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		if (COUNTRY != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"COUNTRY\": ");

			sb.append("\"");

			sb.append(_escape(COUNTRY));

			sb.append("\"");
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
		defaultValue = "com.liferay.headless.commerce.aws.personalize.dto.v1_0.Users",
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