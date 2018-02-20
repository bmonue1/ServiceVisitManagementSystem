from unittest import TestCase
from model.service_visit import ServiceVisit
from model.task import Task

class TestGetTaskById(TestCase):
    def testServiceVisitWithOneTaskWithSpecifiedId(self):
        task1 = Task("task a")
        visit = ServiceVisit("name", "address", "customer", [task1])
        
        result = visit.getTaskById(task1.getTaskId())
        
        self.assertEqual(task1, result, "checking result")
        
    def testServiceVisitWithMultipleTasksAndFirstHasSpecifiedId(self):
        task1 = Task("task a")
        task2 = Task("task b")
        task3 = Task("task c")
        visit = ServiceVisit("name", "address", "customer", [task1, task2, task3])
        
        result = visit.getTaskById(task1.getTaskId())
        
        self.assertEqual(task1, result, "checking result")
        
    def testServiceVisitWithMultipleTasksAndMiddleHasSpecifiedId(self):
        task1 = Task("task a")
        task2 = Task("task b")
        task3 = Task("task c")
        visit = ServiceVisit("name", "address", "customer", [task1, task2, task3])
        
        result = visit.getTaskById(task2.getTaskId())
        
        self.assertEqual(task2, result, "checking result")
        
    def testServiceVisitWithMultipleTasksAndLastHasSpecifiedId(self):
        task1 = Task("task a")
        task2 = Task("task b")
        task3 = Task("task c")
        visit = ServiceVisit("name", "address", "customer", [task1, task2, task3])
        
        result = visit.getTaskById(task3.getTaskId())
        
        self.assertEqual(task3, result, "checking result")
    
    def testServiceVisitWithOneTaskThatDoesNotHaveTheSpecifiedId(self):
        task1 = Task("task a")
        task2 = Task("task b")
        visit = ServiceVisit("name", "address", "customer", [task1])
        
        with self.assertRaises(ValueError):
            visit.getTaskById(task2.getTaskId())
