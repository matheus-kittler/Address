package com.example.findcep.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.findcep.R;
import com.example.findcep.adapter.AddressAdapter;
import com.example.findcep.model.Address;
import com.example.findcep.view.address.AddressActivityInteraction;
import com.example.findcep.view.main.MainActivity;

public class AddressEditActivity extends AppCompatActivity implements View.OnClickListener {

	protected EditText etCep;
	protected EditText etPublicPlace;
	protected EditText etComplement;
	protected EditText etNeighborhood;
	protected EditText etLocal;
	protected EditText etUF;
	protected Button btnBack;
	protected Button btnSend;
	private Address address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address_edit);

		etCep = findViewById(R.id.etCep);
		etPublicPlace = findViewById(R.id.etPublicPlace);
		etComplement = findViewById(R.id.etComplement);
		etNeighborhood = findViewById(R.id.etNeighborhood);
		etLocal = findViewById(R.id.etLocal);
		etUF = findViewById(R.id.etUF);
		btnBack = findViewById(R.id.btnBack);
		btnBack.setOnClickListener(this);
		btnSend = findViewById(R.id.btnSend);
		btnSend.setOnClickListener(this);

		address = getIntent().getParcelableExtra("address");

		if (address != null) {
			etCep.setText(address.getCep());
			etPublicPlace.setText(address.getLogradouro());
			etComplement.setText(address.getComplemento());
			etNeighborhood.setText(address.getBairro());
			etLocal.setText(address.getLocalidade());
			etUF.setText(address.getUf());
		}
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btnBack:
				finish();
			break;
			case R.id.btnSend:
				Intent it = new Intent(AddressEditActivity.this, MainActivity.class);
				it.putExtra("address", address);
				it.putExtra("index", getIntent().getIntExtra("index", 0));
				setResult(RESULT_OK, it);
				finish();
		}
	}
}
