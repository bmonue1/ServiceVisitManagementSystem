from client_server.server import Server
from client_server import constants
from unittest import TestCase
import zmq
import json
from threading import Thread
import time

class TestRun(TestCase):
    
    def setUp(self):
        server = Server()
        serverThread = Thread(target=Server.run, args=(server,))
        serverThread.start()
        time.sleep(1)
        context = zmq.Context()
        self._socket = context.socket(zmq.REQ)
        self._socket.connect(constants.SERVER_LOCATION)
        
    def tearDown(self):
        self._socket.send_string(json.dumps("exit"))
            
    def testCreateServiceVisit(self):
        request = {"op":"create visit","customer name":"name", "customer address":"address", "description":"description"}
        self._socket.send_string(json.dumps(request))
        response = json.loads(self._socket.recv_string())
        
        self.assertEqual(constants.SUCCESS_STATUS, response[constants.KEY_STATUS], "checking status")
        self.assertNotEqual(constants.FAIL_ID, response[constants.KEY_SERVICE_ID], "checking id")
        self.assertEqual(constants.SUCCESS_MESSAGE, response[constants.KEY_MESSAGE], "checking message")
