package nl.belastingdienst.voetbal_vereniging.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrainingControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // Integration test to get all trainings by specific team id for role user
    @Test
    public void getTrainingsByTeamId() throws Exception {
        mockMvc
                .perform(get("/api/trainings/1")
                .with(user("user")
                .roles("USER")))
                .andExpect(status().isOk());
    }

    // Integration test to make sure JSON is return when doing a get request for all trainings for role user
    @Test
    public void shouldReturnJson() throws Exception {
        mockMvc
                .perform(get("/api/trainings")
                .contentType(MediaType.APPLICATION_JSON)
                .with(user("user")
                .roles("USER")))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}
