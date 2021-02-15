package com.liferay.headless.commerce.aws.personalize.client.serdes.v1_0;

import com.liferay.headless.commerce.aws.personalize.client.dto.v1_0.Interactions;
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
public class InteractionsSerDes {

	public static Interactions toDTO(String json) {
		InteractionsJSONParser interactionsJSONParser =
			new InteractionsJSONParser();

		return interactionsJSONParser.parseToDTO(json);
	}

	public static Interactions[] toDTOs(String json) {
		InteractionsJSONParser interactionsJSONParser =
			new InteractionsJSONParser();

		return interactionsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Interactions interactions) {
		if (interactions == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (interactions.getITEM_ID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ITEM_ID\": ");

			sb.append("\"");

			sb.append(_escape(interactions.getITEM_ID()));

			sb.append("\"");
		}

		if (interactions.getTIMESTAMP() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"TIMESTAMP\": ");

			sb.append(interactions.getTIMESTAMP());
		}

		if (interactions.getUSER_ID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"USER_ID\": ");

			sb.append("\"");

			sb.append(_escape(interactions.getUSER_ID()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		InteractionsJSONParser interactionsJSONParser =
			new InteractionsJSONParser();

		return interactionsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Interactions interactions) {
		if (interactions == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (interactions.getITEM_ID() == null) {
			map.put("ITEM_ID", null);
		}
		else {
			map.put("ITEM_ID", String.valueOf(interactions.getITEM_ID()));
		}

		if (interactions.getTIMESTAMP() == null) {
			map.put("TIMESTAMP", null);
		}
		else {
			map.put("TIMESTAMP", String.valueOf(interactions.getTIMESTAMP()));
		}

		if (interactions.getUSER_ID() == null) {
			map.put("USER_ID", null);
		}
		else {
			map.put("USER_ID", String.valueOf(interactions.getUSER_ID()));
		}

		return map;
	}

	public static class InteractionsJSONParser
		extends BaseJSONParser<Interactions> {

		@Override
		protected Interactions createDTO() {
			return new Interactions();
		}

		@Override
		protected Interactions[] createDTOArray(int size) {
			return new Interactions[size];
		}

		@Override
		protected void setField(
			Interactions interactions, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "ITEM_ID")) {
				if (jsonParserFieldValue != null) {
					interactions.setITEM_ID((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "TIMESTAMP")) {
				if (jsonParserFieldValue != null) {
					interactions.setTIMESTAMP(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "USER_ID")) {
				if (jsonParserFieldValue != null) {
					interactions.setUSER_ID((String)jsonParserFieldValue);
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