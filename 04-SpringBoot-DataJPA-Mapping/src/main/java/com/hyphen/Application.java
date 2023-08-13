package com.hyphen;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.hyphen.entity.Address;
import com.hyphen.entity.Category;
import com.hyphen.entity.Employee;
import com.hyphen.entity.Mobile;
import com.hyphen.entity.Product;
import com.hyphen.repository.AddressRepository;
import com.hyphen.repository.CategoryRepository;
import com.hyphen.repository.EmpRepository;
import com.hyphen.repository.MobileRepository;
import com.hyphen.repository.ProductRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private EmpRepository empRepository;
	
	@Autowired
	private MobileRepository mobileRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// OneToOne
		/*
		 * Employee employee = new Employee(); employee.setName("One Employee");
		 * 
		 * Mobile mobile = new Mobile(); mobile.setName("One Plus C3");
		 * 
		 * mobile.setEmployee(employee);
		 * 
		 * // empRepository.save(employee); // mobileRepository.save(mobile);
		 * 
		 * // Get Mobile and Employee data from Employee table by EmployeeRepository
		 * Employee empGetId = empRepository.findById(1).get();
		 * System.out.println("Name: " + empGetId.getName());
		 * System.out.println("Mobile: " + empGetId.getMobile().getName());
		 * 
		 * // Get Mobile and Employee data from Employee table by Mobile Repository
		 * Mobile mobGetId = mobileRepository.findById(1).get();
		 * System.out.println("Name: " + mobGetId.getEmployee().getName());
		 * System.out.println("Mobile: " + mobGetId.getName());
		 */	
		
		// OneToMany - ManyToOne Employee employee = new Employee();
		/*
		 * employee.setName("Employee 2");
		 * 
		 * Address address = new Address(); address.setAddress("Delhi");
		 * address.setType("Permanent"); address.setEmployee(employee);
		 * 
		 * Address address2 = new Address(); address2.setAddress("Bihar");
		 * address2.setType("Current"); address2.setEmployee(employee);
		 * 
		 * List<Address> addList = Arrays.asList(address, address2);
		 * employee.setAddresses(addList);
		 * 
		 * // empRepository.save(employee);
		 * 
		 * // Get Details employee and address details from Employee repository 
		 * Employee getEmployee2 = empRepository.findById(2).get();
		 * System.out.println("ManyToOne Employee - " + getEmployee2.getName());
		 * getEmployee2.getAddresses().forEach(e -> System.out.println(e.getAddress()));
		 * 
		 * // Get Details employee and address details from Address repository 
		 * Address addById = addressRepository.findById(1).get(); System.out.println("Address "
		 * + addById.getAddress()); System.out.println("Name: " +
		 * addById.getEmployee().getName());
		 */
		
		// ManyToMany
		Category cat1 = new Category();
		cat1.setName("Electronics");
		
		Category cat2 = new Category();
		cat2.setName("Mobiles");
		
		Product p1 = new Product();
		p1.setName("TV");
		
		Product p2 = new Product();
		p2.setName("Iphpne 14");
		
		Product p3 = new Product();
		p3.setName("LG Mobiles");
		
		// Add products in category
		cat1.getProducts().add(p1);
		cat1.getProducts().add(p2);
		cat1.getProducts().add(p3);
		
		cat2.getProducts().add(p2);
		cat2.getProducts().add(p3);	
		
		// Bidirectional Add Category from Products
		p1.getCategories().add(cat1);
		p2.getCategories().add(cat1);
		p3.getCategories().add(cat1);
		
		p2.getCategories().add(cat2);
		p3.getCategories().add(cat2);
		
		// categoryRepository.save(cat1);
		// categoryRepository.save(cat2);
		
		// Get Products from Category Repository 
		Category getCategory = categoryRepository.findById(1).get();
		System.out.println(getCategory.getName());
		getCategory.getProducts().forEach(e -> System.out.println(e.getName()));
		
		// Get Category from Products Repository 
		Product getProduct = productRepository.findById(2).get();
		System.out.println(getProduct.getName());
		getProduct.getCategories().forEach(e -> System.out.println(e.getName()));
	}

}
