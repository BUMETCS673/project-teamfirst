package edu.bu.metcs673_notification_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.bu.metcs673_notification_service.entity.NotificationEntity;
import edu.bu.metcs673_notification_service.service.EmailMessage;
import edu.bu.metcs673_notification_service.observerpattern.NotificationStatusService;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationControllerTest2 {

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
    public void testUpdateEmailMessage() throws Exception {
        //create sample
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo("test@example.com");
        emailMessage.setSubject("Test Subject");
        emailMessage.setBody("Test Body");
        emailMessage.setName("Test Name");

        //when add doNothing are used to define the behavior of mocked methods

        //tell mockito to return a specific JSON string when objectMapper.writeValueString() is called
        when(objectMapper.writeValueAsString(any(EmailMessage.class))).
                thenReturn("{\"to\":\"test@example.com\",\"subject\":\"Test Subject\",\"body\":\"Test Body\",\"name\":\"Test Name\"}");
        doNothing().when(customRabbitTemplate).
                convertAndSend(anyString(), anyString(), anyString());
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setMessageId("12345");
        when(notificationStatusService.getNotificationById(anyString()))
                .thenReturn(notificationEntity);

        // Simulate a PUT request to update-email
        mockMvc.perform(put("/v1/notifications/update-email/12345")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"to\":\"test@example.com\",\"subject\":\"Updated Subject\",\"body\":\"Updated Body\",\"name\":\"Updated Name\"}"))
                // Verify that the response status is OK (200)
                .andExpect(status().isOk());

    }


}
