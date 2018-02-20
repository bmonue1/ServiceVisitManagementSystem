from random import randint
import zmq
import time

# The subscriber thread requests messages starting with
# A and B, then reads and counts incoming messages.

def runSubscriber():
    ctx = zmq.Context.instance()

    # Subscribe to "A" and "B"
    subscriber = ctx.socket(zmq.SUB)
    subscriber.connect("tcp://127.0.0.1:5555")
    subscriber.setsockopt_string(zmq.SUBSCRIBE, "A")
    subscriber.setsockopt_string(zmq.SUBSCRIBE, "B")

    count = 0
    while True:
        try:
            msg = subscriber.recv_string()
        except zmq.ZMQError as e:
            if e.errno == zmq.ETERM:
                break           # Interrupted
            else:
                raise
        print("RECEIVED: " + msg)
        count += 1
        
if(__name__ == "__main__"):
    runSubscriber()
