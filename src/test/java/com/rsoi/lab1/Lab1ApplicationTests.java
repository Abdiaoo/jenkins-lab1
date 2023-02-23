package com.rsoi.lab1;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class Lab1ApplicationTests {
	private static final String REQUEST_BODY = "{\"name\":\"Casey_Pfannerstill39\",\"age\":31,\"address\":\"78802 Alayna Trail\",\"work\":\"Wilderman, Blick and Russel\"}";
	private static final String RESPONSE_BODY_ALL = "[{\"id\":1,\"name\":\"Casey_Pfannerstill39\",\"age\":31,\"address\":\"78802 Alayna Trail\",\"work\":\"Wilderman, Blick and Russel\"}]";
	private static final String RESPONSE_BODY = "{\"id\":1,\"name\":\"Casey_Pfannerstill39\",\"age\":31,\"address\":\"78802 Alayna Trail\",\"work\":\"Wilderman, Blick and Russel\"}";
	private static final String REQUEST_PATCH_BODY="{\"name\":\"Dadinos\",\"address\":\"Hayabley\"}";
	@Autowired
	private MockMvc mockMvc;
	@Test
	@Order(1)
	public void testPostMethod() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/persons")
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_BODY));
		result.andExpect(status().isCreated());
	}

	@Test
	@Order(2)
	public void testGetMethod() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons/1"));
		result.andExpect(status().isOk());
		result.andExpect(content().string(RESPONSE_BODY));
	}

	@Test
	@Order(3)
	public void testGetAllMethod() throws Exception {
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/persons"))
				.andExpect(status().isOk())
				.andExpect(content().string(RESPONSE_BODY_ALL));
	}
	@Test
	@Order(4)
	public void testPatchMethod() throws Exception{
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/persons/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(REQUEST_PATCH_BODY))
				.andExpect(status().isOk());
	}
	@Test
	@Order(5)
	public void testDeleteMethod() throws Exception{
		ResultActions result = mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/persons/1"))
				.andExpect(status().isNoContent());

	}
}
