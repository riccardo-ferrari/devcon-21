package com.liferay.headless.commerce.aws.personalize.client.dto.v1_0;

import com.liferay.headless.commerce.aws.personalize.client.function.UnsafeSupplier;
import com.liferay.headless.commerce.aws.personalize.client.serdes.v1_0.UsersSerDes;

import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author riccardoferrari
 * @generated
 */
@Generated("")
public class Users implements Cloneable {

	public static Users toDTO(String json) {
		return UsersSerDes.toDTO(json);
	}

	public String getCOUNTRY() {
		return COUNTRY;
	}

	public void setCOUNTRY(String COUNTRY) {
		this.COUNTRY = COUNTRY;
	}

	public void setCOUNTRY(
		UnsafeSupplier<String, Exception> COUNTRYUnsafeSupplier) {

		try {
			COUNTRY = COUNTRYUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String COUNTRY;

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
	public Users clone() throws CloneNotSupportedException {
		return (Users)super.clone();
	}

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
		return UsersSerDes.toJSON(this);
	}

}