package com.example.findcep.view.address;

import com.example.findcep.model.Address;

public interface AddressActivityInteraction {
	void onLoadCep(Address address);
	void onError(String msg);
}
