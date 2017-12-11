package mahtiuutiset.controller;

import mahtiuutiset.repository.NewsObjectRepository;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsControllerTest {

    @Autowired
    private WebApplicationContext webAppContext;

    private MockMvc mockMvc;
    
    @Autowired
    private NewsObjectRepository newsRepo;

    public NewsControllerTest() {
    }

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testStatusOk() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void testResponseType() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().contentType("text/html;charset=UTF-8"));
    }

    @Test
    public void testResponseContainsTextMahtiUutiset() throws Exception {
        MvcResult res = mockMvc.perform(get("/"))
                .andReturn();

        String content = res.getResponse().getContentAsString();
        Assert.assertTrue(content.contains("MahtiUutiset"));
    }

    @Test
    public void testDatabaseIsNotEmptyAfterPostingAnArticle() throws Exception {
        mockMvc.perform(post("/add")
                .param("title", "otsikko")
                .param("lead", "ingressi")
                .param("text", "teksti")
                .param("author", "kirjoittaja")
                .param("category", "kateoria"))
                .andReturn();

        assertTrue(newsRepo.count()!= 0);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
