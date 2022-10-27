package com.example.easynotes;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.easynotes.controller.NoteController;
import com.example.easynotes.model.Note;
import com.example.easynotes.repository.NoteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(NoteController.class)
public class NoteControllerTests {
	@MockBean
	private NoteRepository tutorialRepository;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void shouldCreateTutorial() throws Exception {
		Note note = new Note(1l, "Bank", "test", new Date(), new Date());

		mockMvc.perform(post("/api/notes").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(note))).andExpect(status().is2xxSuccessful()).andDo(print());
	}

}