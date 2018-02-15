'''
Store information for a single ServiceVisit
'''
class ServiceVisit:
    
    '''
    Create a new ServiceVisit with the provided information
    
    @precondition customerName != None &&
                  customerAddress != None &&
                  description != None
    @postcondition getCustomerName() == customerName
                   getCustomerAddress() == customerAddress
                   getDescription() == description
                   getTasks() == tasks if tasks != None else getTasks() == []
                   
    @param customerName name of the customer for the visit
    @param customerAddress address of the customer for the visit
    @param description overview of the purpose for the visit
    @param tasks (optional)a list of tasks to be completed during the visit
    '''
    def __init__(self, customerName, customerAddress, description, tasks=None):
        if(customerName == None):
            raise ValueError("customer name must not be None")
        if(customerAddress == None):
            raise ValueError("customer address must not be None")
        if(description == None):
            raise ValueError("description must not be None")
        self._customerName = customerName
        self._customerAddress = customerAddress
        self._description = description
        if(tasks == None):
            self._tasks = []
        else:
            self._tasks = tasks
            
    '''
    Gets the name of the customer for the visit
    
    @return the name of the customer for the visit
    '''
    def getCustomerName(self):
        return self._customerName
            
    '''
    Gets the address of the customer for the visit
    
    @return the address of the customer for the visit
    '''
    def getCustomerAddress(self):
        return self._customerAddress
    
    '''
    Gets the overview of the purpose for the visit
    
    @return the overview of the purpose for the visit
    '''
    def getDescription(self):
        return self._description
    
    '''
    Gets the list of tasks to be completed during the visit
    
    @return the list of tasks to be completed during the visit
    '''
    def getTasks(self):
        return self._tasks
    
    '''
    Gets the Task with the specified taskId, if no task has the taskId an error is raised
    
    @precondition task with taskId in getTasks()
    
    @param taskId the taskId of the task to be returned
    
    @return the task with the specified taskId
    
    @raises ValueError if no task exists with the specified taskId
    '''
    def getTaskById(self, taskId):
        for task in self._tasks:
            if(task.getTaskId() == taskId):
                return task
        raise ValueError("Could not find Task with the provided id(" + str(taskId) + ")")
    
    '''
    Adds a new task to the ServiceVisit
    
    @precondition task != None
    @postcondition len(getTasks()) == len(getTasks()@pre)+1 &&
                   getTaskById(task.getId()) == task
                   
    @param task the task to be added to the service visit
    '''
    def addTask(self, task):
        if(task == None):
            raise ValueError("task provided may not be None")
        self._tasks.append(task)
