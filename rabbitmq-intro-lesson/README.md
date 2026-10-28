---
Title: Intro to RabbitMQ
Type: Lesson
Duration: "1:25"
Author:
    Name: Alex De Marco
---

# <picture><img src="https://ga-curriculum.github.io/universal-resources-internal/ga-logos/ga-red-cog-128x128.png" alt="" height="48" align="absmiddle"></picture> Intro to RabbitMQ

### Learning Objectives

*After this lesson, students will be able to:*

- Explain how RabbitMQ is used.
- Install and run RabbitMQ.
- Build a messaging application that uses RabbitMQ.

### LESSON GUIDE

| TIMING  | TYPE  | TOPIC  |
|:-:|---|---|
| 10 min  | Opening  | Lesson Objectives |
| 30 min  | Exercise | Getting Started |
| 20 min  | Guided Practice  | Our First Messages |
| 20 min  | Guided Practice  | Running RabbitMQ From the Command Line |
| 5 min   | Conclusion  | Review/Recap |

## Opening (10 min)

We already know how messaging and asynchronous communication work — at least on a conceptual level. We also know why they're useful for our applications. 

> **Knowledge Check**: Just to be totally sure: Can someone shout out an explanation of messaging and asynchronous communication? Why are they important in our applications?

In this lesson, we'll learn how to set up and use RabbitMQ, the most widely used messaging broker around.

From the horse's mouth:

_"Messaging enables software applications to connect and scale. Applications can connect to each other, as components of a larger application, or to user devices and data. Messaging is asynchronous, decoupling applications by separating sending and receiving data. [...] RabbitMQ is a messaging broker — an intermediary for messaging. It gives your applications a common platform to send and receive messages, and your messages a safe place to live until received."_ ([Source](https://www.rabbitmq.com/features.html))

RabbitMQ supports a number of different messaging [protocols](https://www.rabbitmq.com/protocols.html), including **Advanced Messaging Queuing Protocol** (AMQP). AMQP is an open standard protocol that can be implemented by anyone and allows messaging systems from different vendors to interoperate.

RabbitMQ is commercial-grade, with features that make it suitable for a variety of enterprise applications. 

----

## Getting Started (30 min)

Before we get into the functionalities of RabbitMQ, let's learn a bit more about what it does.

### RabbitMQ Easter Egg Hunt

At this point in the course, you know that the docs are your best friend. Let's go on an Easter egg hunt through RabbitMQ's docs to answer the following questions:

- What are some of RabbitMQ's distinguishing features?
- How does AMQP work and what is RabbitMQ's role?
- How does RabbitMQ work in the command line?
- Translate some RabbitMQ jargon into “explain it like I’m 5” terms like what's an exchange.

Work with a partner to answer these questions. We'll check in as a group in a few minutes.

**Hint**: Start your hunt here:

- [RabbitMQ Docs](https://www.rabbitmq.com/documentation.html)
- [RabbitMQ Features](https://www.rabbitmq.com/features.html)
- [RabbitMQ Protocols](https://www.rabbitmq.com/protocols.html)

### Installation

We've also got to install the tool so we can start using it. We'll follow the instructions for homebrew on Mac.

1. [Download RabbitMQ](https://www.rabbitmq.com/download.html).

1. Install it using homebrew:
   
   ```sh   
   # Homebrew: 
   brew update
   brew install rabbitmq
   
   # Add RabbitMQ to your PATH:
   echo "export PATH=\$PATH:/usr/local/opt/rabbitmq/sbin" >> ~/.bash_profile
   source ~/.bash_profile
   
   # Start the server:
   rabbitmq-server
   
   # Stop the server.  
   # Press Control-C to stop the server at any time.
   ```     

1. Check the server (node) status:

    ```sh
    rabbitmqctl status
    ```

`rabbitmqctl` is the command line tool you can use to monitor and manage nodes. Run the command `rabbitmqctl help` to see a list of commands you can run.

> **Knowledge Check**: Do any of those commands jump out at you?

----

## Guided Practice: Our First Messages (20 mins)

We're ready to go! Next, we'll use the apps given to us [here](./starter-code/) to demonstrate messaging system concepts on RabbitMQ. We'll also use `rabbitmqctl` to perform some basic administrative commands on the message broker.  

### Review the Code 
 
There are couple of files in the directory for the `rabbit-mq` app. We will use it to send messages to our messaging queue. 

Open each file, and with your partner, review the code there. Discuss the following questions:
- What jumps out at you?
- How does the application compare to what we've looked at so far?
- What looks new? What looks familiar?

After you've taken time to discuss, we'll reconvene as a class.

----

## Running RabbitMQ From the Command Line (20 min)

The commands we'll walk through need to be run from the root of the lab directory. `cd` into`rabbit-mq` directory once you've cloned the code for this lab.

1. First, we'll build the application:

```sh
mvn clean install 
```

Pop open four terminal windows to use in this exercise. 

2. In the first terminal window, start the message broker:

```sh
rabbitmq-server    
```

3. In the next terminal window, you can run your app as a Spring Boot application or run the jar from command line to send messages to the queue.

```sh
java -jar target/rabbitmq-lab2-0.0.1-SNAPSHOT.jar
```

Check logs on the console to see if messages are being sent. We are using a scheduler to send message after every second in 10 seconds.

 
4. In a different terminal window, `cd` to receiver app and run the command below to listen for messages and print them on the screen when received:

```sh
mvn clean install
mvn exec:exec -Dreceive
   
# Enter Control-C to quit the program and stop listening.
```

5. Start up many consumers and see what happens. In the last terminal window, run the command below to start an additional instance of the `Receive` program:

```sh
mvn exec:exec -Dreceive
   
# Enter Control-C to quit the program and stop listening. 
```

You should observe that the message will be consumed once by only one of the receivers, but never both!

6. Now let's stop all our receivers and post a few messages to the queue:

```sh   
# Type Control-C in each of the receiver terminal windows.
```
   
Again run the `rabbit-mq` app to post a few messages to the queue:

```sh   
java -jar target/rabbitmq-lab2-0.0.1-SNAPSHOT.jar
```
   
Now, run this command to inspect your queues:

```sh
rabbitmqctl list_queues 
```
   
You should observe, as shown below, that a few messages will have accumulated in the queue:

```
Timeout: 60.0 seconds ...
Listing queues for vhost / ...
name	messages
queue1	3 
```

7. Finally, let's drain the queue by starting a receiver:

```sh   
mvn exec:exec -Dreceive
```
   
Now, run this command to inspect your queues:

```sh
rabbitmqctl list_queues 
```
   
You should observe, as shown below, that a few messages will have accumulated in the queue:

```
Timeout: 60.0 seconds ...
Listing queues for vhost / ...
name	messages
queue1	0 
```

---

## Conclusion (5 min)

Sending and receiving are some of the key features of a messaging system and some of the key functionality that RabbitMQ provides.

Up next, in our lab, we'll look into some of RabbitMQ's administrative features that support these processes.

#### Resources

- [Tutorial](https://www.rabbitmq.com/tutorials/tutorial-one-java.html)
