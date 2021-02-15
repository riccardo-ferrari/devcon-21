package com.liferay.headless.commerce.aws.personalize.client.dto.v1_0;

import com.liferay.headless.commerce.aws.personalize.client.function.UnsafeSupplier;
import com.liferay.headless.commerce.aws.personalize.client.serdes.v1_0.InteractionsSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author riccardoferrari
 * @generated
 */
@Generated("")
public class Interactions implements Cloneable {

	public static Interactions toDTO(String json) {
		return InteractionsSerDes.toDTO(json);
	}

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

	public Long getTIMESTAMP() {
		return TIMESTAMP;
	}

	public void setTIMESTAMP(Long TIMESTAMP) {
		this.TIMESTAMP = TIMESTAMP;
	}

	public void setTIMESTAMP(
		UnsafeSupplier<Long, Exception> TIMESTAMPUnsafeSupplier) {

		try {
			TIMESTAMP = TIMESTAMPUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long TIMESTAMP;

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String USER_ID) {
		this.USER_ID = USER_ID;
	}

	public void setUSER_ID(
		UnsafeSupplier<String, Exception> USER_IDUnsafeSupplier) {

		try {
			USER_ID = USER_IDUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String USER_ID;

	@Override
	public Interactions clone() throws CloneNotSupportedException {
		return (Interactions)super.clone();
	}

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
		return InteractionsSerDes.toJSON(this);
	}

}