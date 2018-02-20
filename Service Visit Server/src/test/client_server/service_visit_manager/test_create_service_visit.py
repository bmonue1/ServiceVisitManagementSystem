from client_server.service_visit_manager import ServiceVisitManager
from unittest import TestCase
from client_server import constants

class TestCreateServiceVisit(TestCase):
        
    def testNoneCustomerNameProvided(self):
        manager = ServiceVisitManager()
        
        statusResult, idResult, messageResult = manager.createServiceVisit(None, "address", "description")
        
        self.assertEqual(constants.FAIL_STATUS, statusResult, "checking status")
        self.assertEqual(constants.FAIL_ID, idResult, "checking id")
        
    def testNoneCustomerAddressProvided(self):
        manager = ServiceVisitManager()
        
        statusResult, idResult, messageResult = manager.createServiceVisit("name", None, "description")
        
        self.assertEqual(constants.FAIL_STATUS, statusResult, "checking status")
        self.assertEqual(constants.FAIL_ID, idResult, "checking id")
        
    def testNoneDescriptionProvided(self):
        manager = ServiceVisitManager()
        
        statusResult, idResult, messageResult = manager.createServiceVisit("name", "address", None)
        
        self.assertEqual(constants.FAIL_STATUS, statusResult, "checking status")
        self.assertEqual(constants.FAIL_ID, idResult, "checking id")
            
    def testValidInformationProvidedWithoutTaskList(self):
        manager = ServiceVisitManager()
        
        statusResult, idResult, messageResult = manager.createServiceVisit("name", "address", "description")
        
        self.assertEqual(constants.SUCCESS_STATUS, statusResult, "checking status")
        self.assertNotEqual(constants.FAIL_ID, idResult, "checking id")
        self.assertEqual(constants.SUCCESS_MESSAGE, messageResult, "checking message")