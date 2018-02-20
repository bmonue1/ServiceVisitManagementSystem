'''
Created on Feb 14, 2018

@author: jcorley
'''
from unittest import TestCase
from model.task import Task
from model.task_status import TaskStatus

class TestSetStatus(TestCase):
    
    def testNoneStatusProvided(self):
        task = Task("task a")
        with self.assertRaises(ValueError):
            task.setStatus(None)
    
    def testStatusProvidedIsNotInstanceOfTaskStatus(self):
        task = Task("task a")
        with self.assertRaises(ValueError):
            task.setStatus(1)
            
    def testValidStatusProvided(self):
        task = Task("task a")
        
        task.setStatus(TaskStatus.COMPLETE)
        
        self.assertEqual(TaskStatus.COMPLETE, task.getStatus(), "checking status")