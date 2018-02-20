import zmq
import json
from client_server.service_visit_manager import ServiceVisitManager
from client_server import constants

class Server:
        
    def __init__(self):
        self._visitManager = ServiceVisitManager()
        
    def _handleRequest(self, message):
        if (message["op"] == "create visit"):
            status, serviceId, message = self._visitManager.createServiceVisit(message["customer name"], message["customer address"], message["description"])
            response = {constants.KEY_STATUS:status, constants.KEY_SERVICE_ID:serviceId, constants.KEY_MESSAGE:message}
            return response
    
    def run(self):
        context = zmq.Context()
        socket = context.socket(zmq.REP)
        socket.bind(constants.SERVER_LOCATION)
        
        request = json.loads(socket.recv_string())
        while request != "exit":
            response = self._handleRequest(request)
            socket.send_string(json.dumps(response))
            request = json.loads(socket.recv_string())
        
if(__name__ == "__main__"):
   Server().run()