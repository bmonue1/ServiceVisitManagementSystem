from unittest import TestCase
from model.service_visit import ServiceVisit
from model.task import Task

class TestConstructor(TestCase):
        
    def testNoneCustomerNameProvided(self):
        with self.assertRaises(ValueError):
            ServiceVisit(None, "address", "description")
        
    def testNoneCustomerAddressProvided(self):
        with self.assertRaises(ValueError):
            ServiceVisit("name", None, "description")
        
    def testNoneDescriptionProvided(self):
        with self.assertRaises(ValueError):
            ServiceVisit("name", "address", None)
            
    def testValidInformationProvidedWithoutTaskList(self):
        result = ServiceVisit("name", "address", "description")
        
        self.assertEqual("name", result.getCustomerName(), "checking customer name")
        self.assertEqual("address", result.getCustomerAddress(), "checking customer address")
        self.assertEqual("description", result.getDescription(), "checking description")
            
    def testValidInformationProvidedIncludingEmptyTaskList(self):
        result = ServiceVisit("name", "address", "description", [])
        
        self.assertEqual("name", result.getCustomerName(), "checking customer name")
        self.assertEqual("address", result.getCustomerAddress(), "checking customer address")
        self.assertEqual("description", result.getDescription(), "checking description")
        self.assertEqual(0, len(result.getTasks()), "checking number of tasks")
            
    def testValidInformationProvidedIncludingNonEmptyTaskList(self):
        result = ServiceVisit("name", "address", "description", [Task("task a")])
        
        self.assertEqual("name", result.getCustomerName(), "checking customer name")
        self.assertEqual("address", result.getCustomerAddress(), "checking customer address")
        self.assertEqual("description", result.getDescription(), "checking description")
        self.assertEqual(1, len(result.getTasks()), "checking number of tasks")
