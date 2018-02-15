from model.task_status import TaskStatus

'''
Stores basic information for a Task

@author jcorley
'''
class Task:
    _idCounter = 0
    
    '''
    Creates a new Task with the provided description and optional status and list of materials
    
    @precondition description != None &&
                  status != None && isinstance(status, TaskStatus)
    @postcondition getDescription() == description &&
                   getStatus() == status &&
                   getMaterials() == materials if materials != None else getMaterials() == []
    
    @param description description of the purpose and overview of the Task
    @param status the current status of the Task, defaults to TaskStatus.ASSIGNED
    @param materials (optional)a list of the materials used to complete the Task, defaults to an empty list
    '''
    def __init__(self, description, status=TaskStatus.ASSIGNED, materials=None):
        if(description == None):
            raise ValueError("description must not be None")
        if(status == None):
            raise ValueError("status must not be None")
        if(not isinstance(status, TaskStatus)):
            raise ValueError("status provided must a be a TaskStatus")
        self._description = description
        self._status = status
        if(materials == None):
            self._materials = []
        else:
            self._materials = materials
        self._taskId = self._getNextId()
    '''
    Determines the next task id to be used
    '''
    def _getNextId(self):
        Task._idCounter+=1
        return Task._idCounter

    '''
    Get the task id for the task
    
    @return the task id for the task
    '''
    def getTaskId(self):
        return self._taskId
    
    '''
    Get the description of the purpose and overview of the Task
    
    @return the description of the purpose and overview of the Task
    '''
    def getDescription(self):
        return self._description
    
    '''
    Get the current status of the Task
    
    @return the current status of the Task
    '''
    def getStatus(self):
        return self._status
    
    '''
    Get the list of the materials used to complete the Task
    
    @return the list of the materials used to complete the Task
    '''
    def getMaterials(self):
        return self._materials
    
    '''
    Update the status of the Task
    
    @precondition status != None && isinstance(status, TaskStatus)
    @postcondition getStatus() == status
    
    @param status the updated status of the Task, must be a TaskStatus
    '''
    def setStatus(self, status):
        if(status == None):
            raise ValueError("status must not be None")
        if(not isinstance(status, TaskStatus)):
            raise ValueError("status provided must a be a TaskStatus")
        self._status = status
        
    '''
    Update the description of the Task
    
    @precondition description != None
    @postcondition getDescription() == description
    
    @param description an updated description of the purpose and overview of the Task
    '''
    def setDescription(self, description):
        if(description == None):
            raise ValueError("description must not be None")
        self._description = description
    
    '''
    Add a material used to complete the Task
    
    @precondition material != None
    @postcondition material in getMaterials()
    
    @param material a material used to complete the Task
    '''
    def addMaterial(self, material):
        if(material == None):
            raise ValueError("material must not be None")
        self._materials.append(material)
        
    '''
    Remove the specified material from the collection of materials used to complete the Task
    
    @precondition material in getMaterials()
    @postcondition len(getMaterials()@pre) == len(getMaterials())+1
    '''
    def removeMaterial(self, material):
        self._materials.remove(material)