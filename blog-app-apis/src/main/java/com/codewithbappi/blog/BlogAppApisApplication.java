package com.codewithbappi.blog;

import com.codewithbappi.blog.config.AppConstants;
import com.codewithbappi.blog.entities.Roles;
import com.codewithbappi.blog.repositories.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.management.relation.Role;
import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner
{

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);

	}

	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(this.passwordEncoder.encode("xyz"));

		try {
			Roles role = new Roles();
			role.setName("ADMIN_USER");
			role.setId(AppConstants.ADMIN_USER);

			Roles role1 = new Roles();
			role1.setName("ADMIN_USER");
			role1.setId(AppConstants.NORMAL_USER);

			List<Roles> roles = List.of(role,role1);

			List<Roles> result = this.roleRepo.saveAll(roles);

			result.forEach(r->{
				System.out.println(r.getName() + " ");
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
