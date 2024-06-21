package service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import sample.ShoppingWebApplication;
import sample.Service.ShopService;

@SpringBootTest(classes = ShoppingWebApplication.class)
@AutoConfigureMockMvc
public class addNewItemTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShopService ShopServiceMock;

    @Test
    public void testAddNewItem() throws Exception {
        // Mockの動作を設定
        doNothing().when(ShopServiceMock).AddNewItem(anyString(), anyInt(), anyInt());

        // テスト用のリクエスト
        mockMvc.perform(MockMvcRequestBuilders.post("/AddNewItem")
                .param("product_name", "Test Product")
                .param("price", "100")
                .param("quantity", "5"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("endadd"));

        // addNewItemServiceのメソッドが正しく呼び出されたことを検証
        verify(ShopServiceMock, times(1)).AddNewItem("Test Product", 100, 5);
    }
}
