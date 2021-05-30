package com.ecommerce.app.service;

import com.ecommerce.app.domain.Address;
import com.ecommerce.app.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/ecommerce")
@CrossOrigin(origins = "http://localhost:4200")
public class AddressService {
	private final AddressRepository addressRepository;

	public Address createAddress(Address address) {
		addressRepository.save(address);
		return address;
	}

	public void removeAddress(Long id) {
		addressRepository.deleteById(id);
	}

	public Iterable<Address> getAddress() {
		return addressRepository.findAll();
	}

	public Address updateAddress(Long id, Address address) {
		if (addressRepository.findById(id).isPresent()) {
			Address fetchedAddress = addressRepository.findById(id).get();
			fetchedAddress.setFullName(address.getFullName());
			fetchedAddress.setAddressType(address.getAddressType());
			fetchedAddress.setFirstLine(address.getFirstLine());
			fetchedAddress.setSecondLine(address.getSecondLine());
			fetchedAddress.setZip(address.getZip());
			fetchedAddress.setCity(address.getCity());
			fetchedAddress.setState(address.getState());
			fetchedAddress.setPhone(address.getPhone());
			addressRepository.save(fetchedAddress);
			return fetchedAddress;
		}
		return null;
	}
}
