package com.liferay.headless.commerce.aws.personalize.client.serdes.v1_0;

import com.liferay.headless.commerce.aws.personalize.client.dto.v1_0.Users;
import com.liferay.headless.commerce.aws.personalize.client.json.BaseJSONParser;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

/**
 * @author riccardoferrari
 * @generated
 */
@Generated("")
public class UsersSerDes {

	public static Users toDTO(String json) {
		UsersJSONParser usersJSONParser = new UsersJSONParser();

		return usersJSONParser.parseToDTO(json);
	}

	public static Users[] toDTOs(String json) {
		UsersJSONParser usersJSONParser = new UsersJSONParser();

		return usersJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Users users) {
		if (users == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (users.getCOUNTRY() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"COUNTRY\": ");

			sb.append("\"");

			sb.append(_escape(users.getCOUNTRY()));

			sb.append("\"");
		}

		if (users.getUSER_ID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"USER_ID\": ");

			sb.append("\"");

			sb.append(_escape(users.getUSER_ID()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		UsersJSONParser usersJSONParser = new UsersJSONParser();

		return usersJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Users users) {
		if (users == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (users.getCOUNTRY() == null) {
			map.put("COUNTRY", null);
		}
		else {
			map.put("COUNTRY", String.valueOf(users.getCOUNTRY()));
		}

		if (users.getUSER_ID() == null) {
			map.put("USER_ID", null);
		}
		else {
			map.put("USER_ID", String.valueOf(users.getUSER_ID()));
		}

		return map;
	}

	public static class UsersJSONParser extends BaseJSONParser<Users> {

		@Override
		protected Users createDTO() {
			return new Users();
		}

		@Override
		protected Users[] createDTOArray(int size) {
			return new Users[size];
		}

		@Override
		protected void setField(
			Users users, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "COUNTRY")) {
				if (jsonParserFieldValue != null) {
					users.setCOUNTRY((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "USER_ID")) {
				if (jsonParserFieldValue != null) {
					users.setUSER_ID((String)jsonParserFieldValue);
				}
			}
			else {
				throw new IllegalArgumentException(
					"Unsupported field name " + jsonParserFieldName);
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
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

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(",");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}