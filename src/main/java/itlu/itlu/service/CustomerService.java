package itlu.itlu.service;


import itlu.itlu.dto.CustomerDto;
import itlu.itlu.model.Customer;
import itlu.itlu.repository.CustomerRepository;
import itlu.itlu.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private  final CustomerRepository customerRepository;
    private final ProjectRepository projectRepository;

    public CustomerService(CustomerRepository customerRepository, ProjectRepository projectRepository) {
        this.customerRepository = customerRepository;
        this.projectRepository = projectRepository;
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public void saveCustomer(CustomerDto customerDto){

        Customer newCustomer = new Customer();
        Optional.ofNullable(customerDto.getId()).ifPresent(id-> newCustomer.setId(id));
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

    public boolean checkCustomerHasProject(Long id) { return projectRepository.findProjectByCustomerId(id).size() > 0; }

    public CustomerDto getCustomerDto(Long id) {
        Customer customer = customerRepository.findById(id).orElse(new Customer());
        return new CustomerDto(customer.getId(),
                customer.getCompany_name(),
                customer.getName(),
                customer.getSurname(),
                customer.getEmail(),
                customer.getPhone_number(),
                customer.getNip_number(),
                customer.getCity(),
                customer.getStreet(),
                customer.getHouse_number());
    }
}
