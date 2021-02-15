package com.liferay.headless.commerce.aws.personalize.client.dto.v1_0;

import com.liferay.headless.commerce.aws.personalize.client.function.UnsafeSupplier;
import com.liferay.headless.commerce.aws.personalize.client.serdes.v1_0.ItemsSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author riccardoferrari
 * @generated
 */
@Generated("")
public class Items implements Cloneable {

	public static Items toDTO(String json) {
		return ItemsSerDes.toDTO(json);
	}

	public String getCATEGORY() {
		return CATEGORY;
	}

	public void setCATEGORY(String CATEGORY) {
		this.CATEGORY = CATEGORY;
	}

	public void setCATEGORY(
		UnsafeSupplier<String, Exception> CATEGORYUnsafeSupplier) {

		try {
			CATEGORY = CATEGORYUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String CATEGORY;

	public Long getCREATION_TIMESTAMP() {
		return CREATION_TIMESTAMP;
	}

	public void setCREATION_TIMESTAMP(Long CREATION_TIMESTAMP) {
		this.CREATION_TIMESTAMP = CREATION_TIMESTAMP;
	}

	public void setCREATION_TIMESTAMP(
		UnsafeSupplier<Long, Exception> CREATION_TIMESTAMPUnsafeSupplier) {

		try {
			CREATION_TIMESTAMP = CREATION_TIMESTAMPUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long CREATION_TIMESTAMP;

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String ITEM_ID) {
		this.ITEM_ID = ITEM_ID;
	}

	public void setITEM_ID(
		UnsafeSupplier<String, Exception> ITEM_IDUnsafeSupplier) {

		try {
			ITEM_ID = ITEM_IDUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String ITEM_ID;

	@Override
	public Items clone() throws CloneNotSupportedException {
		return (Items)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Items)) {
			return false;
		}

		Items items = (Items)object;

		return Objects.equals(toString(), items.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ItemsSerDes.toJSON(this);
	}

}