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
            task.removeMaterial(None)
        
    def testValidMaterialProvidedToTaskWithNoMaterials(self):
        task = Task("task a")
        
        with self.assertRaises(ValueError):
            task.removeMaterial("splitter")
        
    def testValidMaterialProvidedToTaskWithMaterialsThatDoesNotContainTheMaterial(self):
        task = Task("task a", materials=["coax"])
        
        with self.assertRaises(ValueError):
            task.removeMaterial("splitter")
        
    def testValidMaterialProvidedToTaskWithOneMaterial(self):
        task = Task("task a", materials=["splitter"])
        
        task.removeMaterial("splitter")
        
        self.assertEqual(0, len(task.getMaterials()), "checking number of materials")
        
    def testValidMaterialProvidedToTaskWithMultipleMaterialsAndNoDuplicates(self):
        task = Task("task a", materials=["splitter","coax"])
        
        task.removeMaterial("splitter")
        
        self.assertEqual(1, len(task.getMaterials()), "checking number of materials")
        self.assertTrue("coax" in task.getMaterials(), "checking for material added")
        
    def testValidMaterialProvidedToTaskWithMultipleMaterialsAndMaterialHasDuplicates(self):
        task = Task("task a", materials=["splitter","splitter","coax"])
        
        task.removeMaterial("splitter")
        
        self.assertEqual(2, len(task.getMaterials()), "checking number of materials")
        self.assertTrue("coax" in task.getMaterials(), "checking for material added")
        self.assertTrue("splitter" in task.getMaterials(), "checking for material added")
