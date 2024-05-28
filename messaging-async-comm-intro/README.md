---
Title: Intro To Messaging and Asynchronous Communication
Type: Lesson
Duration: "0:55"
Author:
    Name: Alex De Marco
---

# ![](https://ga-dash.s3.amazonaws.com/production/assets/logo-9f88ae6c9c3871690e33280fcf557f33.png) Intro To Messaging and Asynchronous Communication

## Learning Objectives

*After this lesson, students will be able to:*

- Describe how asynchronous communication works conceptually. 
- Differentiate between queues and topics.
- List the basic features of messaging systems.
- Explain how asynchronous communication can improve scalability and performance.

## Lesson Guide

| TIMING  | TYPE  | TOPIC  |
|:---:|---|--- |
| 5 min   | Opening  | Lesson Objectives |
| 15 min  | Lecture  | Messaging Concepts |
| 15 min  | Lecture  | Definitions of Key Terms |
| 15 min  | Lecture  | Important Features of Messaging Systems |
| 5 min   | Conclusion | Review/Recap |

## Opening (5 min)

Communication between components in a [distributed system](https://en.wikipedia.org/wiki/Distributed_computing) can be synchronous or asynchronous. "Communication" in this context means that components send _messages_ to each other. 

In this lesson, we'll focus more on the asynchronous side of those communications: How it works and why it's important.

-----

## Messaging and Asynchronous Communication Concepts (15 min)

What does this look like in practice?

For example, one component in a system may be a client application, such as a mobile app, and another component may be a web server that's accessible over the internet via a REST API. The mobile app would send a message (HTTP request) to the server and expect the server to respond with a message (HTTP response).

Exchanging HTTP requests and responses over the internet can be thought of as _synchronous communication_, which means the calling service (e.g., the mobile application) waits until a response or error code is returned from the server before proceeding with other tasks.
 
In _asynchronous communication_, the calling service does not wait for a response; it fires the message and continues performing other tasks.

We can take advantage of asynchronous communication to do work in parallel and drastically improve overall performance. This is because, instead of waiting for a response from the other end (as in synchronous communication), the component can do other work such as sending or processing other messages. 

### Telephone vs. Email 

Let's think about telephone calls versus emails. 

On a phone call, you and the other person are on the line at the same time and take turns speaking to each other. If the call drops from one end, the conversation ends. 

When you communicate via email, you don't need to be online at the same time; you can read and send messages to _whomever_ you want (assuming you know their email address) _whenever_ you want, and your computer can crash in the middle of writing your email (very sad) without affecting the eventual receiver of the email message.

> **Knowledge Check**: Which technology (telephone or email) represents which type of communication (async or sync)?

![](https://peoplesofttutorial.com/wp-content/uploads/2013/04/difference-between-synchronous-and-asynchronous-messages.gif)
*[Source](https://peoplesofttutorial.com/difference-between-synchronous-and-asynchronous-messaging/)*

One of the biggest differences between messaging systems is whether they use synchronous or asynchronous message passing. **Synchronous message passing** occurs between objects that are running at the same time. In **asynchronous message passing** it is possible for the receiving object to be busy or not running when the requesting object sends the message.

Thinking about how we read email... in a typical 10-minute session, we can often get through dozens of emails. In a 10-minute phone call, on the other hand, all of your attention is tied up speaking to one person. 

To put it another way: If you had a customer service department of five agents reading customer emails, you can imagine that they'd get through a whole lot of emails quickly. If they only handled customer inquiries over the phone, they'd only be able to help five people at the same time (max), and that would leave a lot of customers on hold (ugh).

> **Knowledge Check**: Based on these analogies, why is asynchronous communication generally more efficient than synchronous?

All this is to say that, in asynchronous communication systems, a lot more work can usually get done in less time. Asynchronous communication systems scale better than synchronous systems: With the same number of resources (i.e., five customer service agents), a lot more work can be done (e.g., helping customers) in the same period of time.

---

## Key Async Communication Terms (15 min)

In asynchronous communication systems, also known as _messaging systems_, there are a few important definitions to know.

#### Message

A **message** is an information unit that contains the data that one component wants to communicate to other components. In messaging systems, messages typically have a **header** and a **body**. Sometimes the body is called the **payload**. The header contains metadata about the message, sender, and other data that would be useful for routing purposes. The body contains the main data content. The purpose of sending the message is to send this data.

For example, when a buyer makes a purchase on an eCommerce website, the website (a web server) might need to send a message to a fulfillment system at the company's warehouse. Workers at the warehouse would want to view that message through a mobile app in order to learn what was ordered and where to ship it. 

The message sent from the eCommerce website to the fulfillment application might contain information about the product purchased, the shipping address, the delivery class (next-day/expedited, seven-day ground, etc.), but probably not the price, sales taxes, and other information that isn't useful for fulfillment of the order.

If the message was encoded as a JSON object, it might look like this:

```json
{
  "header": {
    "from": "webserver1.ecommerce.mycompany.co",
    "messageType": "PurchaseOrder"    
  },
  "body": {
    "orderID": "edd094e9-3197-450c-bbb2-c9202f3ccf05",
    "createdAt": "2019-04-23T18:25:43.511Z",
    "items": [{
      "productID": "4735",
      "quantity": 1  
    }],
    "customer":{
      "accountID":"dd035953-bb86-4c5b-b08a-dadab98aa517",
      "name":"Bill Jones"  
    }, 
    "shippingAddress": {
      "street": "1234 Main Street",
      "city": "Middletown",
      "state": "DE",
      "country": "USA",
      "zip": 13121
    },
    "delivery": "expedited"
  }
}
```

That message would be received by the fulfillment system and saved in a database. It could then be retrieved from the database when a warehouse worker's mobile app "talks to" the fulfillment system via a REST API to download _new orders_.

#### Producer

**Producers** are the components that produce (or create) messages. They are sometimes called **publishers**, as in pub/sub messaging (covered here later). 

#### Consumer

**Consumers** are the components that consume (or read) messages.   

#### Bounded Buffer

When a producer creates a new message, it will be stored in a computer system's memory (and sometimes also on disk) until a consumer reads it. After the consumer reads it successfully, it can be removed from storage (consumed). 

Consuming the message is important because a computer's memory and disk space each have an upper limit (an upper bound); you can only store up to a certain number of messages, hence the term **bounded buffer**. If the buffer is full, a producer would need to wait before producing more messages. If the buffer is empty, the consumer doesn't have any work to do and needs to wait for a new message. The most common implementation of a bounded buffer in messaging systems is a message queue. 

#### Queue

A **queue** is where your messages are stored temporarily or buffered (see bounded buffer above). The most common type is a **first-in, first-out** (FIFO) queue. 

> A queue is a type of data structure that appears in lots of places, not just messaging systems. How does FIFO work in a queue data structure?

![](https://d1.awsstatic.com/product-marketing/Messaging/sqs_seo_queue.1dc710b63346bef869ee34b8a9a76abc014fbfc9.png)
*[Source](https://aws.amazon.com/message-queue/)*

A message queue is like waiting in line to get into a nightclub. It's typically FIFO unless you're a VIP. If you have a VIP pass and you show it to the nightclub's bouncer, they may let you in right away. In that case, that would be an example of a [**priority queue**](https://stackoverflow.com/questions/12692479/is-priorityqueue-a-fifo-queue).

> **Knowledge Check**: What are some other real-world examples of a priority queue?

One important thing to know about queues is that only one consumer can consume a message from a queue. Therefore, this kind of messaging is called "point-to-point" or "one-to-one" messaging. In contrast, there is "pub/sub" messaging, where many consumers can consume each message via _topics_. 

#### Topic

A topic is a named logical channel where messages are published.

In other words, when a producer needs to send a message to many consumers and it doesn't necessarily know (or care) who or where they are (e.g., which queue to send to), one way to solve the problem is to publish the message on a _topic_. Zero or more consumers can _subscribe_ for messages on that topic. When a message is published on a topic, any consumers that are subscribed to that topic will get a copy of the message. This is often called [pub/sub messaging](https://aws.amazon.com/pub-sub-messaging/).  

![](https://d1.awsstatic.com/product-marketing/Messaging/sns_img_topic.e024462ec88e79ed63d690a2eed6e050e33fb36f.png)
*[Source](https://aws.amazon.com/pub-sub-messaging/)*

A topic can represent pretty much anything. On [Reddit](https://www.reddit.com), you have subreddits for almost anything interesting to at least more than one person. Those can be thought of as examples of topics. Any Reddit user can publish a message in a subreddit, and anyone can subscribe to (or join) a subreddit. 

-----

## Features to Consider When Building Messaging Systems (15 min)

Before diving into [RabbitMQ](https://www.rabbitmq.com/) in the next lesson, we'll explain some key features of messaging systems in general. 

<dl>
    <dt>Reliability</dt>
    <dd>A messaging system may be reliable in the sense that your message is guaranteed to be delivered "at least once," "exactly once," or "at most once" (which is like saying the system is actually quite unreliable).</dd>    
    <dt>Sequence (Ordering of Messages)<td>
    <dd>Messages may be queued and delivered to consumers based on a certain order or sequencing scheme: "first-in, first-out (FIFO) order," ordered by priority, or in no defined order at all (i.e., random).</dd>    
    <dt>High Availability</dt> 
    <dd>Under load, or when a part of the messaging system is down, some part of the system may still be "up" and available to process messages. The degree to which your system is "highly available" to process messages is typically measured as a <a href="https://en.wikipedia.org/wiki/High_availability">percentage of uptime per year</a>. For example, 99.9% means a system is expected to be up (i.e., working) at least that percentage of the time in a typical year or rather down for at most 8.77 hours in total. A messaging system will support a number of different features to help achieve a high level of availability.</dd>    
    <dt>Persistence</dt>
    <dd>When your messaging system (point-to-point or pub/sub) crashes (stuff happens!), messages in memory are lost. If your messages are persistent, when the system comes back online, it can resume from where it left off. Most often, this persistence is supported by writing messages to disk and loading them from disk when resuming.</dd>
    <dt>Durability</dt>
    <dd>In pub/sub systems, all subscribers who are online are notified of the messages. However, there are times when consumers who were offline when the message was published want to be notified when they come back online. If a subscription between a publisher and subscriber is durable, when the subscriber comes back online, it will be notified of any messages from the publisher that were published while it was offline.</dd> 
    <dt>Routing</dt>
    <dd>Some messaging systems can route messages based on criteria in a message header or body. Routing means that you can define if-then rules to say where messages should go. For example, a simple rule might be to send all messages to everyone. This is also called "fan-out." Another example is: "if the message is from Jeff then discard it."</dd>    
    <dt>Error Handling</dt>
    <dd>Messaging systems often have built-in mechanisms for error handling. One common feature is to route undeliverable messages to a "dead letter queue" where it can be reviewed and inspected by a human administrator.</dd>
</dl>

*See also: [RabbitMQ Reliability Guide](https://www.rabbitmq.com/reliability.html)*

---

## Conclusion (5 min)

Yes, that was a lot of terms and definitions, but everything should become clear once we start working with an actual messaging tool: RabbitMQ. 

Before we wrap up, let's review a few key terms:

- Pub/sub messaging vs. one-to-one messaging
- Queue vs. priority queue
- Bounded buffer
- Producer and consumer
