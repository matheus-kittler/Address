package com.example.findcep.view.address;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.findcep.R;
import com.example.findcep.model.Address;
import com.example.findcep.view.AddressEditActivity;
import com.example.findcep.view.main.MainActivity;


public class AddressActivity extends AppCompatActivity implements View.OnClickListener, AddressActivityInteraction {

	protected EditText etCep;
	protected EditText etPublicPlace;
	protected EditText etComplement;
	protected EditText etNeighborhood;
	protected EditText etLocal;
	protected EditText etUF;
	protected Button btnBack;
	protected Button btnSend;
	protected Button btnFind;
	private RelativeLayout rlLoader;
	private ConstraintLayout clForms;
	private ConstraintLayout clPublicPlace;
	private ConstraintLayout clComplement;
	private ConstraintLayout clNeighborhood;
	private ConstraintLayout clLocal;
	private ConstraintLayout clUF;

	private AddressActivityViewModel viewModel;
	Address address;
	AddressActivityInteraction interaction = (AddressActivityInteraction) address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_address);
		setTitle("");

		viewModel = new AddressActivityViewModel(this);


		etCep = findViewById(R.id.etCep);
		etPublicPlace = findViewById(R.id.etPublicPlace);
		etComplement = findViewById(R.id.etComplement);
		etNeighborhood = findViewById(R.id.etNeighborhood);
		etLocal = findViewById(R.id.etLocal);
		etUF = findViewById(R.id.etUF);
		rlLoader = findViewById(R.id.rlLoader);
		clForms = findViewById(R.id.clForms);
		clComplement = findViewById(R.id.clComplement);
		clPublicPlace = findViewById(R.id.clPublicPlace);
		clNeighborhood = findViewById(R.id.clNeighborhood);
		clLocal = findViewById(R.id.clLocal);
		clUF = findViewById(R.id.clUF);
		btnBack = findViewById(R.id.btnBack);
		btnBack.setOnClickListener((View.OnClickListener) this);
		btnFind = findViewById(R.id.btnFind);
		btnFind.setOnClickListener(this);
		btnSend = findViewById(R.id.btnSend);
		btnSend.setOnClickListener(this);
	}

	private void openAlertDialog(String messange){
		AlertDialog.Builder builder = new AlertDialog.Builder(AddressActivity.this)
				.setTitle("ERROR")
				.setMessage(messange)
				.setNeutralButton("Ok", null);
		builder.create().show();
	}



	@Override
	public void onClick(View view) {

		switch (view.getId()) {
			case R.id.btnFind:
				loadPage();
			break;
			case R.id.btnBack: {
				finish();
			}
			break;
			case R.id.btnSend:
				Intent intent = new Intent(AddressActivity.this, MainActivity.class);
				address.setCep(etCep.getText().toString());
				address.setLogradouro(etPublicPlace.getText().toString());
				address.setComplemento(etComplement.getText().toString());//TODO faz parecido com um "reload" onde vai aparecer o digitado no adapter e não o que vem da API
				address.setBairro(etNeighborhood.getText().toString());
				address.setLocalidade(etLocal.getText().toString());
				address.setUf(etUF.getText().toString());
				intent.putExtra("address", address);
				setResult(RESULT_OK, intent);
				finish();
		}
	}

	@Override
	public void onLoadCep(Address a) {
		rlLoader.setVisibility(View.GONE);
		clForms.setVisibility(View.VISIBLE);
		address = a;
		if (address != null) {
			etPublicPlace.setText(address.getLogradouro());
			etComplement.setText(address.getComplemento());
			etNeighborhood.setText(address.getBairro());
			etLocal.setText(address.getLocalidade());
			etUF.setText(address.getUf());
		}
	}

	@Override
	public void onError(String msg) {
		openAlertDialog(msg);
	}

	public void loadPage() {
		rlLoader.setVisibility(View.VISIBLE);
		clForms.setVisibility(View.GONE);
		viewModel.loadAddress(etCep.getText().toString());
	}
}
