package com.example.findcep.view.address;

import com.example.findcep.model.Address;
import com.example.findcep.service.IAddressAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddressActivityViewModel {

	protected AddressActivityInteraction interaction;
	private  Address address;
	private AddressActivity activity;

	public AddressActivityViewModel(AddressActivityInteraction interaction) {
		this.interaction = interaction;
	}

	public void loadAddress(String cep) {
		Retrofit retrofit = new Retrofit.Builder()
				.baseUrl("https://viacep.com.br/ws/")
				.addConverterFactory(GsonConverterFactory.create())
				.client(new OkHttpClient.Builder()
						.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
						.build()
				).build();

		retrofit.create(IAddressAPI.class).getAddress(cep).enqueue(new Callback<Address>() {
			@Override
			public void onResponse(Call<Address> call, Response<Address> response) {
				if (response != null && response.body() != null) {
					interaction.onLoadCep(response.body());
				} else {

				}
			}

			@Override
			public void onFailure(Call<Address> call, Throwable t) {
				interaction.onError(t.getMessage());
			}
		});
	}

}
