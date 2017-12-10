//package mahtiuutiset.selenium;
//
//import org.fluentlenium.adapter.junit.FluentTest;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class NewsTest extends FluentTest{
//    
//    @LocalServerPort
//    private Integer port;
//    
//    public NewsTest() { // ?? java.lang.NoClassDefFoundError: org/openqa/selenium/remote/JsonException
//    }
//
//     @Test
//     public void testFrontpageOk() {
//          goTo("http://localhost:" + port);
//          assertEquals("MahtiUutiset", window().title());
//          assertTrue(pageSource().contains("Etusivu"));
//     }
//}
