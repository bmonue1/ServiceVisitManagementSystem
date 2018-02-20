'''
Created on Feb 14, 2018

@author: jcorley
'''
from unittest import TestCase
from model.task import Task
from model.task_status import TaskStatus

class TestAddMaterial(TestCase):
    
        
    def testNoneMaterialProvided(self):
        task = Task("task a")
        with self.assertRaises(ValueError):
            task.addMaterial(None)
        
    def testValidMaterialProvidedToTaskWithNoMaterials(self):
        task = Task("task a")
        
        task.addMaterial("splitter")
        
        self.assertEqual(1, len(task.getMaterials()), "checking number of materials")
        self.assertTrue("splitter" in task.getMaterials(), "checking for material added")
        
    def testValidMaterialProvidedToTaskWithSomeMaterials(self):
        task = Task("task a", materials=["splitter"])
        
        task.addMaterial("coax")
        
        self.assertEqual(2, len(task.getMaterials()), "checking number of materials")
        self.assertTrue("splitter" in task.getMaterials(), "checking for existing material")
        self.assertTrue("coax" in task.getMaterials(), "checking for material added")