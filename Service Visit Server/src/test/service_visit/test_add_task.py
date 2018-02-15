from unittest import TestCase
from model.service_visit import ServiceVisit
from model.task import Task

class TestAddTask(TestCase):
    def testAddNoneTask(self):
        visit = ServiceVisit("name", "address", "description")
        
        with self.assertRaises(ValueError):
            visit.addTask(None)
    
    def testAddValidTaskToServiceVisitWithNoTasks(self):
        visit = ServiceVisit("name", "address", "description")
        task = Task("task a")
        
        visit.addTask(task)
        
        self.assertEqual(1, len(visit.getTasks()), "checking number of tasks")
        self.assertTrue(task in visit.getTasks(), "checking for task added")
    
    def testAddValidTaskToServiceVisitWithSomeTasks(self):
        task1 = Task("task a")
        visit = ServiceVisit("name", "address", "description", [task1])
        task2 = Task("task b")
        
        visit.addTask(task2)
        
        self.assertEqual(2, len(visit.getTasks()), "checking number of tasks")
        self.assertTrue(task1 in visit.getTasks(), "checking for pre-existing task")
        self.assertTrue(task2 in visit.getTasks(), "checking for task added")
        

