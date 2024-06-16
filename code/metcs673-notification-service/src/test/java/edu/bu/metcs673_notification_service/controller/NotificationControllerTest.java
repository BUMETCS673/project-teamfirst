package edu.bu.metcs673_notification_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class NotificationControllerTest {

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
    public void testSendEmailMessage() throws Exception {
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

        //Tell Mocktio do nothing when covertAnd Send is called on customRabbitTemplate with any three argument
        when(notificationStatusService.generateEmailContent(anyString(), anyString(), anyString(), anyMap())).
                thenReturn("Test Email Content");
        doNothing().when(notificationStatusService).
                saveNotification(any(EmailMessage.class), anyString(), anyString());
        //simulate post request to send-email

        mockMvc.perform(post("/v1/notifications/send-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"to\":\"test@example.com\",\"subject\":\"Test Subject\",\"body\":\"Test Body\",\"name\":\"Test Name\"}"))
                //verify test result
                .andExpect(status().isOk());
    }

    @Test
    public void testGetNotificationStatus() throws Exception {
        when(notificationStatusService.getStatusById(anyString())).thenReturn("sent");

        mockMvc.perform(get("/v1/notifications/status/12345"))
                .andExpect(status().isOk());
    }







}
