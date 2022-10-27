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
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    // Integration test to make sure status 200 isOk will be returned when getting /users endpoint with trainer role
    @Test
    public void get200StatusFoEndpointTrainer() throws Exception {
        mockMvc.perform(get("/users")
               .with(user("users")
               .roles("TRAINER")))
               .andExpect(status().isOk());
    }

    // Integration test to make sure JSON is return when doing a get request for all users for role trainer
    @Test
    public void getJsonReturned() throws Exception {
        mockMvc.perform(get("/users")
               .contentType(MediaType.APPLICATION_JSON)
               .with(user("trainer")
               .roles("TRAINER")))
               .andExpect(status()
               .isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

}