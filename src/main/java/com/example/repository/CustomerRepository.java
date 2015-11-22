package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	@Query("SELECT x FROM Customer x ORDER BY x.firstName, x.lastName")
	List<Customer> findAllOrderByName();
	/**
	@Autowired
	NamedParameterJdbcTemplate jdbcdTemplate;

	private static final RowMapper<Customer> customerRowMapper = (rs, i) -> {
		Integer id = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");
		return new Customer(id, firstName, lastName);
	};

	public List<Customer> findAll() {
		List<Customer> customers = jdbcdTemplate.query(
				"SELECT id, first_name, last_name FROM customers ORDER BY id",
				customerRowMapper);
		return customers;
	}

	public Customer findOne(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id",
				id);
		return jdbcdTemplate.queryForObject(
				"select * from customers where id =:id", param,
				customerRowMapper);
	}

	public Customer save(Customer customer) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		if (customer.getId() == null) {
			jdbcdTemplate
					.update("insert into customers (first_name, last_name) values (:first_name, :last_name)",
							param);

		} else {
			jdbcdTemplate
					.update("insert into customers (id, first_name, last_name) values (:id, :first_name, :last_name)",
							param);
		}
		return customer;
	}
	
	
	public void delete(Integer id){
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		jdbcdTemplate.update("delete from customers where id = :id", param);
	}
	*/
}
