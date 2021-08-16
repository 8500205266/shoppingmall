package com.shoppingmall;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShoppingmallApplication
{

	/**
	 *
	 * @return
	 */
	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ShoppingmallApplication.class, args);
	}
}
