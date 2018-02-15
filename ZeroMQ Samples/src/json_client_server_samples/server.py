import zmq
import time
import json

def runServer():
    context = zmq.Context()
    socket = context.socket(zmq.REP)
    socket.bind("tcp://127.0.0.1:5555")
    
    while True:
        #  Wait for next request from client
        print("waiting for message...")
        json_message = socket.recv_string()
        message = json.loads(json_message)
        print("Received request: %s" % message['content'])
        if(message['content'] == "exit"):
            return
        #  Do some 'work'
        time.sleep(1)
    
        #  Send reply back to client
        json_response = json.dumps("World")
        socket.send_string(json_response)
        
if(__name__ == "__main__"):
   runServer()