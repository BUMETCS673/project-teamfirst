package edu.bu.metcs673_notification_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.metcs673_notification_service.entity.NotificationEntity;
import edu.bu.metcs673_notification_service.observerpattern.NotificationStatusService;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationControllerTest3 {

    //set uo simulate http requests
    private MockMvc mockMvc;

    //create obj for dependencies
    @Mock
    private RabbitTemplate customRabbitTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private NotificationStatusService notificationStatusService;

    // inject the mocks into the NotificationController

    @InjectMocks
    private NotificationController notificationController;

    @BeforeEach
    public void setUp() {
        // initializes the mocks
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(notificationController).build();
    }

    @Test
    public void testDeleteeEmailMessage() throws Exception {

        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessageId("12345");
        when(notificationStatusService.getNotificationById(anyString()))
                .thenReturn(notificationEntity);

        // Simulate a PUT request to update-email
        mockMvc.perform(delete("/v1/notifications/delete-email/12345")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }


}
