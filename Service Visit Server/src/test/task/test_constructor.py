'''
Created on Feb 14, 2018

@author: jcorley
'''
from unittest import TestCase
from model.task import Task
from model.task_status import TaskStatus

class TestConstructor(TestCase):
        
    def testNoneDescriptionProvided(self):
        with self.assertRaises(ValueError):
            Task(None)
    
    def testNoneStatusProvided(self):
        with self.assertRaises(ValueError):
            Task("task a", None)
    
    def testStatusProvidedIsNotInstanceOfTaskStatus(self):
        with self.assertRaises(ValueError):
            Task("task a", 1)
    
    def testValidDescriptionOnlyProvided(self):
        result = Task("task a")
        
        self.assertEquals("task a", result.getDescription(), "checking description")
        self.assertEquals(TaskStatus.ASSIGNED, result.getStatus(), "checking status")
        self.assertEquals(0, len(result.getMaterials()), "checking number of materials")
            
    def testValidDescriptionAndStatusProvided(self):
        result = Task("task a", TaskStatus.COMPLETE)
        
        self.assertEquals("task a", result.getDescription(), "checking description")
        self.assertEquals(TaskStatus.COMPLETE, result.getStatus(), "checking status")
        self.assertEquals(0, len(result.getMaterials()), "checking number of materials")
            
    def testValidDescriptionAndStatusAndEmptyListOfMaterialsProvided(self):
        result = Task("task a", TaskStatus.COMPLETE, [])
        
        self.assertEquals("task a", result.getDescription(), "checking description")
        self.assertEquals(TaskStatus.COMPLETE, result.getStatus(), "checking status")
        self.assertEquals(0, len(result.getMaterials()), "checking number of materials")
            
    def testValidDescriptionAndStatusAndNonEmptyListOfMaterialsProvided(self):
        result = Task("task a", TaskStatus.COMPLETE, ["splitter", "coax"])
        
        self.assertEquals("task a", result.getDescription(), "checking description")
        self.assertEquals(TaskStatus.COMPLETE, result.getStatus(), "checking status")
        self.assertEquals(2, len(result.getMaterials()), "checking number of materials")
        self.assertEquals("splitter", result.getMaterials()[0], "checking first material")
        self.assertEquals("coax", result.getMaterials()[1], "checking second material")