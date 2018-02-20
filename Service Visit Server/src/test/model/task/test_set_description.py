'''
Created on Feb 14, 2018

@author: jcorley
'''
from unittest import TestCase
from model.task import Task

class TestSetDescription(TestCase):
        
    def testNoneDescriptionProvided(self):
        task = Task("task a")
        with self.assertRaises(ValueError):
            task.setDescription(None)
    
        
    def testValidDescriptionProvided(self):
        task = Task("task a")
        
        task.setDescription("task b")
        
        self.assertEqual("task b", task.getDescription(), "checking description")
    
