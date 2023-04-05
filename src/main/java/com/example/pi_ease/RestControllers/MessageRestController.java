package com.example.pi_ease.RestControllers;

import com.example.pi_ease.DAO.Entities.User;
import com.example.pi_ease.DAO.Repositories.MessageRepository;
import com.example.pi_ease.DAO.Repositories.UserRepository;
import com.example.pi_ease.Services.Classes.EmailServices;
import com.example.pi_ease.Services.Classes.MessageServices;
import com.example.pi_ease.Services.Classes.UserServices;
import com.example.pi_ease.Services.Interface.IUserServices;

import com.example.pi_ease.DAO.Entities.Message;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
public class MessageRestController {
    private UserServices userService;
    private UserRepository userRepository ;
    private IUserServices iUserServices ;
    private EmailServices emailServices ;
    private MessageRepository messageRepository;
    //private DoccatModel model;


    // public MessageRestController() throws IOException {
    // Charger le modèle pour la détection de gros mots
    //   InputStream modelIn = getClass().getResourceAsStream("C:/Users/HP/Documents/opennlp.bin");
    //   model = new DoccatModel(modelIn);
    //   modelIn.close();
    // }

    private MessageServices messageService;
    //@Autowired
    //private ProfanityFilterService profanityFilterService;
    //@Autowired
    //public MessageRestController(ProfanityFilterService profanityFilterService) {
    // this.profanityFilterService = profanityFilterService;
    //}

    //@Autowired
    //private ProfanityFilterService profanityFilterService;

    //@PostMapping("/{senderUsername}/{receiverUsername}")
    //public void sendMessage(@PathVariable int senderUsername, @PathVariable int receiverUsername, @RequestBody String content) {
    //  messageService.sendMessage(senderUsername, receiverUsername, content);
    //}
    //private IMicroGrowth iMicroGrowth;
    @PostMapping("/messagee")
    public List<String> createMessage(@RequestBody Message message) {
        {List<String> motsARechercher = Arrays.asList("merde", "bordel", "mot3");
            String texte = message.getContent();
            User user = userRepository.findById(1L).get();

            //String email=userRepository.findById(4L).getEget();.get();;
            for (String mot : motsARechercher) {
                if (texte.contains(mot)) {
                    message.setSentAt(LocalDateTime.now());
                    message.setContent("*****(Attention message cencuré)");
                    this.emailServices.sendSimpleEmail(user.getEmail(), "badwords", "Attention!!!");                    messageService.save(message);

                    // return ResponseEntity.badRequest().body("message censuré");
                }
            }}
        message.setSentAt(LocalDateTime.now());
        messageService.save(message);
        List<Message> messages = messageService.findBySenderAndRecipient(message.getSender(), message.getRecipient());

        messages.addAll(messageService.findBySenderAndRecipient(message.getRecipient(), message.getSender()));
        Collections.sort(messages, Comparator.comparing(Message::getSentAt));
        List<String> s=new ArrayList<>();
        for (Message m:messages) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.of(m.getSentAt().getYear(), m.getSentAt().getMonth(), m.getSentAt().getDayOfMonth(), m.getSentAt().getHour(), m.getSentAt().getMinute());
            String formattedDateTime = dateTime.format(formatter);
            s.add(m.getRecipient().getFirstName());
            s.add(m.getContent());
            s.add(formattedDateTime);
        }
        return s;}
    // }

    @GetMapping("/messagees/{sender}/{recipient}")
    public List<String> getMessages(@PathVariable String sender, @PathVariable String recipient) {
        User senderUser = userService.findByUsername(sender);
        System.out.println(senderUser);
        User recipientUser = userService.findByUsername(recipient);
        List<Message> messages = messageService.findBySenderAndRecipient(senderUser, recipientUser);

        messages.addAll(messageService.findBySenderAndRecipient(recipientUser, senderUser));
        Collections.sort(messages, Comparator.comparing(Message::getSentAt));
        List<String> s=new ArrayList<>();
        for (Message m:messages) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.of(m.getSentAt().getYear(), m.getSentAt().getMonth(), m.getSentAt().getDayOfMonth(), m.getSentAt().getHour(), m.getSentAt().getMinute());
            String formattedDateTime = dateTime.format(formatter);

            s.add(m.getContent());
            s.add(formattedDateTime);
        }
        return s;
    }

    @GetMapping("/messagees/{recipient}")
    public List<Message> getMessagesByRecipient(@PathVariable String recipient) {
        User recipientUser = userService.findByUsername(recipient);
        return messageService.findByRecipient(recipientUser);
    }

    // @Autowired
    //public MessageRestController() throws IOException {
    // profanityDetector = new ProfanityDetector();}
    @PostMapping("/messageezz")
    public Message createMessage1(@RequestBody Message message) {
        //if (profanityDetector.containsProfanity(message.getContent())) {
        //  message.setContent("Message censuré");
        //}
        message.setSentAt(LocalDateTime.now());
        return messageService.save(message);
        //return ResponseEntity.ok().build();}
    }

    @Scheduled(cron = "0 0 * * * *") // Toutes les heures à 0 minute et 0 seconde
    public void executeDelete() {
        messageRepository.deleteAll();
    }
// code json sur postman
//{
//        "content": "Hello !",
//                "sender": {
//            "idUser": 4,
//                    "firstName": "ddd"
//        },
//        "recipient": {
//            "idUser": 5,
//                    "firstName": "mimi"
//        }
//    }

  /* @MessageMapping("/message")
    @SendTo("/topic/return-to")
    public Message getContent(@RequestBody Message message){
        //  try
        //  {
        //  Thread.sleep(2000);

        // }catch(InterruptedException e ){
        //   e.printStackTrace();
        //}

        return message;
    }
  /*  @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    @SendTo("/chatroom/public")
    public Message receiveMessage(@Payload Message message){
        return message;
    }

    @MessageMapping("/private-message")
    public Message recMessage(@Payload Message message){
        simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(),"/private",message);
        System.out.println(message.toString());
        return message;
    }*/
}
