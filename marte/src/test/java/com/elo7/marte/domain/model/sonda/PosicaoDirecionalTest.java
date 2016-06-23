package com.elo7.marte.domain.model.sonda;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class PosicaoDirecionalTest {
	private Integer num0;
	private Integer num1;
	private Integer num2;

	public PosicaoDirecionalTest(Integer num0, Integer num1, Integer num2) {
		super();
		this.num0 = num0;
		this.num1 = num1;
		this.num2 = num2;
	}

	@Parameters(name="{index}: testAdd({0}+{1}) = {2}")
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { 
			{ 1, 1, 2 }, 
			{ 2, 2, 4 }, 
			{ 8, 2, 10 }, 
			{ 4, 5, 9 }, 
			{ 5, 5, 10 } 
		});
	}
	
	@Test
	public void deveSomarNumeros(){
		assertThat(num0+num1, equalTo(num2));
	}
}
