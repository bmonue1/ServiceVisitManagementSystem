from enum import Enum, auto

'''
Provides a set of constants to be used for the status of a Task

ASSIGNED - the Task has been assigned to a Service Tech, but the Task has not been started yet
INCOMPLETE - the Task was attempted, but could not be completed by the Service Tech
COMPLETE - the Task has been completed by the Service Tech
UNNECESSARY - the Task was not necessary to complete the Service Visit
'''
class TaskStatus(Enum):
    ASSIGNED = auto()
    INCOMPLETE = auto()
    COMPLETE = auto()
    UNNECESSARY = auto()