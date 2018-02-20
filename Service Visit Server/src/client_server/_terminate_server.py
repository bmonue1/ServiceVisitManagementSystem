if(__name__ == "__main__"):
    import zmq
    import json
    from client_server import constants
    #  Prepare our context and sockets
    context = zmq.Context()
    socket = context.socket(zmq.REQ)
    print("connecting to server...")
    socket.connect(constants.SERVER_LOCATION)
    
    print("sending exit message...")
    socket.send_string(json.dumps("exit"))
        