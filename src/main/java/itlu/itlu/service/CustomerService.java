package itlu.itlu.service;


import itlu.itlu.dto.CustomerDto;
import itlu.itlu.model.Customer;
import itlu.itlu.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private  final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void saveCustomer(CustomerDto customerDto){

        Customer newCustomer = new Customer();
        newCustomer.setCompany_name(customerDto.getCompany_name());
        newCustomer.setName(customerDto.getName());
        newCustomer.setSurname(customerDto.getSurname());
        newCustomer.setEmail(customerDto.getEmail());
        newCustomer.setPhone_number(customerDto.getPhone_number());
        newCustomer.setNip_number(customerDto.getNip_number());
        newCustomer.setCity(customerDto.getCity());
        newCustomer.setStreet(customerDto.getStreet());
        newCustomer.setHouse_number(customerDto.getHouse_number());

        customerRepository.save(newCustomer);
    }

    public void deleteCustomer(Long id){customerRepository.deleteById(id);}

}
