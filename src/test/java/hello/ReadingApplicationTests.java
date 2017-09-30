package hello;

import hello.controllers.CartController;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReadingApplicationTests {

    private MockRestServiceServer server;
    private MockMvc mockMvc;
    private CartController controller;
    public static final String CONTENT_TYPE = "Content-Type";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplate rest;

    @Before
    public void setup() {
        this.server = MockRestServiceServer.createServer(rest);
        controller = new CartController();
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @After
    public void teardown() {
        this.server = null;
    }

    @Test
    public void successTest(){

    }

    /*@Test
    public void getInterestListTest() throws Exception{
        DeferredResult<InterestRatesListModel> deferredResult = testRestTemplate.getForObject("/api/interest-rates", DeferredResult.class);
        MvcResult mvcResult = this.mockMvc.perform(get("/api/interest-rates"))
                .andExpect(request().asyncStarted())
                .andExpect(request().asyncResult("Deferred result"))
                .andExpect(status().isOk()).andReturn();
        this.mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk());
        System.out.println(deferredResult.getResult());
    }*/

    /*@Test
    public void toReadTest() {
        this.server.expect(requestTo("http://localhost:8080/recommended"))
                .andExpect(method(HttpMethod.GET)).
                andRespond(withSuccess("books", MediaType.TEXT_PLAIN));
        String books = testRestTemplate.getForObject("/to-read", String.class);
        assertThat(books).isEqualTo("books");
    }*/

    /*@Test
    public void toReadFailureTest() {
        this.server.expect(requestTo("http://localhost:8090/recommended")).
                andExpect(method(HttpMethod.GET)).andRespond(withServerError());
        String books = testRestTemplate.getForObject("/to-read", String.class);
        assertThat(books).isEqualTo("Cloud Native Java (O'Reilly)");
    }*/
}
