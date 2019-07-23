package com.example.findcep.service;

import com.example.findcep.model.Address;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAddressAPI {

	@GET("{cep}/json/")
	Call<Address> getAddress(
			@Path("cep") String cep
	);

}
