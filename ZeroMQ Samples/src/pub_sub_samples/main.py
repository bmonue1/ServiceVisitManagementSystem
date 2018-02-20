from multiprocessing import Process
import time
from pub_sub_samples.server import runPublisher
from pub_sub_samples.client import runSubscriber

def main():
    server = Process(target=runPublisher)
    client = Process(target=runSubscriber)
    print("main - starting server")
    server.start()
    print("main - waiting for server to start")
    time.sleep(.05)
    print("main - starting client")
    client.start()
    
    time.sleep(10)
    
    print("main - stopping the server")
    server.terminate()
    print("main - stopping the client")
    client.terminate()   

    
    
if(__name__ == "__main__"):
    main()
