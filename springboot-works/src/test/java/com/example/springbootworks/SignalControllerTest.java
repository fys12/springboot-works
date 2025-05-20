package com.example.springbootworks;

import com.example.springbootworks.controller.SignalController;
import com.example.springbootworks.domain.TbSignal;
import com.example.springbootworks.dto.SignalDTO;
import com.example.springbootworks.service.TbSignalService;
import com.example.springbootworks.vo.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SignalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private TbSignalService signalService;

    @InjectMocks
    private SignalController signalController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(signalController).build();
    }

    @Test
    public void testUploadSignal() throws Exception {
        // Arrange
        SignalDTO dto = new SignalDTO();
        dto.setCarId(1);

        // Act & Assert
        mockMvc.perform(post("/api/signal/upload")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"carId\":1}")) // 根据实际结构调整
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200));

        verify(signalService, times(1)).saveOrUpdateSignal(dto);
    }

    @Test
    public void testGetSignalByCarId() throws Exception {
        // Arrange
        TbSignal signal = new TbSignal();
        List<TbSignal> list = Arrays.asList(signal);

        when(signalService.getSignalByCarId(1, "2023-10")).thenReturn(list);

        // Act & Assert
        mockMvc.perform(get("/api/signal/getSignalByCarId")
                        .param("carId", "1")
                        .param("month", "2023-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data").isArray());

        verify(signalService, times(1)).getSignalByCarId(1, "2023-10");
    }


}
