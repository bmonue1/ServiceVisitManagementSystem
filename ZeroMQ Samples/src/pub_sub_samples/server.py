from random import randint
import zmq
import time

# publisher thread
# The publisher sends random messages starting with A-J:

def runPublisher():
    context = zmq.Context.instance()

    publisher = context.socket(zmq.PUB)
    publisher.bind("tcp://127.0.0.1:5555")
    subscriptionOptions = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"]

    while True:
        subscription = subscriptionOptions[randint(0,9)]
        message = "{0}-{1}".format(subscription, randint(0,100000))
        print("PUBLISHING: " + message)
        try:
            publisher.send_string(message)
        except zmq.ZMQError as e:
            if e.errno == zmq.ETERM:
                break           # Interrupted
            else:
                raise e
        time.sleep(0.1)         # Wait for 1/10th second
        
if(__name__ == "__main__"):
    runPublisher()
