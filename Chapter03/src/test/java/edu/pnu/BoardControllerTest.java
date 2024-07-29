package edu.pnu;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import edu.pnu.domain.BoardVO;

@SpringBootTest
public class BoardControllerTest {
	@Autowired
//	private MockMvc mockMvc;
	private TestRestTemplate restTemplate;
	
//	@Test
//	public void testHello() throws Exception {
//		mockMvc.perform(get("/hello").param("name", "둘리"))
//			.andExpect(status().isOk())
//			.andExpect(content().string("Hello : 둘리"))
//			.andDo(print());
//	}
//	@Test
//	public void testHello() throws Exception {		
//	String result =
//			restTemplate.getForObject("/hello?name=둘리", String.class);
//	assertEquals("Hello : 둘리", result);
//	}
	
	@Test
	public void testGetBoard() throws Exception {
		BoardVO b = restTemplate.getForObject("/getBoard?writer=테스터", BoardVO.class);
		assertEquals("테스터", b.getWriter());
		System.out.println(b);
	}

	
}
