package com.liferay.headless.commerce.aws.personalize.client.serdes.v1_0;

import com.liferay.headless.commerce.aws.personalize.client.dto.v1_0.Items;
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
public class ItemsSerDes {

	public static Items toDTO(String json) {
		ItemsJSONParser itemsJSONParser = new ItemsJSONParser();

		return itemsJSONParser.parseToDTO(json);
	}

	public static Items[] toDTOs(String json) {
		ItemsJSONParser itemsJSONParser = new ItemsJSONParser();

		return itemsJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Items items) {
		if (items == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (items.getCATEGORY() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"CATEGORY\": ");

			sb.append("\"");

			sb.append(_escape(items.getCATEGORY()));

			sb.append("\"");
		}

		if (items.getCREATION_TIMESTAMP() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"CREATION_TIMESTAMP\": ");

			sb.append(items.getCREATION_TIMESTAMP());
		}

		if (items.getITEM_ID() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"ITEM_ID\": ");

			sb.append("\"");

			sb.append(_escape(items.getITEM_ID()));

			sb.append("\"");
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ItemsJSONParser itemsJSONParser = new ItemsJSONParser();

		return itemsJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Items items) {
		if (items == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (items.getCATEGORY() == null) {
			map.put("CATEGORY", null);
		}
		else {
			map.put("CATEGORY", String.valueOf(items.getCATEGORY()));
		}

		if (items.getCREATION_TIMESTAMP() == null) {
			map.put("CREATION_TIMESTAMP", null);
		}
		else {
			map.put(
				"CREATION_TIMESTAMP",
				String.valueOf(items.getCREATION_TIMESTAMP()));
		}

		if (items.getITEM_ID() == null) {
			map.put("ITEM_ID", null);
		}
		else {
			map.put("ITEM_ID", String.valueOf(items.getITEM_ID()));
		}

		return map;
	}

	public static class ItemsJSONParser extends BaseJSONParser<Items> {

		@Override
		protected Items createDTO() {
			return new Items();
		}

		@Override
		protected Items[] createDTOArray(int size) {
			return new Items[size];
		}

		@Override
		protected void setField(
			Items items, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "CATEGORY")) {
				if (jsonParserFieldValue != null) {
					items.setCATEGORY((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "CREATION_TIMESTAMP")) {

				if (jsonParserFieldValue != null) {
					items.setCREATION_TIMESTAMP(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "ITEM_ID")) {
				if (jsonParserFieldValue != null) {
					items.setITEM_ID((String)jsonParserFieldValue);
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