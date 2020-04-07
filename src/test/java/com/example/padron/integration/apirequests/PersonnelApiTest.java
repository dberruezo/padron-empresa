package com.example.padron.integration.apirequests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonnelApiTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testTest () throws Exception {
		mockMvc.perform(get("/api/personnel/12345678"))
			.andExpect(status().isNotFound())
		;
	}
}
