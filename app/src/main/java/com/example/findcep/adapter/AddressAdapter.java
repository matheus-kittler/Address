package com.example.findcep.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.findcep.R;
import com.example.findcep.model.Address;

import java.util.ArrayList;
import java.util.List;

public class AddressAdapter extends RecyclerView.Adapter<AddressAdapter.AddressViewHolder> {

	private List<Address> addresses;
	private LayoutInflater inflater;
	private Address address;
	private OnAddressClickListner onAddressClickListner;

	public AddressAdapter(Context context) {
		this.addresses = new ArrayList<>();
		this.inflater = LayoutInflater.from(context);
	}

	public void editAddress(Address address, int index) {
		addresses.set(index, address);
		notifyDataSetChanged();
	}

	public void addAddress(Address address) {
		this.addresses.add(address);
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public AddressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		return new AddressViewHolder(inflater.inflate(R.layout.row_cep, viewGroup, false));
	}

	@Override
	public void onBindViewHolder(@NonNull AddressViewHolder viewHolder, int position) {
			Address address = addresses.get(position);
			AddressViewHolder addressViewHolder = viewHolder;
			addressViewHolder.tvCEP.setText(address.getCep());
			addressViewHolder.tvComplement.setText(address.getComplemento());
			addressViewHolder.tvPublicPlace.setText(address.getLogradouro());
			addressViewHolder.tvNeighborhood.setText(address.getBairro());
			addressViewHolder.tvLocal.setText(address.getLocalidade());
			addressViewHolder.tvUF.setText(address.getUf());

			viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (addressViewHolder != null) {
						onAddressClickListner.onAddressClick(address, position);
					}
				}
			});
	}

	@Override
	public int getItemCount() {
		if (addresses != null) {
			return addresses.size();
		}
			return 0;
	}

	public void setOnAddressClickListner (OnAddressClickListner onAddressClickListner) {
		this.onAddressClickListner = onAddressClickListner;
	}

	class AddressViewHolder extends RecyclerView.ViewHolder {

		private TextView tvCEP;
		private TextView tvPublicPlace;
		private TextView tvComplement;
		private TextView tvNeighborhood;
		private TextView tvLocal;
		private TextView tvUF;

		public AddressViewHolder(@NonNull View itemView) {
			super(itemView);
			tvCEP = itemView.findViewById(R.id.tvCEP);
			tvPublicPlace = itemView.findViewById(R.id.tvPublicPlace);
			tvComplement = itemView.findViewById(R.id.tvComplement);
			tvNeighborhood = itemView.findViewById(R.id.tvNeighborhood);
			tvLocal = itemView.findViewById(R.id.tvLocal);
			tvUF = itemView.findViewById(R.id.tvUF);

		}
	}

	public interface OnAddressClickListner {
		void onAddressClick (Address address, int index);
	}

}
