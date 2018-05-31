/**
 * Title:       Chat application - implements a JMS publisher/subscriber
 * ClassName:   Chat.java
 * Created:     November 16, 2009
 * Author:      Claudiu Vinte
 * Department:  Economic Informatics, The Bucharest Academy of Economic Studies
 *
 * Description: Defines a chat class able to open pub/sub sessions on an OpenMQ broker
 */

package jms.chat;

import java.io.*;
import javax.jms.*;
// import javax.naming.*;


public class Chat implements javax.jms.MessageListener
{
    private TopicSession pubSession;
    private TopicPublisher publisher;
    private TopicConnection connection;
    private String userName;

    /* Constructor used to Initialize Chat */
    // public Chat(String topicFactory, String topicName, String username)
    public Chat(String userName) throws Exception
    {
    	
    	// Obtain a JNDI connection using the jndi.properties file
        // InitialContext ctx = new InitialContext();

        // Look up a JMS connection factory
        // TopicConnectionFactory conFactory = (TopicConnectionFactory)ctx.lookup(topicFactory);

        String addressList = "http://asets.ase.ro:8080/imqhttp/tunnel";
        com.sun.messaging.TopicConnectionFactory topicConnectionFactory = new com.sun.messaging.TopicConnectionFactory();
        topicConnectionFactory.setProperty(com.sun.messaging.ConnectionConfiguration.imqAddressList, addressList);
        
        // Create a JMS connection
        // TopicConnection connection = conFactory.createTopicConnection();
        // TopicConnection connection = topicConnectionFactory.createTopicConnection();
        TopicConnection connection = topicConnectionFactory.createTopicConnection("guest", "guest");
        connection.setClientID(userName);

        // Create two JMS session objects
        TopicSession pubSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        TopicSession subSession = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

        // Look up a JMS topic
        // Topic chatTopic = (Topic)ctx.lookup(topicName);
        Topic chatTopic = pubSession.createTopic("CHAT_TOPIC");

        // Create a JMS publisher and subscriber
        TopicPublisher publisher = pubSession.createPublisher(chatTopic);
        TopicSubscriber subscriber = subSession.createSubscriber(chatTopic, null, false);

        // Set a JMS message listener
        subscriber.setMessageListener(this);

        // Intialize the Chat application variables
        this.connection = connection;
        this.pubSession = pubSession;
        this.publisher = publisher;
        this.userName = userName;

        // Start the JMS connection; allows messages to be delivered
        connection.start();
    }

    /* Receive Messages From Topic Subscriber */
    public void onMessage(Message message)
    {
        try
        {
            TextMessage textMessage = (TextMessage) message;
            System.out.println(textMessage.toString());
            String text = textMessage.getText();
            System.out.println(text);
        } catch (JMSException jmse){ jmse.printStackTrace(); }
    }

    /* Create and Send Message Using Publisher */
    protected void writeMessage(String text) throws JMSException
    {
        TextMessage message = pubSession.createTextMessage();
        message.setText(userName+": "+text);
        publisher.publish(message);
    }

    /* Close the JMS Connection */
    public void close() throws JMSException
    {
        connection.close();
    }

    /* Run the Chat Client */
    public static void main(String [] args)
    {
        try
        {
            // if (args.length != 3)
            //    System.out.println("Factory, Topic, or username missing");
            if (args.length != 1)
            {
                System.out.println("User name missing ... exiting");
                System.exit(0);// exit program
            }
            
            // args[0]=topicFactory; args[1]=topicName; args[2]=username
            // Chat chat = new Chat(args[0],args[1],args[2]);
            // args[0]= userName
            Chat chat = new Chat(args[0]);

            // Print a sort of user's guide message
            System.out.println("Type your messages below. To terminate type: exit");

            // Read from command line
            BufferedReader commandLine = new java.io.BufferedReader(new InputStreamReader(System.in));

            // Loop until the word "exit" is typed
            while(true)
            {
                String s = commandLine.readLine();
                if (s.equalsIgnoreCase("exit"))
                {
                    chat.close(); // close down connection
                    System.exit(0);// exit program
                } else 
                    chat.writeMessage(s);
            }
        } catch (Exception e) { e.printStackTrace(); }
    }
}