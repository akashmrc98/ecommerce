package com.ecommerce.app.bootstrap;

import com.ecommerce.app.domain.Address;
import com.ecommerce.app.domain.Vendor;
import com.ecommerce.app.repository.AddressRepository;
import com.ecommerce.app.repository.ImageRepository;
import com.ecommerce.app.repository.ProductRepository;
import com.ecommerce.app.repository.*;
import com.ecommerce.app.repository.UserRepository;
import com.ecommerce.app.repository.VendorRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@AllArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;
    private final VendorRepository vendorRepository;

    
    @Override
    public void run(String... args) throws Exception {


    	if(vendorRepository.count() < 0){
            Address address = new Address("21-10/1", "TC2", 520011, "VJA", "AP");
            Set<Address> addresses = new HashSet<>();
            addresses.add(address);

            Vendor vendor = new Vendor();
            vendor.setVendor("akash");
            vendor.setEmail("akashmadduru@gmail.com");
            vendor.setUsername("akash98");
            vendor.setPhone("9494535327");
            vendor.setPassword(passwordEncoder.encode("akash"));
            vendor.setAddress(addresses);

            addressRepository.save(address);
            vendorRepository.save(vendor);
        }


    }
}
