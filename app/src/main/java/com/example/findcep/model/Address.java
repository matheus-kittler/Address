package com.example.findcep.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Address implements Parcelable {

	@SerializedName("cep")
	private String cep;
	@SerializedName("logradouro")
	private String logradouro;
	@SerializedName("complemento")
	private String complemento;
	@SerializedName("bairro")
	private String bairro;
	@SerializedName("localidade")
	private String localidade;
	@SerializedName("uf")
	private String uf;


	public Address(String cep, String lagradouro, String complemento, String bairro, String localidade, String uf) {
		this.cep = cep;
		this.logradouro = lagradouro;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
	}

	protected Address(Parcel in) {
		cep = in.readString();
		logradouro = in.readString();
		complemento = in.readString();
		bairro = in.readString();
		localidade = in.readString();
		uf = in.readString();
	}

	public static final Creator<Address> CREATOR = new Creator<Address>() {
		@Override
		public Address createFromParcel(Parcel in) {
			return new Address(in);
		}

		@Override
		public Address[] newArray(int size) {
			return new Address[size];
		}
	};

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(cep);
		parcel.writeString(logradouro);
		parcel.writeString(complemento);
		parcel.writeString(bairro);
		parcel.writeString(localidade);
		parcel.writeString(uf);
	}
}
