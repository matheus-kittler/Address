package com.example.findcep.view.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.findcep.R;
import com.example.findcep.adapter.AddressAdapter;
import com.example.findcep.model.Address;
import com.example.findcep.view.AddressEditActivity;
import com.example.findcep.view.address.AddressActivity;
import com.example.findcep.view.address.AddressActivityViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	protected RecyclerView rvCEP;
	protected Button btnOk;
	protected AddressAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		rvCEP = findViewById(R.id.rvCEP);
		btnOk = findViewById(R.id.btnOk);
		btnOk.setOnClickListener((View.OnClickListener) this);
		adapter = new AddressAdapter(this);
		rvCEP.setAdapter(adapter);
		rvCEP.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

		adapter.setOnAddressClickListner(new AddressAdapter.OnAddressClickListner() {
			@Override
			public void onAddressClick(Address address, int index) {
				Intent it = new Intent(MainActivity.this, AddressEditActivity.class);
				rvCEP.setAdapter(adapter);//TODO manda o objeto que o adapter tem
				it.putExtra("address", address);
				it.putExtra("index", index);
				startActivityForResult(it, 20);
			}
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent address) {
		super.onActivityResult(requestCode, resultCode, address);

		if (requestCode == 10) {
			if (address != null) {
				Address a = address.getParcelableExtra("address");
				adapter.addAddress(a);
			}
		}

		if (requestCode == 20) {
			if (address != null) {
				Address a = address.getParcelableExtra("address");
				adapter.editAddress(a, address.getIntExtra("index", 0));
			}
		}
	}


	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.btnOk:
				completLoginProcess();
			break;
		}
	}

	public void completLoginProcess() {
		Intent i = new Intent(this, AddressActivity.class);
		startActivityForResult(i, 10);
	}
}
